package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.ProductDetailDAO;
import service.ProductDetailManager;

public class CartItem {
	private Product product;
	private ProductDetail productDetail;
	private int quantity;
	private int price;
	private Map<String, Boolean> colorMap;
	private Map<String, Boolean> sizeMap;
	
	public CartItem() {
		super();
		
	}
	public CartItem(Product product, ProductDetail productDetail, int quantity, int price) {
		super();
		this.product = product;
		this.productDetail = productDetail;
		this.quantity = quantity;
		this.price = price;
		
		List<ProductDetail> PDlist = ProductDetailDAO.getInstance().selectByProductId(product.getId());
		List<String> sizeList =ProductDetailManager.getInstance().getDistinctSize(PDlist);
		List<String> availableSizes = ProductDetailDAO.getInstance().selectDistinctSizeByColor(productDetail.getColor(),product.getId());

		
		this.sizeMap = new HashMap<String, Boolean>();
		for (String size : sizeList) {
			this.sizeMap.put(size, availableSizes.contains(size));
		}
		
		List<String> colorList = ProductDetailManager.getInstance().getDistinctColor(PDlist);
		List<String> availableColors = ProductDetailDAO.getInstance().selectDistinctColorBySize(productDetail.getSize(),product.getId());

		
		this.colorMap = new HashMap<String, Boolean>();
		for (String color : colorList) {
			colorMap.put(color, availableColors.contains(color));
		}
		
	}
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
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
	
	public Map<String, Boolean> getSizeMap() {
		return sizeMap;
	}
	public void setSizeMap(Map<String, Boolean> sizeMap) {
		this.sizeMap = sizeMap;
	}
	public Map<String, Boolean> getColorMap() {
		return colorMap;
	}
	public void setColorMap(Map<String, Boolean> colorMap) {
		this.colorMap = colorMap;
	}
	
	
	
}
