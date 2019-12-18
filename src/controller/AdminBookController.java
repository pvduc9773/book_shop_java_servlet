package controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import model.bean.UserBean;
import model.bo.BookBo;
import model.bo.CategoryBo;

/**
 * Servlet implementation class AdminBookController
 */
@WebServlet("/AdminBookController")
public class AdminBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryBo categoryBo = new CategoryBo();
	private BookBo bookBo = new BookBo();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminBookController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				// Lấy dữ liệu từ client utf-8
				request.setCharacterEncoding("utf-8");
				// Gửi dữ liệu về client utf-8
				response.setCharacterEncoding("utf-8");
				
				DiskFileItemFactory factory = new DiskFileItemFactory();
				DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(fileItemFactory);
				upload.setHeaderEncoding("UTF-8"); 
				
				String iDBook = null;
				String name = null;
				String quantity = null;
				String price = null;
				String iDCategory = null;
				String image = null;
				String dateInput = null;
				String author = null;
				Boolean isUpdate = false;
				Boolean isAdd = false;
				
				HttpSession session = request.getSession();
				UserBean user = (UserBean) session.getAttribute("user_shop_book");

				// Check Login
				if (user == null) {
					response.sendRedirect("AuthController");
				} else {
					if (user.isPermission()) {

						try {
							try {
								List<FileItem> fileItems = upload.parseRequest(request);
								//Lấy về các đối tượng gửi lên
								//Duyệt qua các đối tượng gửi lên từ client gồm file và các control
								for (FileItem fileItem : fileItems) {
									if (!fileItem.isFormField()) {	//Nếu ko phải các control=>upfile lên
										//Xử lý file
										String nameimg = fileItem.getName();
										if (!nameimg.equals("")) {
											//Lấy đường dẫn hiện tại, chủ ý xử lý trên dirUrl để có đường dẫn đúng
											String dirUrl = request.getServletContext().getRealPath("") +  File.separator + "image_sach";
											File dir = new File(dirUrl);
											if (!dir.exists()) { //Nếu ko có thư mục thì tạo ra
												dir.mkdir();
											}
											String fileImg = dirUrl + File.separator + nameimg;
											File file = new File(fileImg);//Tạo file
											try {
												fileItem.write(file);//Lưu file
												System.out.println("UPLOAD THÀNH CÔNG...!");
												System.out.println("Đường dẫn lưu file là: " + dirUrl);
												image = "image_sach/" + nameimg;
												System.out.println("Lưu file: " + image);
											} catch (Exception e) {
												e.printStackTrace();
											}
										}
									} else {
										//Nếu là control
										String nameField=fileItem.getFieldName();
										if (nameField.equals("txtIDBook"))
											iDBook = fileItem.getString("UTF-8");
										if (nameField.equals("txtNameBook"))
											name = fileItem.getString("UTF-8");
										if (nameField.equals("txtQuantityBook"))
											quantity = fileItem.getString();
										if (nameField.equals("txtPriceBook"))
											price = fileItem.getString();
										if (nameField.equals("txtCategoryBook"))
											iDCategory = fileItem.getString("UTF-8");
										if (nameField.equals("txtDateInputBook"))
											dateInput = fileItem.getString();
										if (nameField.equals("txtAuthorBook"))
											author = fileItem.getString("UTF-8");
										if (nameField.equals("btnAdd")) {
											isAdd = true;
										}
										if (nameField.equals("btnUpdate")) {
											isUpdate = true;
										}
									}
								}
							} catch (FileUploadException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							if (isUpdate) {
								if (!iDBook.equals("") && !name.equals("") && !quantity.equals("") && !price.equals("") && !iDCategory.equals("") && !dateInput.equals("") && !author.equals("")) {
									if (image==null) {
										image = bookBo.getBookByID(iDBook).getImage();
									}
									SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
									Date date = f.parse(dateInput);
									int rsUpdate = bookBo.updateBook(iDBook, name, Integer.parseInt(quantity), Long.parseLong(price), iDCategory, image, date, author);
									if (rsUpdate == 0) request.setAttribute("rsUpdate", 0);
								} else {
									request.setAttribute("rsUpdate", 0);
								}
								
							}
							
							if (isAdd) {
								if (image==null)
									image = "image_sach/default.png";
								if (!name.equals("") && !quantity.equals("") && !price.equals("") && !iDCategory.equals("") && !author.equals("")) {
									SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
									Date sqlDate = null;
									if (dateInput.equals("")) {
										 sqlDate = f.parse(dateInput);
									} else {
										sqlDate = f.parse(dateInput);
									}
									int rsAdd = bookBo.addBook(iDBook, name, Integer.parseInt(quantity), Long.parseLong(price), iDCategory, image, sqlDate, author);
									if (rsAdd == 0) request.setAttribute("rsAdd", 0);
								} else {
									request.setAttribute("rsAdd", 0);
								}
								
							}
							
							if (request.getParameter("delete") != null) {
								int rsDelete = bookBo.delelteBook(request.getParameter("delete"));
								if (rsDelete == 0) request.setAttribute("rsDelete", 0);
							}
							
							if (request.getParameter("select") != null) {
								request.setAttribute("bookSelect", bookBo.getBookByID(request.getParameter("select")));
							}
								
							request.setAttribute("books", bookBo.getBooks());
							request.setAttribute("categories", categoryBo.getCategories());
							
							RequestDispatcher rd = request.getRequestDispatcher("AdminBook.jsp");
							rd.forward(request, response);
							
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						response.sendRedirect("HomeController");
					}
				}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
