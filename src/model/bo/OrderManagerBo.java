package model.bo;

import java.util.ArrayList;

import model.bean.OrderBean;
import model.bean.OrderDetailBean;
import model.dao.OrderManagerDao;

public class OrderManagerBo {
	private OrderManagerDao orderManagerDao = new OrderManagerDao();
	
	public ArrayList<OrderBean> getOrderConfirmed() throws Exception {
		return orderManagerDao.getOrderConfirmed();
	}
	
	public ArrayList<OrderBean> getOrderUnConfirm() throws Exception {
		return orderManagerDao.getOrderUnConfirm();
	}
	
	public OrderDetailBean getOrderDetailByID(long id) throws Exception {
		return orderManagerDao.getOrderDetailByID(id);
	}
	
	public int confirmOrder(long idOrder) throws Exception {
		return orderManagerDao.confirmOrder(idOrder);
	}
}
