package model;

import java.util.List;

import dao.ImageDAO;
import dao.ProductDetailDAO;

public class ProductItem {
	private Product product;
    private List<ProductDetail> productDetails;
    private List<Image> images;
    
	
	public ProductItem(Product product) {
		super();
		this.product = product;
		this.productDetails = ProductDetailDAO.getInstance().selectByProductId(product.getId());
		this.images = ImageDAO.getInstance().selectByProductId(product.getId());
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public List<ProductDetail> getProductDetails() {
		return productDetails;
	}
	public void setProductDetails(List<ProductDetail> productDetails) {
		this.productDetails = productDetails;
	}
	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}

}
