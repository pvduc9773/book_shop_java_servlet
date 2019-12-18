package model.bean;

public class CartBean {
	private long idCart;
	private long idCustomer;
	private String idBook;
	private long quantity;
	private CustomerBean customer;
	private BookBean book;
	
	public CartBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CartBean(long idCart, long idCustomer, String idBook, long quantity) {
		super();
		this.idCart = idCart;
		this.idCustomer = idCustomer;
		this.idBook = idBook;
		this.quantity = quantity;
	}

	public CartBean(long idCart, long idCustomer, String idBook, long quantity, CustomerBean customer,
			BookBean book) {
		super();
		this.idCart = idCart;
		this.idCustomer = idCustomer;
		this.idBook = idBook;
		this.quantity = quantity;
		this.customer = customer;
		this.book = book;
	}

	public long getIdCart() {
		return idCart;
	}

	public void setIdCart(long idCart) {
		this.idCart = idCart;
	}

	public long getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(long idCustomer) {
		this.idCustomer = idCustomer;
	}

	public String getIdBook() {
		return idBook;
	}

	public void setIdBook(String idBook) {
		this.idBook = idBook;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public CustomerBean getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerBean customer) {
		this.customer = customer;
	}

	public BookBean getBook() {
		return book;
	}

	public void setBook(BookBean book) {
		this.book = book;
	}
}
