package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import controller.ConnectionDatabase;
import model.bean.CategoryBean;

public class CategoryDao {
	private ConnectionDatabase cnn = new ConnectionDatabase();
	
	public ArrayList<CategoryBean> getCategories() throws Exception {
		ArrayList<CategoryBean> categories = new ArrayList<CategoryBean>();
		// Kết nối CSDL
		cnn.ConnectionDB();
		// Lấy CSDL
		String query = "SELECT * FROM Category";
		PreparedStatement cmd = cnn.connection.prepareStatement(query);
		ResultSet rs = cmd.executeQuery();        

		while (rs.next()) {
			String iDCategory = rs.getString("IDCategory");
			String name = rs.getString("Name");
			categories.add(new CategoryBean(iDCategory, name));
		}		
		// Đóng CSDL
		cnn.connection.close();
		return categories;
	}
	
	public CategoryBean getCategoryByID(String id) throws Exception {
		CategoryBean categoryBean = null;
		// Kết nối CSDL
		cnn.ConnectionDB();
		// Lấy CSDL
		String query = "SELECT * FROM Category WHERE IDCategory=?";
		PreparedStatement cmd = cnn.connection.prepareStatement(query);
		cmd.setString(1, id);
		ResultSet rs = cmd.executeQuery();        
		if (rs.next()) {
			String iDCategory = rs.getString("IDCategory");
			String name = rs.getString("Name");
			categoryBean = new CategoryBean(iDCategory, name);
		}		
		// Đóng CSDL
		cnn.connection.close();
		return categoryBean;
	}
	
	public boolean checkCategory(String idCategory) throws Exception {
		// Nếu ID đã tồn tại, trả về TRUE
		String query = "SELECT IDCategory FROM Category WHERE IDCategory=?";
		PreparedStatement cmd = cnn.connection.prepareStatement(query);
		cmd.setString(1, idCategory);
		ResultSet rs = cmd.executeQuery(); 
		boolean result = rs.next();
		rs.close();
		return result;
	}
	
	public int addCategory(String idCategory, String name) throws Exception {
		// Kết nối CSDL
		cnn.ConnectionDB();
		// Kiểm tra ID
		if (!checkCategory(idCategory)) {
			// Lấy CSDL
			String query = "INSERT INTO Category VALUES (?, ?)";
			PreparedStatement cmd = cnn.connection.prepareStatement(query);
			cmd.setString(1, idCategory);
			cmd.setString(2, name);
			int rs = cmd.executeUpdate();	
			// Đóng CSDL
			cmd.close();
			cnn.connection.close();
			return rs;
		}
		return 0;
	}
	
	public int updateCategory(String idCategory, String name) throws Exception {
		// Kết nối CSDL
		cnn.ConnectionDB();
		// Kiểm tra ID
		// Lấy CSDL
		String query = "UPDATE Category SET Name=? WHERE IDCategory=?";
		PreparedStatement cmd = cnn.connection.prepareStatement(query);
		cmd.setString(1, name);
		cmd.setString(2, idCategory);
		int rs = cmd.executeUpdate();	
		// Đóng CSDL
		cmd.close();
		cnn.connection.close();
		return rs;
	}
	
	public boolean checkBookInCategory(String idCategory) throws Exception {
		// Nếu Có Sách trả về TRUE
		String query = "SELECT IDBook FROM Book WHERE IDCategory=?";
		PreparedStatement cmd = cnn.connection.prepareStatement(query);
		cmd.setString(1, idCategory);
		ResultSet rs = cmd.executeQuery(); 
		boolean result = rs.next();
		rs.close();
		return result;
	}
	
	public int delelteCategory(String idCategory) throws Exception {
		// Kết nối CSDL
		cnn.ConnectionDB();
		// Kiểm tra ID
		if (!checkBookInCategory(idCategory)) {
			// Lấy CSDL
			String query = "DELETE FROM Category WHERE IDCategory=?;";
			PreparedStatement cmd = cnn.connection.prepareStatement(query);
			cmd.setString(1, idCategory);
			int rs = cmd.executeUpdate();	
			// Đóng CSDL
			cmd.close();
			cnn.connection.close();
			return rs;
		}
		return 0;
	}
}
