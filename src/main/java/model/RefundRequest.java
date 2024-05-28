package model;

import java.sql.Timestamp;

public class RefundRequest {
	int id;
	OnlinePayment onlinePayment;
	Timestamp requestTime;
	boolean status;
	public RefundRequest(int id, OnlinePayment onlinePayment, Timestamp requestTime, boolean status) {
		super();
		this.id = id;
		this.onlinePayment = onlinePayment;
		this.requestTime = requestTime;
		this.status = status;
	}
	public RefundRequest(OnlinePayment onlinePayment, Timestamp requestTime, boolean status) {
		super();
		this.onlinePayment = onlinePayment;
		this.requestTime = requestTime;
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public OnlinePayment getOnlinePayment() {
		return onlinePayment;
	}
	public void setOnlinePayment(OnlinePayment onlinePayment) {
		this.onlinePayment = onlinePayment;
	}
	public Timestamp getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(Timestamp requestTime) {
		this.requestTime = requestTime;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
}
