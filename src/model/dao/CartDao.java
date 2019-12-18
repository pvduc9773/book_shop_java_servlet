package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import controller.ConnectionDatabase;
import model.bean.CartBean;
import model.bean.OrderBean;
import model.bean.OrderDetailBean;

public class CartDao {
	private ConnectionDatabase cnn = new ConnectionDatabase();
	
	public ArrayList<CartBean> getCarts(long idCustomer) throws Exception {
		ArrayList<CartBean> carts = new ArrayList<CartBean>();
		// Kết nối CSDL
		cnn.ConnectionDB();
		// Lấy CSDL
		String query = "SELECT * FROM Cart WHERE IDCustomer=?";
		PreparedStatement cmd = cnn.connection.prepareStatement(query);
		cmd.setLong(1, idCustomer);
		ResultSet rs = cmd.executeQuery();        

		while (rs.next()) {
			long iDCart = rs.getLong("IDCart");
			long iDCustomer = rs.getLong("IDCustomer");
			String idBook = rs.getString("IDBook");
			long quantity = rs.getLong("Quantity");
			carts.add(new CartBean(iDCart, iDCustomer, idBook, quantity));
		}		
		// Đóng CSDL
		cnn.connection.close();
		return carts;
	}
	
	public void addCart(String idBook, long idCustomer, long quantity) throws Exception{
		// Kết nối CSDL
		cnn.ConnectionDB();
		// Add Cart
		String query = "INSERT INTO Cart (IDCustomer, IDBook, Quantity) VALUES (?, ?, ?)";
		PreparedStatement cmd = cnn.connection.prepareStatement(query);
		cmd.setLong(1, idCustomer);
		cmd.setString(2, idBook);
		cmd.setLong(3, quantity);
		cmd.executeUpdate();
		// Đóng CSDL
		cnn.connection.close();
	}
	
	public boolean checkCart(String idBook, long idCustomer) throws Exception {
		// Kết nối CSDL
		cnn.ConnectionDB();
		// Add Cart
		String query = "SELECT IDBook FROM Cart WHERE IDBook=? AND IDCustomer=?";
		PreparedStatement cmd = cnn.connection.prepareStatement(query);
		cmd.setString(1, idBook);
		cmd.setLong(2, idCustomer);
		ResultSet rs = cmd.executeQuery(); 
		boolean result = rs.next();
		rs.close();
		return result;
	}
	
	public void orderCart(long idCustomer) throws Exception{
		List<CartBean> carts = getCarts(idCustomer);
		if (!carts.isEmpty()) {
			java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());
			// Kết nối CSDL
			cnn.ConnectionDB();
			for (CartBean cart: carts) {
				// Add Order
				String queryAddOrder = "INSERT INTO Orders (IDCustomer, DateBuy, HaveBuy) VALUES (?, ?, ?)";
				PreparedStatement cmdAddOrder = cnn.connection.prepareStatement(queryAddOrder);
				cmdAddOrder.setLong(1, idCustomer);
				cmdAddOrder.setDate(2, sqlDate);
				cmdAddOrder.setBoolean(3, false);
				cmdAddOrder.executeUpdate();
				// Get ID Cart
				long idOrder = 0;
				String queryGetMaxIDOrder = "SELECT IDOrder FROM Orders WHERE IDOrder = (SELECT MAX(IDOrder) FROM Orders)";
				PreparedStatement cmdGetMaxIDOrder = cnn.connection.prepareStatement(queryGetMaxIDOrder);
				ResultSet rs = cmdGetMaxIDOrder.executeQuery();        
				while (rs.next()) {
					idOrder = rs.getLong("IDOrder");
					break;
				}
				// Add OrderDetail
				String queryAddOrderDetail = "INSERT INTO OrderDetail (IDBook, QuantityBuy, IDOrder) VALUES (?, ?, ?)";
				PreparedStatement cmdAddOrderDetail = cnn.connection.prepareStatement(queryAddOrderDetail);
				cmdAddOrderDetail.setString(1, cart.getIdBook());
				cmdAddOrderDetail.setLong(2, cart.getQuantity());
				cmdAddOrderDetail.setLong(3, idOrder);
				cmdAddOrderDetail.executeUpdate();
				// Delete Cart
				String queryDeleteCart = "DELETE FROM Cart WHERE IDCart=?";
				PreparedStatement cmdDeleteCart = cnn.connection.prepareStatement(queryDeleteCart);
				cmdDeleteCart.setLong(1, cart.getIdCart());
				cmdDeleteCart.executeUpdate();
				// Đóng CSDL
			}
			cnn.connection.close();
		}
	}
	
	public void updateCart(long idCart, int quantity) throws Exception {
		// Kết nối CSDL
		cnn.ConnectionDB();
		// Lấy CSDL
		String query = "UPDATE Cart SET Quantity=? WHERE IDCart=?";
		PreparedStatement cmd = cnn.connection.prepareStatement(query);
		cmd.setInt(1, quantity);
		cmd.setLong(2, idCart);
		cmd.executeUpdate();        
		// Đóng CSDL
		cnn.connection.close();
	}
	
	public void deleteCart(long idCart) throws Exception {
		// Kết nối CSDL
		cnn.ConnectionDB();
		// Lấy CSDL
		String query = "DELETE FROM Cart WHERE IDCart=?";
		PreparedStatement cmd = cnn.connection.prepareStatement(query);
		cmd.setLong(1, idCart);
		cmd.executeUpdate();        
		// Đóng CSDL
		cnn.connection.close();
	}
}
