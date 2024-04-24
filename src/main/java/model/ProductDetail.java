package model;

import java.sql.Date;

public class ProductDetail {
	private int id, productId, size, quantity;
	private Date createdAt;
	private int color;
	private String img;
	public ProductDetail() {
		super();
	}
	public ProductDetail(int id, int productId, int size, int quantity, Date createdAt, int color, String img) {
		super();
		this.id = id;
		this.productId = productId;
		this.size = size;
		this.quantity = quantity;
		this.createdAt = createdAt;
		this.color = color;
		this.img = img;
	}
	public ProductDetail(int productId, int size, int quantity, Date createdAt, int color, String img) {
		super();
		this.productId = productId;
		this.size = size;
		this.quantity = quantity;
		this.createdAt = createdAt;
		this.color = color;
		this.img = img;
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
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
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
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	@Override
	public String toString() {
		return "ProductDetail [id=" + id + ", productId=" + productId + ", size=" + size + ", quantity=" + quantity
				+ ", createdAt=" + createdAt + ", color=" + color + ", img=" + img + "]";
	}
	
	
	
}
