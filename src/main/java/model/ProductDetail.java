package model;

import java.sql.Date;

public class ProductDetail {
	private int id, productId;
	String size;
	int quantity;
	private Date createdAt;
	private String color;
	public ProductDetail() {
		super();
	}
	public ProductDetail(int id, int productId, String size, int quantity, Date createdAt, String color) {
		super();
		this.id = id;
		this.productId = productId;
		this.size = size;
		this.quantity = quantity;
		this.createdAt = createdAt;
		this.color = color;
	}
	public ProductDetail(int productId, String size, int quantity, Date createdAt, String color) {
		super();
		this.productId = productId;
		this.size = size;
		this.quantity = quantity;
		this.createdAt = createdAt;
		this.color = color;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "ProductDetail [id=" + id + ", productId=" + productId + ", size=" + size + ", quantity=" + quantity
				+ ", createdAt=" + createdAt + ", color=" + color + "]";
	}
	
	
	
}
