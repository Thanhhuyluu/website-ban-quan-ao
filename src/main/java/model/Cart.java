package model;

import java.util.ArrayList;
import java.util.List;

import dao.ProductDAO;

public class Cart {
	private List<CartItem> items;
	public Cart() {
		items= new ArrayList<CartItem>();
	}
	public List<CartItem> getItems(){
		return items;
	}
	public int getQuantityById(int id) {
		return getItemById(id).getQuantity();
	}
	
	
	private CartItem getItemById(int id) {
		for(CartItem ci : items) {
			if(ci.getProductDetail().getId() == id) {
				return ci;
			}
		}
		return null;
	}
	public void addItem(CartItem ci) {
		CartItem cI = getItemById(ci.getProductDetail().getId());
		if(cI!= null) {
			cI.setQuantity(cI.getQuantity() + ci.getQuantity());
		}else {
			items.add(cI);
		}
	}
	public void removeItems(int id) {
		CartItem cI = getItemById(id);
		if(cI != null) {
			items.remove(cI);
		}
	}
	public int getTotalMoney() {
		int t = 0;
		for(CartItem ci : items) {
			t+=(ci.getQuantity() * ci.getPrice());
		}
		return t;
	}
	
	private ProductDetail getProductDetailById(int id, List<ProductDetail> list) {
		for(ProductDetail i : list) {
			if(i.getId() == id) return i;
			
		}
		return null;
	}
	public Cart(String txt, List<ProductDetail> list) {
		items = new ArrayList<CartItem>();
		try {
			if(txt!= null && txt.length() != 0) {
				String[] s = txt.split(",");
				for(String i : s) {
					String[] n = i.split(":");
					int id = Integer.parseInt(n[0]);
					int quantity = Integer.parseInt(n[1]);
					ProductDetail p = getProductDetailById(id, list);
					CartItem ci = new CartItem(p, quantity, ProductDAO.getInstance().selectById(p.getProduct().getId()).getPrice());
					addItem(ci);
				}
						
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}
}
