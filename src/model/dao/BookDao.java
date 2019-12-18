package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import controller.ConnectionDatabase;
import model.bean.BookBean;

public class BookDao {
	private ConnectionDatabase cnn = new ConnectionDatabase();
	
	public ArrayList<BookBean> getAllBooks() throws Exception {
		ArrayList<BookBean> books = new ArrayList<BookBean>();
		// Kết nối CSDL
		cnn.ConnectionDB();
		// Lấy CSDL
		String query = "SELECT * FROM Book";
		PreparedStatement cmd = cnn.connection.prepareStatement(query);
		ResultSet rs = cmd.executeQuery();        

		while (rs.next()) {
			String iDBook = rs.getString("IDBook");
			String name = rs.getString("Name");
			int quantity = rs.getInt("Quantity");
			long price = rs.getLong("Price");
			String iDCategory = rs.getString("IDCategory");
			String image = rs.getString("Image");
			Date dateInput = rs.getDate("DateInput");
			String author = rs.getString("Author");
			books.add(new BookBean(iDBook, name, quantity, price, iDCategory, image, dateInput, author));
		}		
		// Đóng CSDL
		cnn.connection.close();
		return books;
	}
	
	public BookBean getBooksByID(String id) throws Exception {
		BookBean book = null;
		// Kết nối CSDL
		cnn.ConnectionDB();
		// Lấy CSDL
		String query = "SELECT * FROM Book WHERE IDBook=?";
		PreparedStatement cmd = cnn.connection.prepareStatement(query);
		cmd.setString(1, id);
		ResultSet rs = cmd.executeQuery();        

		while (rs.next()) {
			String iDBook = rs.getString("IDBook");
			String name = rs.getString("Name");
			int quantity = rs.getInt("Quantity");
			long price = rs.getLong("Price");
			String iDCategory = rs.getString("IDCategory");
			String image = rs.getString("Image");
			Date dateInput = rs.getDate("DateInput");
			String author = rs.getString("Author");
			book = new BookBean(iDBook, name, quantity, price, iDCategory, image, dateInput, author);
			break;
		}
		// Đóng CSDL
		cnn.connection.close();
		return book;
	}
	
	public ArrayList<BookBean> getBooksByCategory(String idCategory) throws Exception {
		ArrayList<BookBean> books = new ArrayList<BookBean>();
		// Kết nối CSDL
		cnn.ConnectionDB();
		// Lấy CSDL
		String query = "SELECT * FROM Book WHERE IDCategory=?";
		PreparedStatement cmd = cnn.connection.prepareStatement(query);
		cmd.setString(1, idCategory);
		ResultSet rs = cmd.executeQuery();        

		while (rs.next()) {
			String iDBook = rs.getString("IDBook");
			String name = rs.getString("Name");
			int quantity = rs.getInt("Quantity");
			long price = rs.getLong("Price");
			String iDCategory = rs.getString("IDCategory");
			String image = rs.getString("Image");
			Date dateInput = rs.getDate("DateInput");
			String author = rs.getString("Author");
			books.add(new BookBean(iDBook, name, quantity, price, iDCategory, image, dateInput, author));
		}	
		// Đóng CSDL
		cnn.connection.close();
		return books;
	}
	
	public boolean checkIDBook(String idBook) throws Exception {
		// Nếu ID đã tồn tại, trả về TRUE
		String query = "SELECT IDBook FROM Book WHERE IDBook=?";
		PreparedStatement cmd = cnn.connection.prepareStatement(query);
		cmd.setString(1, idBook);
		ResultSet rs = cmd.executeQuery(); 
		boolean result = rs.next();
		rs.close();
		return result;
	}
	
	public int addBook(String iDBook, String name, int quantity, long price, String iDCategory, String image, Date dateInput, String author) throws Exception {
		// Kết nối CSDL
		cnn.ConnectionDB();
		// Kiểm tra ID
		if (!checkIDBook(iDBook)) {
			// Lấy CSDL
			java.sql.Date sqlDate = new java.sql.Date(dateInput.getTime());
			String query = "INSERT INTO Book (IDBook, Name, Quantity, Price, IDCategory, Image, DateInput, Author) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement cmd = cnn.connection.prepareStatement(query);
			cmd.setString(1, iDBook);
			cmd.setString(2, name);
			cmd.setInt(3, quantity);
			cmd.setLong(4, price);
			cmd.setString(5, iDCategory);
			cmd.setString(6, image);
			cmd.setDate(7, sqlDate);
			cmd.setString(8, author);
			int rs = cmd.executeUpdate();	
			// Đóng CSDL
			cmd.close();
			cnn.connection.close();
			return rs;
		}
		return 0;
	}
	
	public int updateBook(String iDBook, String name, int quantity, long price, String iDCategory, String image, Date dateInput, String author) throws Exception {
		// Kết nối CSDL
		cnn.ConnectionDB();
		// Kiểm tra ID
		// Lấy CSDL
		java.sql.Date sqlDate = new java.sql.Date(dateInput.getTime());
		String query = "UPDATE Book SET Name=?, Quantity=?, Price=?, IDCategory=?, Image=?, DateInput=?, Author=? WHERE IDBook=?";
		PreparedStatement cmd = cnn.connection.prepareStatement(query);
		cmd.setString(1, name);
		cmd.setInt(2, quantity);
		cmd.setLong(3, price);
		cmd.setString(4, iDCategory);
		cmd.setString(5, image);
		cmd.setDate(6, sqlDate);
		cmd.setString(7, author);
		cmd.setString(8, iDBook);
		int rs = cmd.executeUpdate();	
		// Đóng CSDL
		cmd.close();
		cnn.connection.close();
		return rs;
	}
	
	public int delelteBook(String idBook) throws Exception {
		// Kết nối CSDL
		cnn.ConnectionDB();
		// Kiểm tra ID
		// Lấy CSDL
		String query = "DELETE FROM Book WHERE IDBook=?;";
		PreparedStatement cmd = cnn.connection.prepareStatement(query);
		cmd.setString(1, idBook);
		int rs = cmd.executeUpdate();	
		// Đóng CSDL
		cmd.close();
		cnn.connection.close();
		return rs;
	}
}
