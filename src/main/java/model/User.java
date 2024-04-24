package model;

import java.sql.Date;

public class User {

	private int id;
	private String fullname,email,phoneNumber,address,password;
	private Date createdAt,updatedAt;
	private int status; // 0 là bình thường, 1 là bị ban , 2 là đã xóa
	private int role; // 0 là khách 1 là admin
	
	public User() {
		super();
	}

	public User(int id, String fullname, String email, String phoneNumber, String address, String password,
			Date createdAt, Date updatedAt, int status, int role) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.password = password;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.status = status;
		this.role = role;
	}

	public User(String fullname, String email, String phoneNumber, String address, String password, Date createdAt,
			Date updatedAt, int	status, int role) {
		super();
		this.fullname = fullname;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.password = password;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.status = status;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNum) {
		this.phoneNumber = phoneNum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getStatus() {
		return status;
	}

	

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", fullname=" + fullname + ", email=" + email + ", phoneNumber=" + phoneNumber
				+ ", address=" + address + ", password=" + password + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + ", status=" + status + ", role=" + role + "]";
	}
	
	
	
}
