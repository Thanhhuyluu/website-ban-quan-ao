package model;

import java.sql.Date;

public class ProductDetail {
	private int id;
	private Product product;
	private String size;
	private int quantity;
	private Date createdAt;
	private String color;
	public ProductDetail() {
		super();
	}
	
	public ProductDetail(int id, Product product, String size, int quantity, Date createdAt, String color) {
		super();
		this.id = id;
		this.product = product;
		this.size = size;
		this.quantity = quantity;
		this.createdAt = createdAt;
		this.color = color;
	}

	public ProductDetail(Product product, String size, int quantity, Date createdAt, String color) {
		super();
		this.product = product;
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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
		return "ProductDetail [id=" + id + ", product=" + product + ", size=" + size + ", quantity=" + quantity
				+ ", createdAt=" + createdAt + ", color=" + color + "]";
	}

}
