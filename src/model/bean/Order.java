package model.bean;

public class Order {
	private OrderBean order;
	private OrderDetailBean orderDetail;
	private BookBean book;
	private CustomerBean customer;
	
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(OrderBean order, OrderDetailBean orderDetail, BookBean book, CustomerBean customer) {
		super();
		this.order = order;
		this.orderDetail = orderDetail;
		this.book = book;
		this.customer = customer;
	}

	public OrderBean getOrder() {
		return order;
	}

	public void setOrder(OrderBean order) {
		this.order = order;
	}

	public OrderDetailBean getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(OrderDetailBean orderDetail) {
		this.orderDetail = orderDetail;
	}

	public BookBean getBook() {
		return book;
	}

	public void setBook(BookBean book) {
		this.book = book;
	}

	public CustomerBean getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerBean customer) {
		this.customer = customer;
	}
}
