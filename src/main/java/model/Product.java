package model;

import java.sql.Date;

public class Product {

	private int id;
	private Category category;
	private Brand brand;
	private Supplier supplier;
	private String title;
	private int price,discount;
	private String img,description;
	private Date createdAt, updatedAt;
	private boolean deleted;
	private int  gender,likes;
	public Product() {
		super();
	}
	
	public Product(int id, Category category, Brand brand, Supplier supplier, String title, int price, int discount,
			String img, String description, Date createdAt, Date updatedAt, boolean deleted, int gender, int likes) {
		super();
		this.id = id;
		this.category = category;
		this.brand = brand;
		this.supplier = supplier;
		this.title = title;
		this.price = price;
		this.discount = discount;
		this.img = img;
		this.description = description;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.deleted = deleted;
		this.gender = gender;
		this.likes = likes;
	}

	public Product(Category category, Brand brand, Supplier supplier, String title, int price, int discount, String img,
			String description, Date createdAt, Date updatedAt, boolean deleted, int gender, int likes) {
		super();
		this.category = category;
		this.brand = brand;
		this.supplier = supplier;
		this.title = title;
		this.price = price;
		this.discount = discount;
		this.img = img;
		this.description = description;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.deleted = deleted;
		this.gender = gender;
		this.likes = likes;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", category=" + category + ", brand=" + brand + ", supplier=" + supplier
				+ ", title=" + title + ", price=" + price + ", discount=" + discount + ", img=" + img + ", description="
				+ description + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", deleted=" + deleted
				+ ", gender=" + gender + ", likes=" + likes + "]";
	}
	
	
}
