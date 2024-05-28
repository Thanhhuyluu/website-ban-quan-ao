package model;

import java.time.LocalDateTime;

public class OnlinePayment {
	private int id;
	private Order order;
	private int amount;
	private String transactionNo;
	private LocalDateTime transactionDate;
	private String createdBy;
	public OnlinePayment(int id, Order order, int amount, String transactionNo, LocalDateTime transactionDate,
			String createdBy) {
		super();
		this.id = id;
		this.order = order;
		this.amount = amount;
		this.transactionNo = transactionNo;
		this.transactionDate = transactionDate;
		this.createdBy = createdBy;
	}
	public OnlinePayment(Order order, int amount, String transactionNo, LocalDateTime transactionDate,
			String createdBy) {
		super();
		this.order = order;
		this.amount = amount;
		this.transactionNo = transactionNo;
		this.transactionDate = transactionDate;
		this.createdBy = createdBy;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getTransactionNo() {
		return transactionNo;
	}
	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}
	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	
	
	
}
