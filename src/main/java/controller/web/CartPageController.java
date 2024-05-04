package controller.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dal.ProductDetailDAO;
import model.Cart;
import model.CartItem;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String action =  request.getParameter("action");
		if(action.equals("add")) {
			Cookie[] cookies = request.getCookies();
			String txt = "";
			if(cookies != null) {
				for(Cookie c : cookies) {
					if(c.getName().equals("cart")) {
						txt+= c.getValue();
						c.setMaxAge(0);
						response.addCookie(c);
					}
				}
			}
			
			String buyQuantity = request.getParameter("buyQuantity");
			String productDetailId = request.getParameter("productDetailId");
			int productId = ProductDetailDAO.getInstance().selectById(Integer.parseInt(productDetailId)).getProductId();
			if(txt.isEmpty() || txt.equals("")) {
				txt = productDetailId+":"+buyQuantity;
			}else {
				txt = txt + "/" + productDetailId + ":" + buyQuantity;
			}
			
			Cookie c = new Cookie("cart", txt);
			c.setMaxAge(60*60*24*30*6);
			response.addCookie(c);
			
			
//			Cart cart = new Cart(txt, productDetailList);
//			List<CartItem> cartItems = cart.getItems();
//			
//			int cartItemNum;
//			if(cartItems!= null) {
//				cartItemNum = cartItems.size();
//			}else {
//				cartItemNum = 0;
//			}
//			request.setAttribute("cartItemNum", cartItemNum);
			request.setAttribute("proId",String.valueOf(productId));
			request.getRequestDispatcher("/chi-tiet-san-pham").forward(request, response);
		}
		else if(action.equals("show")) {
			List<ProductDetail> productDetailList = ProductDetailDAO.getInstance().selectAll();
			Cookie[] cookies = request.getCookies();
			String txt = "";
			if(cookies != null) {
				for(Cookie c : cookies) {
					if(c.getName().equals("cart")) {
						txt+= c.getValue();
						c.setMaxAge(0);
						response.addCookie(c);
					}
				}
			}
			Cart cart = new Cart(txt,productDetailList);
			request.setAttribute("cart", cart);
			request.getRequestDispatcher("views/web/cart-page.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
