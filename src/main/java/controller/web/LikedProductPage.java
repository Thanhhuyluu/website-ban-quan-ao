package controller.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDetailDAO;
import model.Cart;
import model.CommonProductItem;
import model.ProductDetail;
import service.LikedProductsManager;
import service.ProductManager;

/**
 * Servlet implementation class LikedProductPage
 */
@WebServlet(urlPatterns = {"/yeu-thich"})
public class LikedProductPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LikedProductPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("null")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cart.setCartAttribute(request, response, null);
		ProductLikeHandle.setLikedProductCountAttribute(request, response);
		String action = request.getParameter("action");
		Cookie[] cookies = request.getCookies();
		String txt = "";
		if(cookies != null) {
			for(Cookie c : cookies) {
				if(c.getName().equals("likedProducts"))
					txt+=c.getValue();
			}
		}
		LikedProductsManager likedProductManager = new LikedProductsManager(txt);
		if(action != null && action.equals("process")) {
			String selectedColor = request.getParameter("selectedColor");
			String selectedSize = request.getParameter("selectedSize");
			String productDetailId_raw =request.getParameter("productDetailId");
			
			if(productDetailId_raw!= null || !productDetailId_raw.isEmpty()) {
				try {
					int productDetailId = Integer.parseInt(productDetailId_raw);
					ProductDetail productDetail = ProductDetailDAO.getInstance().selectById(productDetailId);
					
					 
					ProductDetail newProductDetail = ProductDetailDAO.getInstance().selectByColorAndSize(selectedColor, selectedSize,productDetail.getProduct().getId());
					if(newProductDetail == null) {
						newProductDetail = new ProductDetail(productDetail.getId(), productDetail.getProduct(), selectedSize, 0, null, selectedColor);
					}
					
					CommonProductItem comPro = new CommonProductItem(newProductDetail.getProduct(), newProductDetail);
					
					
					likedProductManager.update(comPro);
					Cookie co = new Cookie("likedProducts",LikedProductsManager.LikedListToCookieTxt(likedProductManager));
					co.setMaxAge(60*60*24*30*12);
					response.addCookie(co);
					
					
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}
		} else if(action != null && action.equals("add-to-cart")) {
			String productDetailId = request.getParameter("productDetailId");
			String buyQuantity = request.getParameter("buyQuantity");
			ProductDetail productDetail = ProductDetailDAO.getInstance().selectById(Integer.parseInt(productDetailId));
			likedProductManager.removeItem(productDetail.getProduct().getId());
			Cookie co = new Cookie("likedProducts",LikedProductsManager.LikedListToCookieTxt(likedProductManager));
			co.setMaxAge(60*60*24*30*12);
			response.addCookie(co);
			request.setAttribute("productDetailId",productDetailId);
			request.setAttribute("buyQuantity", buyQuantity);
			request.getRequestDispatcher("/gio-hang?action=add").forward(request, response);
		} else if(action != null && action.equals("delete")) {
			String productId = request.getParameter("productId");
			likedProductManager.removeItem(Integer.parseInt(productId));
			Cookie co = new Cookie("likedProducts",LikedProductsManager.LikedListToCookieTxt(likedProductManager));
			co.setMaxAge(60*60*24*30*12);
			response.addCookie(co);
			
		}else if(action!= null && action.equals("delete-all")) {
			Cookie[] cookies1 = request.getCookies();
			if (cookies1 != null) {
				for (Cookie c : cookies1) {
					if (c.getName().equals("likedProducts")) {
						c.setValue("");
						response.addCookie(c);
						
					}
				}
			}
		}
		List<CommonProductItem> productList = likedProductManager.getItems();
		request.setAttribute("productList", productList);
		
		
		request.getRequestDispatcher("/views/web/liked-product-page.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
