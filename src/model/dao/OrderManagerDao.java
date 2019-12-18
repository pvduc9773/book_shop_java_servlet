package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import controller.ConnectionDatabase;
import model.bean.BookBean;
import model.bean.OrderBean;
import model.bean.OrderDetailBean;

public class OrderManagerDao {
	private ConnectionDatabase cnn = new ConnectionDatabase();
	
	public ArrayList<OrderBean> getOrderConfirmed() throws Exception {
		ArrayList<OrderBean> carts = new ArrayList<OrderBean>();
		// Kết nối CSDL
		cnn.ConnectionDB();
		// Lấy CSDL
		String query = "SELECT * FROM Orders WHERE HaveBuy=?";
		PreparedStatement cmd = cnn.connection.prepareStatement(query);
		cmd.setBoolean(1, true);
		ResultSet rs = cmd.executeQuery();        

		while (rs.next()) {
			long iDOrder = rs.getLong("IDOrder");
			long iDCustomer = rs.getLong("IDCustomer");
			Date dateBuy = rs.getDate("DateBuy");
			boolean haveBuy = rs.getBoolean("HaveBuy");
			carts.add(new OrderBean(iDOrder, iDCustomer, dateBuy, haveBuy));
		}		
		// Đóng CSDL
		cnn.connection.close();
		return carts;
	}
	
	public ArrayList<OrderBean> getOrderUnConfirm() throws Exception {
		ArrayList<OrderBean> carts = new ArrayList<OrderBean>();
		// Kết nối CSDL
		cnn.ConnectionDB();
		// Lấy CSDL
		String query = "SELECT * FROM Orders WHERE HaveBuy=?";
		PreparedStatement cmd = cnn.connection.prepareStatement(query);
		cmd.setBoolean(1, false);
		ResultSet rs = cmd.executeQuery();        

		while (rs.next()) {
			long iDOrder = rs.getLong("IDOrder");
			long iDCustomer = rs.getLong("IDCustomer");
			Date dateBuy = rs.getDate("DateBuy");
			boolean haveBuy = rs.getBoolean("HaveBuy");
			carts.add(new OrderBean(iDOrder, iDCustomer, dateBuy, haveBuy));
		}		
		// Đóng CSDL
		cnn.connection.close();
		return carts;
	}
	
	public OrderDetailBean getOrderDetailByID(long id) throws Exception {
		OrderDetailBean orderDetailBean = null;
		// Kết nối CSDL
		cnn.ConnectionDB();
		// Lấy CSDL
		String query = "SELECT * FROM OrderDetail WHERE IDOrder=?";
		PreparedStatement cmd = cnn.connection.prepareStatement(query);
		cmd.setLong(1, id);
		ResultSet rs = cmd.executeQuery();        

		while (rs.next()) {
			long iDOrderDetail = rs.getLong("IDOrderDetail");
			String idBook = rs.getString("IDBook");
			long quantityBuy = rs.getLong("QuantityBuy");
			long idOrder = rs.getLong("IDOrder");
			orderDetailBean = new OrderDetailBean(iDOrderDetail, idBook, quantityBuy, idOrder);
		}		
		// Đóng CSDL
		cnn.connection.close();
		return orderDetailBean;
	}
	
	public int confirmOrder(long idOrder) throws Exception {
		// Kết nối CSDL
		cnn.ConnectionDB();
		// Lấy CSDL
		String query = "UPDATE Orders SET HaveBuy=? WHERE IDOrder=?";
		PreparedStatement cmd = cnn.connection.prepareStatement(query);
		cmd.setBoolean(1, true);
		cmd.setLong(2, idOrder);
		int rs = cmd.executeUpdate();	
		// Đóng CSDL
		cmd.close();
		cnn.connection.close();
		return rs;
	}
}
