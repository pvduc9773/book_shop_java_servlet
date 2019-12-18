package model.bean;

public class OrderDetailBean {
	private long idOrderDetail;
	private String idBook;
	private long quantityBuy;
	private long idCart;
	
	public OrderDetailBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderDetailBean(long idOrderDetail, String idBook, long quantityBuy, long idCart) {
		super();
		this.idOrderDetail = idOrderDetail;
		this.idBook = idBook;
		this.quantityBuy = quantityBuy;
		this.idCart = idCart;
	}

	public long getIdOrderDetail() {
		return idOrderDetail;
	}

	public void setIdOrderDetail(long idOrderDetail) {
		this.idOrderDetail = idOrderDetail;
	}

	public String getIdBook() {
		return idBook;
	}

	public void setIdBook(String idBook) {
		this.idBook = idBook;
	}

	public long getQuantityBuy() {
		return quantityBuy;
	}

	public void setQuantityBuy(long quantityBuy) {
		this.quantityBuy = quantityBuy;
	}

	public long getIdCart() {
		return idCart;
	}

	public void setIdCart(long idCart) {
		this.idCart = idCart;
	}
}
