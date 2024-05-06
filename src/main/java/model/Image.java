package model;

public class Image {
	private int id;
	private Product product;
	private String img;
	
	public Image() {
		super();
	}
	
	public Image(int id, Product product, String img) {
		super();
		this.id = id;
		this.product = product;
		this.img = img;
	}

	public Image(Product product, String img) {
		super();
		this.product = product;
		this.img = img;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public String toString() {
		return "Image [id=" + id + ", product=" + product + ", img=" + img + "]";
	}

}
