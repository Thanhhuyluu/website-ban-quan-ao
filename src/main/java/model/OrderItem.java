package model;

import java.util.ArrayList;
import java.util.List;

import dao.OrderDetailDAO;

public class OrderItem {
	private Order order;
    private List<OrderDetail> orderDetails;
    private int priceOrder = 0; 
    private List<Integer> discountOrders;
	
	public OrderItem(Order order) {
		super();
		this.order = order;
		this.orderDetails = OrderDetailDAO.getInstance().selectByOrderId(order);
		this.priceOrder =  OrderDetailDAO.getInstance().getPriceByOrderId(order);
		this.discountOrders = getDiscount();
		
	}

	private List<Integer> getDiscount() {
		List<Integer> result = new ArrayList<Integer>();
		for (OrderDetail orderDetail : this.orderDetails) {
			result.add(orderDetail.getProductDetail().getProduct().getDiscount());
		}
		return result;
	}
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public int getPriceOrder() {
		return priceOrder;
	}

	public void setPriceOrder(int priceOrder) {
		this.priceOrder = priceOrder;
	}

	public List<Integer> getDiscountOrders() {
		return discountOrders;
	}

	public void setDiscountOrders(List<Integer> discountOrders) {
		this.discountOrders = discountOrders;
	}

}
