package model.bo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.bean.CartBean;
import model.dao.CartDao;

public class CartBo {
	private CartDao cartDao = new CartDao();
	
	public ArrayList<CartBean> getCarts(long idCustomer) throws Exception {
		return cartDao.getCarts(idCustomer);
	}
	
	public void addCart(String idBook, long idCustomer, long quantity) throws Exception{
		cartDao.addCart(idBook, idCustomer, quantity);
	}
	
	public void updateCart(long idCart, int quantity) throws Exception {
		cartDao.updateCart(idCart, quantity);
	}
	
	public void deleteCart(long idCart) throws Exception {
		cartDao.deleteCart(idCart);
	}
	
	public void orderCart(long idCustomer) throws Exception {
		cartDao.orderCart(idCustomer);
	}
	
	public boolean checkCart(String idBook, long idCustomer) throws Exception {
		return cartDao.checkCart(idBook, idCustomer);
	}
}
