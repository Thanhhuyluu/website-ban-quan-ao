package controller.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAO;
import dao.ProductDetailDAO;
import model.Cart;
import model.CartItem;
import model.Product;
import model.ProductDetail;

/**
 * Servlet implementation class CartPageController
 */
@WebServlet(urlPatterns = { "/gio-hang" })
public class CartPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartPageController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("null")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action = request.getParameter("action");
		if (action.equals("add")) {
			Cookie[] cookies = request.getCookies();
			String txt = "";
			if (cookies != null) {
				for (Cookie c : cookies) {
					if (c.getName().equals("cart")) {
						txt += c.getValue();
						//c.setMaxAge(0);
						response.addCookie(c);
					}
				}
			}

			String buyQuantity = request.getParameter("buyQuantity");
			String productDetailId = request.getParameter("productDetailId");

			int productId = ProductDetailDAO.getInstance().selectById(Integer.parseInt(productDetailId)).getProduct().getId();
			if(txt.isEmpty() || txt.equals("")) {
				txt = productDetailId+":"+buyQuantity;
			}else {
				txt = txt + "/" + productDetailId + ":" + buyQuantity;
			}

			Cookie c = new Cookie("cart", txt);
			
			c.setMaxAge(60 * 60 * 24 * 30 * 6);
			response.addCookie(c);
			//lấy cart để hiển thị số lượng trên header
			Cart.setCartAttribute(request, response,c);
			
			
			
			
			request.setAttribute("proId", String.valueOf(productId));
			request.getRequestDispatcher("/chi-tiet-san-pham").forward(request, response);
		}
		//XỬ LÝ XEM GIỎ HÀNG
		else if (action.equals("show")) {
			List<ProductDetail> productDetailList = ProductDetailDAO.getInstance().selectAll();
			Cookie[] cookies = request.getCookies();
			String txt = "";
			if (cookies != null) {
				for (Cookie c : cookies) {
					if (c.getName().equals("cart")) {
						txt += c.getValue();
						response.addCookie(c);
					}
				}
			}
			Cart cart = new Cart(txt, productDetailList);
			request.setAttribute("cart", cart);

			
			request.getRequestDispatcher("views/web/cart-page.jsp").forward(request, response);
		}
		//XỬ LÝ THAY ĐỔI SỐ LƯỢNG
		
		else if(action.equals("quantity_change")) {
			
			
			List<ProductDetail> productDetailList = ProductDetailDAO.getInstance().selectAll();
			Cookie[] cookies = request.getCookies();
			String txt = "";
			if (cookies != null) {
				for (Cookie c : cookies) {
					if (c.getName().equals("cart")) {
						txt += c.getValue();
						response.addCookie(c);
					}
				}
			}
			Cart cart = new Cart(txt, productDetailList);
			
			String productDetailId_raw = request.getParameter("productDetailId");
			String num_raw= request.getParameter("num");
			int productDetailId;
			int num = 0;
			try {
				productDetailId = Integer.parseInt(productDetailId_raw);
				ProductDetail productDetail = ProductDetailDAO.getInstance().selectById(productDetailId);
				Product product  = ProductDAO.getInstance().selectById(productDetail.getProduct().getId());
				int availableQuantity = productDetail.getQuantity();
				//System.out.println(availableQuantity + productDetail.getSize() + productDetail.getColor());
				num= Integer.parseInt(num_raw);
				if(num== -1 && cart.getQuantityById(productDetailId) <= 1) {
					cart.removeItems(productDetailId);
				}else {
					if(num==1 && cart.getQuantityById(productDetailId) >= availableQuantity) {
						num = 0;
					}
					CartItem cartItem = new CartItem(product, productDetail, num, product.getPrice());
					cart.addItem(cartItem);
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			
			txt =Cart.cartToCookieTxt(cart);
			Cookie cookie = new Cookie("cart", txt);
			cookie.setMaxAge(60*60*24*30*6);
			response.addCookie(cookie);
			request.setAttribute("cart",cart);
			request.getRequestDispatcher("/views/web/cart-page.jsp").forward(request, response);
			
		}else if(action.equals("process")) {
			List<ProductDetail> productDetailList = ProductDetailDAO.getInstance().selectAll();
			Cookie[] cookies = request.getCookies();
			String txt = "";
			if (cookies != null) {
				for (Cookie c : cookies) {
					if (c.getName().equals("cart")) {
						txt += c.getValue();
						response.addCookie(c);
					}
				}
			}
			Cart cart = new Cart(txt, productDetailList);
			
			String selectedColor = request.getParameter("selectedColor");
			String selectedSize = request.getParameter("selectedSize");
			String productDetailId_raw =request.getParameter("productDetailId");
			String quantity_raw = request.getParameter("quantity");
			if(productDetailId_raw!= null || !productDetailId_raw.isEmpty()) {
				try {
					int productDetailId = Integer.parseInt(productDetailId_raw);
					int quantity = Integer.parseInt(quantity_raw);
					ProductDetail productDetail = ProductDetailDAO.getInstance().selectById(productDetailId);
					Product product = ProductDAO.getInstance().selectById(productDetail.getProduct().getId());
					
					  
					 
					ProductDetail newProductDetail = ProductDetailDAO.getInstance().selectByColorAndSize(selectedColor, selectedSize, product.getId());
					if(newProductDetail == null) {
						newProductDetail = new ProductDetail(productDetail.getId(), product, selectedSize, 0, null, selectedColor);
					}
					
					CartItem cartItem = new CartItem(product, newProductDetail, quantity, product.getPrice());
					cart.updateItem(productDetailId, cartItem);
					txt = Cart.cartToCookieTxt(cart);
					Cookie cookie = new Cookie("cart", txt);
					cookie.setMaxAge(60*60*24*30*6);
					response.addCookie(cookie);
					request.setAttribute("cart",cart);
					request.getRequestDispatcher("/views/web/cart-page.jsp").forward(request, response);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}
			
		}else if(action.equals("delete")) {
			String deleteCartItemId_raw = request.getParameter("deleteCartItemId");
			List<ProductDetail> productDetailList = ProductDetailDAO.getInstance().selectAll();
			Cookie[] cookies = request.getCookies();
			String txt = "";
			if (cookies != null) {
				for (Cookie c : cookies) {
					if (c.getName().equals("cart")) {
						txt += c.getValue();
						response.addCookie(c);
					}
				}
			}
			Cart cart = new Cart(txt, productDetailList);
			try {
				
				if(deleteCartItemId_raw != null || !deleteCartItemId_raw.isEmpty()) {
					int deleteCartItemId = Integer.parseInt(deleteCartItemId_raw);
					cart.removeItems(deleteCartItemId);
					txt = Cart.cartToCookieTxt(cart);
					Cookie cookie = new Cookie("cart", txt);
					cookie.setMaxAge(60*60*24*30*6);
					response.addCookie(cookie);
					request.setAttribute("cart",cart);
					request.getRequestDispatcher("/views/web/cart-page.jsp").forward(request, response);
					
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			
		}else if(action.equals("delete-all")) {
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie c : cookies) {
					if (c.getName().equals("cart")) {
						c.setValue("");
						response.addCookie(c);
						
					}
				}
			}
			Cart cart = new Cart();
			
			request.setAttribute("cart",cart);
			request.getRequestDispatcher("/views/web/cart-page.jsp").forward(request, response);
			
		}
		ProductLikeHandle.setLikedProductCountAttribute(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
	
	

}
