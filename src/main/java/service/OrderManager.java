package service;

public class OrderManager {
	private OrderManager Instance;
	public OrderManager getInstance() {
		if(Instance == null) {
			Instance = new OrderManager();
		}
		return Instance;
	}
	
}
