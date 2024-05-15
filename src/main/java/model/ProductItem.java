package model;

import java.util.List;

import dao.ImageDAO;
import dao.ProductDetailDAO;

public class ProductItem {
	private Product product;
    private List<ProductDetail> productDetails;
    private List<Image> images;
    private List<String> colorsOfProduct; 
    private List<String> sizesOfProduct;
    private int countProductDetail;
	
	public ProductItem(Product product) {
		super();
		this.product = product;
		this.productDetails = ProductDetailDAO.getInstance().selectByProductId(product.getId());
		this.images = ImageDAO.getInstance().selectByProductId(product.getId());
		this.colorsOfProduct = ProductDetailDAO.getInstance().selectDistinctColor(product);
		this.sizesOfProduct = ProductDetailDAO.getInstance().selectDistinctSize(product);
		this.countProductDetail = ProductDetailDAO.getInstance().countProductDetail(product);
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
	public List<String> getColorsOfProduct() {
		return colorsOfProduct;
	}
	public void setColorsOfProduct(List<String> colorsOfProduct) {
		this.colorsOfProduct = colorsOfProduct;
	}
	public List<String> getSizesOfProduct() {
		return sizesOfProduct;
	}
	public void setSizesOfProduct(List<String> sizesOfProduct) {
		this.sizesOfProduct = sizesOfProduct;
	}
	public int getCountProductDetail() {
		return countProductDetail;
	}
	public void setCountProductDetail(int countProductDetail) {
		this.countProductDetail = countProductDetail;
	}

}
