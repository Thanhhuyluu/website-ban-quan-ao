package model;

public class CartItem {
	private ProductDetail productDetail;
	private int quantity;
	private int price;
	public CartItem(ProductDetail productDetail, int quantity, int price) {
		super();
		this.productDetail = productDetail;
		this.quantity = quantity;
		this.price = price;
	}
	public CartItem() {
		super();
	}
	public ProductDetail getProductDetail() {
		return productDetail;
	}
	public void setProductDetail(ProductDetail productDetail) {
		this.productDetail = productDetail;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
}
