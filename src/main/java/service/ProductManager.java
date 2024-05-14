package service;

import java.util.ArrayList;
import java.util.List;

import model.Product;
import model.ProductItem;

public class ProductManager {
	public static ProductManager getInstance() {
		return new ProductManager();
	}
	public List<Product> genderFilter(List<Product> li,int gender){
		List<Product> result = new ArrayList<Product>();
		for (Product product : li) {
			if(product.getGender() == gender || product.getGender() == 3) {
				result.add(product);
			}
		}
		
		
		return result;
	}
	public List<Product> saleOffFilter(List<Product> li){
		List<Product> result = new ArrayList<Product>();
		for(Product product : li) {
			if(product.getDiscount() > 0) {
				result.add(product);
			}
		}
		return result;
	}
	public List<Product> searchByKey(List<Product> li, String key){
		List<Product> result = new ArrayList<Product>();
		for(Product product : li) {
			if(product.getTitle().toLowerCase().contains(key.toLowerCase())) {
				result.add(product);
			}
		}
		return result;
	}
	public List<ProductItem> products2ProductItems(List<Product> products){
		List<ProductItem> lItems = new ArrayList<ProductItem>();
		for (Product product : products) {
			ProductItem n = new ProductItem(product);
			lItems.add(n);
		}
		
		
		return lItems;
		
	}

	
	
	
	
	
}
