package test;


import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import dao.OrderDAO;
import dao.ProductDAO;
import model.Order;
import model.Product;
import model.User;

public class testDatabaseUtil {
	public static void main(String[] args) {
		
		/*
		 * Order o= OrderDAO.getInstance().selectLastestOrderOfUser(1);
		 * System.out.print(o.toString());
		 */
		User u = new User(2, null, null, null, null, null, null, null, 0, 0);
		Order od = new Order(u, "Thanh Huy", "thanhhuy@gmail.com", "0123312131","12 bàu mạc,hòa khánh,liên chiểu,đà nẵng" , "coi chừng vỡ hộp",Date.valueOf(LocalDate.now()) , 0);
		OrderDAO.getInstance().insert(od);
	}
}
