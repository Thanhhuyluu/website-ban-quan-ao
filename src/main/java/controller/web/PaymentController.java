package controller.web;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.x.protobuf.MysqlxCrud.Order;

import Utils.SessionUtil;
import constant.SystemConstant;
import dao.OrderDAO;
import dao.OrderDetailDAO;
import dao.ProductDetailDAO;
import model.Cart;
import model.CartItem;
import model.OrderDetail;
import model.ProductDetail;
import model.User;

/**
 * Servlet implementation class PaymentController
 */
@WebServlet(urlPatterns = { "/thanh-toan","/hoan-tat-dat-hang" })
public class PaymentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");	
		String action = request.getParameter("action");
		if(action.equals("thanh-toan")) {
			
			if(SessionUtil.getInstance().getValue(request, "USER") == null) {
				response.sendRedirect(request.getContextPath() + "/dang-nhap?action=login&message=not_login_yet");
				
			}else {
				User u =(User) SessionUtil.getInstance().getValue(request, "USER");
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
				txt = Cart.cartToCookieTxt(cart);
				Cookie cookie = new Cookie("cart", txt);
				cookie.setMaxAge(60*60*24*30*6);
				response.addCookie(cookie);
				request.setAttribute("cart",cart);
				request.getRequestDispatcher("/views/web/payment-page.jsp").forward(request, response);
				
			}
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");	
		String action = request.getParameter("action");
		if(action != null && action.equals("hoan-tat-dat-hang")) {
			String fullname= request.getParameter("fullname");
			String phoneNumber= request.getParameter("phoneNumber");
			String email= request.getParameter("email");
			String address= request.getParameter("address");
			String province= request.getParameter("province");
			String district= request.getParameter("district");
			String wards= request.getParameter("wards");
			String note= request.getParameter("note");
			String paymentMethod= request.getParameter("paymentMethod");
			User u =(User) SessionUtil.getInstance().getValue(request, "USER");
			model.Order o = new model.Order(u, fullname, email, phoneNumber, address + ","+wards + ","+ district + "," + province,note ,Date.valueOf(LocalDate.now()), SystemConstant.WAIT_FOR_PACKING);
			List<ProductDetail> productDetailList = ProductDetailDAO.getInstance().selectAll();
			
			
			
			
			
			//=====thanh toán bằng COD
			if(paymentMethod.equals("cod")) {
				OrderDAO.getInstance().insert(o);
				
				model.Order order = OrderDAO.getInstance().selectLastestOrderOfUser(u.getId());
				
				if(order != null) {
					Cookie[] cookies = request.getCookies();
					String txt = "";
					if (cookies != null) {
						for (Cookie c : cookies) {
							if (c.getName().equals("cart")) {
								txt += c.getValue();
								c.setValue("");
								response.addCookie(c);
							}
						}
					}
					Cart cart = new Cart(txt, productDetailList);
					for(CartItem i : cart.getItems()) {
						System.out.println(i.getProductDetail().toString());
						float discount = i.getProduct().getDiscount()/100;
						int price =  i.getQuantity() * (i.getProduct().getPrice() - (int)(discount * i.getProduct().getPrice()) );
						OrderDetail od = new OrderDetail(order, i.getProductDetail(), price, i.getQuantity());
						
						OrderDetailDAO.getInstance().insert(od);
					}
					
//					
					
				}
				response.sendRedirect(request.getContextPath() + "/trang-chu");
			}
			
			//=====thanh toán bằng VNPAY
			else if(paymentMethod.equals("vnpay")) {
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
				SessionUtil.getInstance().putValue(request, "ORDER", o);
				request.setAttribute("totalMoney", cart.getTotalMoney());
				request.getRequestDispatcher("/vnpay-payment").forward(request, response);
			}
			
		}
	}

}
