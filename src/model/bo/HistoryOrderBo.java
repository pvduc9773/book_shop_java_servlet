package model.bo;

import java.util.ArrayList;

import model.bean.OrderBean;
import model.dao.HistoryOrderDao;

public class HistoryOrderBo {
	private HistoryOrderDao historyOrderDao = new HistoryOrderDao();
	
	public ArrayList<OrderBean> getHistoryOrder(long idCustomer) throws Exception {
		return historyOrderDao.getHistoryOrder(idCustomer);
	}
}