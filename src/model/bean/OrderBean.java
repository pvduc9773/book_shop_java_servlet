package model.bean;

import java.util.Date;

public class OrderBean {
	private long idOrder;
	private long idCustomer;
	private Date dateBuy;
	private boolean haveBuy;
	
	public OrderBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderBean(long idOrder, long idCustomer, Date dateBuy, boolean haveBuy) {
		super();
		this.idOrder = idOrder;
		this.idCustomer = idCustomer;
		this.dateBuy = dateBuy;
		this.haveBuy = haveBuy;
	}


	public long getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(long idOrder) {
		this.idOrder = idOrder;
	}

	public long getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(long idCustomer) {
		this.idCustomer = idCustomer;
	}

	public Date getDateBuy() {
		return dateBuy;
	}

	public void setDateBuy(Date dateBuy) {
		this.dateBuy = dateBuy;
	}

	public boolean isHaveBuy() {
		return haveBuy;
	}

	public void setHaveBuy(boolean haveBuy) {
		this.haveBuy = haveBuy;
	}

	
}
