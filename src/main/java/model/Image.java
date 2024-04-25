package model;

public class Image {
	private int id;
	private int productId;
	private String img;
	public Image(int id, int productId, String img) {
		super();
		this.id = id;
		this.productId = productId;
		this.img = img;
	}
	public Image(int productId, String img) {
		super();
		this.productId = productId;
		this.img = img;
	}
	public Image() {
		super();
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
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	@Override
	public String toString() {
		return "image [id=" + id + ", productId=" + productId + ", img=" + img + "]";
	}
	
	
}
