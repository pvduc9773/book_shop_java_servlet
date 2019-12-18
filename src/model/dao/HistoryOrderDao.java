package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import controller.ConnectionDatabase;
import model.bean.OrderBean;

public class HistoryOrderDao {
	private ConnectionDatabase cnn = new ConnectionDatabase();
	
	public ArrayList<OrderBean> getHistoryOrder(long idCustomer) throws Exception {
		ArrayList<OrderBean> orders = new ArrayList<OrderBean>();
		// Kết nối CSDL
		cnn.ConnectionDB();
		// Lấy CSDL
		String query = "SELECT * FROM Orders WHERE IDCustomer=?";
		PreparedStatement cmd = cnn.connection.prepareStatement(query);
		cmd.setLong(1, idCustomer);
		ResultSet rs = cmd.executeQuery();        

		while (rs.next()) {
			long iDOrder = rs.getLong("IDOrder");
			long iDCustomer = rs.getLong("IDCustomer");
			Date dateBuy = rs.getDate("DateBuy");
			boolean haveBuy = rs.getBoolean("HaveBuy");
			orders.add(new OrderBean(iDOrder, iDCustomer, dateBuy, haveBuy));
		}		
		// Đóng CSDL
		cnn.connection.close();
		return orders;
	}

	
}
