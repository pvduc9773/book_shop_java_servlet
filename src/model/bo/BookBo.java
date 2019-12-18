package model.bo;

import java.util.ArrayList;
import java.util.Date;

import model.bean.BookBean;
import model.dao.BookDao;

public class BookBo {
	private BookDao bookDao = new BookDao();
	
	public ArrayList<BookBean> getBooks() throws Exception{
		return bookDao.getAllBooks();
	}
	
	public BookBean getBookByID(String id) throws Exception{
        return bookDao.getBooksByID(id);
    }
	
	public ArrayList<BookBean> getBooksByCategory(String idCategory) throws Exception{
		return bookDao.getBooksByCategory(idCategory);
	}
	
	public int addBook(String iDBook, String name, int quantity, long price, String iDCategory, String image, Date dateInput, String author) throws Exception {
		return bookDao.addBook(iDBook, name, quantity, price, iDCategory, image, dateInput, author);
	}
	
	public int updateBook(String iDBook, String name, int quantity, long price, String iDCategory, String image, Date dateInput, String author) throws Exception {
		return bookDao.updateBook(iDBook, name, quantity, price, iDCategory, image, dateInput, author);
	}
	
	public int delelteBook(String idBook) throws Exception {
		return bookDao.delelteBook(idBook);
	}
}
