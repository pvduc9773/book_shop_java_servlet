package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.BookBean;
import model.bean.Order;
import model.bean.OrderBean;
import model.bean.OrderDetailBean;
import model.bean.CustomerBean;
import model.bean.UserBean;
import model.bo.BookBo;
import model.bo.CategoryBo;
import model.bo.CustomerBo;
import model.bo.HistoryOrderBo;
import model.bo.OrderManagerBo;

/**
 * Servlet implementation class HistoryOrderController
 */
@WebServlet("/HistoryOrderController")
public class HistoryOrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryBo categoryBo = new CategoryBo();
	private HistoryOrderBo historyOrderBo = new HistoryOrderBo();
	private BookBo bookBo = new BookBo();
	private CustomerBo customerBo = new CustomerBo();
	private OrderManagerBo orderManagerBo = new OrderManagerBo();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HistoryOrderController() {
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
			ArrayList<Order> orders = new ArrayList<Order>();
			ArrayList<OrderBean> orderBeans = new ArrayList<OrderBean>();
			OrderDetailBean orderDetail = null;
			BookBean book = null;
			long idCustomer = 0;
			
			try {
				CustomerBean customerBean = customerBo.getCustomer(user.getUserName(), user.getPassword());
				if (customerBean != null) {
					idCustomer = customerBean.getIDCustomer();
				}	
				
				if (idCustomer != 0) {
					orderBeans = historyOrderBo.getHistoryOrder(idCustomer);
					if (orderBeans != null) {
						for (OrderBean orderBean : orderBeans) {
							orderDetail = orderManagerBo.getOrderDetailByID(orderBean.getIdOrder());
							if (orderDetail != null) {
								book = bookBo.getBookByID(orderDetail.getIdBook());	
								Order order = new Order(orderBean, orderDetail, book, null);
								orders.add(order);
							}
						}
					}
				}
				request.setAttribute("categories", categoryBo.getCategories());
				request.setAttribute("orders", orders);
				System.out.println("Order: " + orders.size());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// Gửi về client
			RequestDispatcher rd = request.getRequestDispatcher("HistoryOrder.jsp");
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
