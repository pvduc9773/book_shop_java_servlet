package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.BookBean;
import model.bean.CartBean;
import model.bean.Order;
import model.bean.OrderBean;
import model.bean.OrderDetailBean;
import model.bean.CustomerBean;
import model.bean.UserBean;
import model.bo.BookBo;
import model.bo.CartBo;
import model.bo.CategoryBo;
import model.bo.CustomerBo;

/**
 * Servlet implementation class CartController
 */
@WebServlet("/CartController")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryBo categoryBo = new CategoryBo();
	private CartBo cartBo = new CartBo();
	
	private BookBo bookBo = new BookBo();
	private CustomerBo customerBo = new CustomerBo();
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public CartController() {
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
		
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute("user_shop_book");

		// Check Login
		if (user == null) {
			response.sendRedirect("AuthController");
		} else {
			ArrayList<CartBean> carts = new ArrayList<CartBean>();
			BookBean book = null;
			CustomerBean customer = null;
			long idCustomer = 0;
			long sumProduct = 0;
			long sumPrice = 0;
			
			try {
				CustomerBean customerBean = customerBo.getCustomer(user.getUserName(), user.getPassword());
				if (customerBean != null) {
					idCustomer = customerBean.getIDCustomer();
				}
				
				String action = (String) request.getParameter("action");
				if (action != null) {
					if (action.equals("update")) {
						long idCart = Long.parseLong(request.getParameter("idcart"));
						int quantity = Integer.parseInt(request.getParameter("quantity"));
						cartBo.updateCart(idCart, quantity);
					}
					
					
					if (action.equals("delete")) {
						long idCart = Long.parseLong(request.getParameter("idcart"));
						cartBo.deleteCart(idCart);
					}
					
					if (action.equals("add")) {
						String idBook = request.getParameter("idbook");
						if (cartBo.checkCart(idBook, idCustomer)) {
							// Update
						} else {
							cartBo.addCart(idBook, idCustomer, 1);
						}
					}
					
					if (action.equals("order")) {
						cartBo.orderCart(idCustomer);
					}
				}
				
				if (idCustomer != 0) {
					carts = cartBo.getCarts(idCustomer);
					if (carts != null) {
						for (CartBean cartB : carts) {
							if (cartB != null) {
								book = bookBo.getBookByID(cartB.getIdBook());
								customer = customerBo.getCustomerByID(cartB.getIdCustomer());
								cartB.setBook(book);
								cartB.setCustomer(customer);
								sumProduct = sumProduct + cartB.getQuantity();
								sumPrice = sumPrice + (cartB.getBook().getPrice() * cartB.getQuantity());
							}
						}
					}
				}
				
				request.setAttribute("sumproducts", sumProduct);
				request.setAttribute("sumprice", sumPrice);
				request.setAttribute("categories", categoryBo.getCategories());
				request.setAttribute("carts", carts);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// Gửi về client
			RequestDispatcher rd = request.getRequestDispatcher("Cart.jsp");
			rd.forward(request, response);
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
