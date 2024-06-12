package model;

public class Address {
	private String address;
	private String province;
	private String district;
	private String wards;
	
	
	public Address(String txt) {
		
		String a[] = txt.split("/");
		
		
		  this.address = a[0].trim(); 
		  this.wards = a[1].trim(); 
		  this.district = a[2].trim(); 
		  this.province = a[3].trim();
		  System.out.println("address" + a[0] + "  provice:" + a[1] + "   district:"+ a[2] + "  wards:" + a[3]);
		 
	}
	
	public Address(String address, String province, String district, String wards) {
		super();
		this.address = address;
		this.province = province;
		this.district = district;
		this.wards = wards;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getWards() {
		return wards;
	}
	public void setWards(String wards) {
		this.wards = wards;
	}
	
	
}
