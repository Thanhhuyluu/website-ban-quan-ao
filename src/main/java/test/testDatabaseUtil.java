package test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dal.ProductDAO;
import dal.ProductDetailDAO;
import model.Product;
import model.ProductDetail;
import service.ProductDetailManager;

public class testDatabaseUtil {
	public static void main(String[] args) {
		 List<Product> products = ProductDAO.getInstance().selectRelatedProductsByBrand(3, 1);
		 for(Product p : products) {
			 System.out.println(p.toString());
		 }
	}
}
