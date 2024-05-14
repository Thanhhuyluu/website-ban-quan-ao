package model;

public class MyItem {
	private String time;
    private int value;
    
    
	public MyItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MyItem(String time, int value) {
		super();
		this.time = time;
		this.value = value;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "MyItem [time=" + time + ", value=" + value + "]\n";
	}
    
    
}
