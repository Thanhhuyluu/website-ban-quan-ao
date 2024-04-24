package model;

public class Category {
	private int id;
	private String name;
	private int type;
	public Category() {
		super();
	}
	public Category(int id, String name, int type) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
	}
	public Category(String name, int type) {
		super();
		this.name = name;
		this.type = type;
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
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name +", type=" + ((type==1) ? " áo" : " quần") + "]";
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	
	
	
}
