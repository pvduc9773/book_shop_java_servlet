package model.bo;

import model.bean.UserBean;
import model.dao.UserDao;

public class UserBo {
	private UserDao userDao = new UserDao();
	
	public UserBean getLogin(String un, String pw) throws Exception{
		return userDao.getLogin(un, pw);
	}

}
