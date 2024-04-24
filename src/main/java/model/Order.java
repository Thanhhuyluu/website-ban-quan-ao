package model;

import java.sql.Date;

public class Order {

	private int id, userId;
	private String fullname, email,phoneNum,address,note;
	private Date orderDate;
	private int status;
	public Order() {
		super();
	}
	public Order(int id, int userId, String fullname, String email, String phoneNum, String address, String note,
			Date orderDate, int status) {
		super();
		this.id = id;
		this.userId = userId;
		this.fullname = fullname;
		this.email = email;
		this.phoneNum = phoneNum;
		this.address = address;
		this.note = note;
		this.orderDate = orderDate;
		this.status = status;
	}
	public Order(int userId, String fullname, String email, String phoneNum, String address, String note,
			Date orderDate, int status) {
		super();
		this.userId = userId;
		this.fullname = fullname;
		this.email = email;
		this.phoneNum = phoneNum;
		this.address = address;
		this.note = note;
		this.orderDate = orderDate;
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", userId=" + userId + ", fullname=" + fullname + ", email=" + email + ", phoneNum="
				+ phoneNum + ", address=" + address + ", note=" + note + ", orderDate=" + orderDate + ", status="
				+ status + "]";
	}
	
	
	
	
}
