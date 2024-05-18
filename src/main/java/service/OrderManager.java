package service;

import java.util.ArrayList;
import java.util.List;

import model.Order;
import model.OrderItem;

public class OrderManager {
	public static OrderManager getInstance() {
		return new OrderManager();
	}
	public List<OrderItem> oders2OrderItems(List<Order> orders){
		List<OrderItem> lItems = new ArrayList<OrderItem>();
		for (Order order : orders) {
			OrderItem n = new OrderItem(order);
			lItems.add(n);
		}
		
		
		return lItems;
		
	}
}
