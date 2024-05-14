package model;

public class OrderDetail {	
	private int id;
	private Order order;
	private ProductDetail productDetail;
	private int price,quantity;
	public OrderDetail() {
		super();
	}

	public OrderDetail(int id, Order order, ProductDetail productDetail, int price, int quantity) {
		super();
		this.id = id;
		this.order = order;
		this.productDetail = productDetail;
		this.price = price;
		this.quantity = quantity;
	}

	public OrderDetail(Order order, ProductDetail productDetail, int price, int quantity) {
		super();
		this.order = order;
		this.productDetail = productDetail;
		this.price = price;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public ProductDetail getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(ProductDetail productDetail) {
		this.productDetail = productDetail;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int num) {
		this.quantity = num;
	}

	@Override
	public String toString() {
		return "OrderDetail [id=" + id + ", order=" + order + ", productDetail=" + productDetail + ", price=" + price
				+ ", num=" + quantity + "]";
	}


	
}
