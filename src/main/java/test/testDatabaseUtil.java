package test;

import java.util.List;

import dal.ProductDetailDAO;
import model.ProductDetail;

public class testDatabaseUtil {
	public static void main(String[] args) {
		//User u = new User(0,"Thanh Huy Hoàng","thanhhuykd170104@gmail.com","0398074177","","0123456",null,null,0,0);
        List<ProductDetail> list= ProductDetailDAO.getInstance().selectByProductId(1);
        for(ProductDetail i : list) {
        	System.out.println(i.toString());
        }
	}
}
