package model;

public class Supplier {
	
	private int id;
	private String name,email,phoneNum,address;
	public Supplier() {
		super();
	}
	public Supplier(int id, String name, String email, String phoneNum, String address) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phoneNum = phoneNum;
		this.address = address;
	}
	public Supplier(String name, String email, String phoneNum, String address) {
		super();
		this.name = name;
		this.email = email;
		this.phoneNum = phoneNum;
		this.address = address;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	@Override
	public String toString() {
		return "Supplier [id=" + id + ", name=" + name + ", email=" + email + ", phoneNum=" + phoneNum + ", address="
				+ address + "]";
	}
	
	
}
