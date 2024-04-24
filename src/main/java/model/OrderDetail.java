package model;

public class OrderDetail {	
	private int id, orderId, productDetailId, price,num;

	public OrderDetail() {
		super();
	}

	public OrderDetail(int id, int orderId, int productDetailId, int price, int num) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.productDetailId = productDetailId;
		this.price = price;
		this.num = num;
	}

	public OrderDetail(int orderId, int productDetailId, int price, int num) {
		super();
		this.orderId = orderId;
		this.productDetailId = productDetailId;
		this.price = price;
		this.num = num;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getProductDetailId() {
		return productDetailId;
	}

	public void setProductDetailId(int productDetailId) {
		this.productDetailId = productDetailId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	@Override
	public String toString() {
		return "OrderDetail [id=" + id + ", orderId=" + orderId + ", productDetailId=" + productDetailId + ", price="
				+ price + ", num=" + num + "]";
	}
	
	
}
