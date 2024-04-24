package test;

import java.util.List;

import dal.CategoryDAO;
import dal.ProductDAO;
import dal.SupplierDAO;
import model.Category;
import model.Product;
import model.Supplier;
import model.User;
import service.ProductManager;
import service.UserManager;

public class testDatabaseUtil {
	public static void main(String[] args) {
		User u = new User(0,"Thanh Huy Hoàng","thanhhuykd170104@gmail.com","0398074177","","0123456",null,null,0,0);
		if(	UserManager.getInstance().IsExistAccount(u)) {
			System.out.print("okk");
		}else {
			System.out.print("not ok");
		}
	}
}
