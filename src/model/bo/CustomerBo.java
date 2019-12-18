package model.bo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.bean.CustomerBean;
import model.dao.CustomerDao;

public class CustomerBo {
	private CustomerDao customerDao = new CustomerDao();
	
	public CustomerBean getCustomer(String un, String pw) throws Exception {
		return customerDao.getCustomer(un, pw);
	}
	
	public CustomerBean getCustomerByID(long id) throws Exception {
		return customerDao.getCustomerByID(id);
	}
}
