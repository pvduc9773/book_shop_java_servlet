package controller;

import java.io.IOException;
import java.util.ArrayList;

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
import model.bo.CustomerBo;
import model.bo.OrderManagerBo;

/**
 * Servlet implementation class AdminOrderManagerController
 */
@WebServlet("/AdminOrderManagerController")
public class AdminOrderManagerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private OrderManagerBo orderManagerBo = new OrderManagerBo();
    private CustomerBo customerBo = new CustomerBo();
    private BookBo bookBo = new BookBo();
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminOrderManagerController() {
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
		
		ArrayList<Order> ordersConfirmed = new ArrayList<Order>();
		ArrayList<Order> ordersUnConfirm = new ArrayList<Order>();
		ArrayList<OrderBean> orderBeansConfirmed = new ArrayList<OrderBean>();
		ArrayList<OrderBean> orderBeansUnConfirm = new ArrayList<OrderBean>();
		OrderDetailBean orderDetail = null;
		BookBean book = null;
		CustomerBean customer = null;
		
		try {
			HttpSession session = request.getSession();
			UserBean user = (UserBean) session.getAttribute("user_shop_book");
			
			// Check Login
			if (user == null) {
				response.sendRedirect("AuthController");
			} else {
				if (user.isPermission()) {
					if (request.getParameter("confirm") != null) {
						int rsConfirm = orderManagerBo.confirmOrder(Long.parseLong(request.getParameter("confirm")));
						if (rsConfirm == 0) request.setAttribute("rsConfirm", 0);
					}
						
					orderBeansUnConfirm = orderManagerBo.getOrderUnConfirm();
					if (orderBeansUnConfirm != null) {
						for (OrderBean orderBean : orderBeansUnConfirm) {
							if (orderBean != null) {
								customer = customerBo.getCustomerByID(orderBean.getIdCustomer());
								orderDetail = orderManagerBo.getOrderDetailByID(orderBean.getIdOrder());
								if (orderDetail != null) {
									book = bookBo.getBookByID(orderDetail.getIdBook());	
									Order Unorder = new Order(orderBean, orderDetail, book, customer);
									ordersUnConfirm.add(Unorder);
								}
							}
						}
					}
					
					orderBeansConfirmed = orderManagerBo.getOrderConfirmed();
					if (orderBeansConfirmed != null) {
						for (OrderBean orderBean : orderBeansConfirmed) {
							if (orderBean != null) {
								customer = customerBo.getCustomerByID(orderBean.getIdCustomer());
								orderDetail = orderManagerBo.getOrderDetailByID(orderBean.getIdOrder());
								if (orderDetail != null) {
									book = bookBo.getBookByID(orderDetail.getIdBook());	
									Order order = new Order(orderBean, orderDetail, book, customer);
									ordersConfirmed.add(order);
								}
							}
						}
					}
					
					request.setAttribute("unconfirm", ordersUnConfirm);
					request.setAttribute("confirmed", ordersConfirmed);
					
					RequestDispatcher rd = request.getRequestDispatcher("AdminOrderManager.jsp");
					rd.forward(request, response);
				} else {
					response.sendRedirect("HomeController");
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
