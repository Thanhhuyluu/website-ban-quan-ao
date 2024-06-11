package service;

import java.util.ArrayList;
import java.util.List;

import dao.ProductDAO;
import dao.ProductDetailDAO;
import model.CommonProductItem;
import model.Product;
import model.ProductDetail;

public class LikedProductsManager {
	private List<CommonProductItem> items;
	public LikedProductsManager() {
		items = new ArrayList<CommonProductItem>();
		
	}
	public List<CommonProductItem> getItems(){
		return items;
	}
	public CommonProductItem getItemById(int id) {
		for(CommonProductItem cp : items) {
			if(cp.getProduct() != null && cp.getProduct().getId() == id) {
				return cp;
			}
		}
		return null;
	}
	public void addItem(CommonProductItem cp) {
		CommonProductItem cP = getItemById(cp.getProduct().getId());
		if(cP != null) {
			return;
		}
		else {
			items.add(cP);
		}
		
		
	}
	public void removeItem(int id) {
		CommonProductItem cP = getItemById(id);
		if(cP!= null) {
			items.remove(cP);
		}
	}
	public LikedProductsManager(String txt) {
		items = new ArrayList<CommonProductItem>();
		if(!txt.isEmpty()) {
			String a[] = txt.split("/");
			for(int i = 0; i < a.length; i++) {
				Product pd = ProductDAO.getInstance().selectById(Integer.parseInt(a[i]));
				ProductDetail pdd = ProductDetailDAO.getInstance().selectOne(pd);
				if(pdd!= null) {
					items.add(new CommonProductItem(pd, pdd));
				}
			}
		
		}
	}
	public void update(CommonProductItem cp) {
		if(items!= null) {
			for( int i = 0; i < items.size(); i++) {
				if(items.get(i).getProduct().getId() == cp.getProduct().getId()) {
					
					items.set(i, cp);
				}
			}
		}
	}
	public static String LikedListToCookieTxt(LikedProductsManager likedProductsManager) {
		
		List<CommonProductItem> likedList = likedProductsManager.getItems();
		String txt="";
		if(likedList.size() > 0) {
			txt = likedList.get(0).getProduct().getId() +"";
			for(int i = 1;i < likedList.size(); i++) {
				txt+="/" + likedList.get(i).getProduct().getId();

			}
		}
		return txt;
	}
	public int getSize() {
		if(items != null) {
			return items.size();
		}
		return 0;
	}
}
