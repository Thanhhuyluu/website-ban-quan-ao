package test;


import java.util.List;

import dao.ProductDAO;
import model.Product;

public class testDatabaseUtil {
	public static void main(String[] args) {
		 List<Product> products = ProductDAO.getInstance().selectRelatedProductsByBrand(3, 1);
		 for(Product p : products) {
			 System.out.println(p.toString());
		 }
	}
}
