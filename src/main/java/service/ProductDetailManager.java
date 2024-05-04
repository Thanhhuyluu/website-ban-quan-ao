package service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.ProductDetail;

public class ProductDetailManager {
	private static ProductDetailManager productDM;
	public static ProductDetailManager getInstance() {
		if(productDM == null) {
			productDM = new ProductDetailManager();
		}
		return productDM;
	}
	
	public List<String> getDistinctColor(List<ProductDetail> list) {
		Set<String> uniqueColors = new HashSet<String>();
		
		for(ProductDetail pd: list) {
			uniqueColors.add(pd.getColor());
		}
		return new ArrayList<String>(uniqueColors);
	}
	public List<String> getDistinctSize(List<ProductDetail> list) {
		Set<String> uniqueSizes = new HashSet<String>();
		
		for(ProductDetail pd: list) {
			uniqueSizes.add(pd.getSize());
		}
		return new ArrayList<String>(uniqueSizes);
	}
	public int getCurrentProductQuantity(List<ProductDetail>list, String Color, String Size) {
		int q = 0;
		for(ProductDetail pd : list) {
			if(pd.getColor().equals(Color) && pd.getSize().equals(Size)) {
				q = pd.getQuantity();
			}
			
		}
		return q;
		
	}
	public ProductDetail getProductDetailByProductIdAndSizeAndColor(int productId, String size, String color, List<ProductDetail>list) {
		for(ProductDetail productDetail : list) {
			if(productDetail.getProductId() == productId && productDetail.getSize().equals(size)&& productDetail.getColor().equals(color)) {
				return productDetail;
			}
		}
		return null;
	}
}
