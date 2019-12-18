package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.UserBean;
import model.bo.CategoryBo;

/**
 * Servlet implementation class AdminCategoryController
 */
@WebServlet("/AdminCategoryController")
public class AdminCategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CategoryBo categoryBo = new CategoryBo();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminCategoryController() {
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
		
		try {
			HttpSession session = request.getSession();
			UserBean user = (UserBean) session.getAttribute("user_shop_book");

			// Check Login
			if (user == null) {
				RequestDispatcher rd = request.getRequestDispatcher("AuthController");
				rd.forward(request, response);
			} else {
				if (user.isPermission()) {
					String idCategory = request.getParameter("txtIDCategory");
					String name = request.getParameter("txtNameCategory");
					
					if (request.getParameter("btnAdd") != null) {
						int rsAdd = categoryBo.addCategory(idCategory, name);
						if (rsAdd == 0) request.setAttribute("rsAdd", 0);
					}
					
					if (request.getParameter("btnUpdate") != null) {
						int rsUpdate = categoryBo.updateCategory(idCategory, name);
						if (rsUpdate == 0) request.setAttribute("rsUpdate", 0);
					}

					if (request.getParameter("delete") != null) {
						int rsDelete = categoryBo.deleteCategory(request.getParameter("delete"));
						if (rsDelete == 0) request.setAttribute("rsDelete", 0);
					}
					
					if (request.getParameter("select") != null) {
						request.setAttribute("categorySelect", categoryBo.getCategoryByID(request.getParameter("select")));
					}
					
					request.setAttribute("categories", categoryBo.getCategories());
					
					RequestDispatcher rd = request.getRequestDispatcher("AdminCategory.jsp");
					rd.forward(request, response);
				} else {
					RequestDispatcher rd = request.getRequestDispatcher("HomeController");
					rd.forward(request, response);
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
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
