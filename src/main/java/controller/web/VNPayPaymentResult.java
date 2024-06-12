package controller.web;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Utils.SessionUtil;
import constant.SystemConstant;
import dao.OnlinePaymentDAO;
import dao.OrderDAO;
import dao.OrderDetailDAO;
import dao.ProductDetailDAO;
import model.Cart;
import model.CartItem;
import model.OnlinePayment;
import model.Order;
import model.OrderDetail;
import model.ProductDetail;
import model.User;

/**
 * Servlet implementation class VNPayPaymentResult
 */
@WebServlet(urlPatterns = { "/vnpay-payment-result"})
public class VNPayPaymentResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VNPayPaymentResult() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String paymentResultCode = request.getParameter("vnp_TransactionStatus");
		String PaymentTransactionNo = request.getParameter("vnp_TransactionNo");

		
		String result = "";
		if(paymentResultCode!= null && paymentResultCode.equals("00")) {
			String amount = request.getParameter("vnp_Amount");
			String transactionDate = request.getParameter("vnp_PayDate");
			Order o = (Order)SessionUtil.getInstance().getValue(request, "ORDER");

			User u =(User) SessionUtil.getInstance().getValue(request, "USER");

			o.setStatus(SystemConstant.SHIPPING);
			OrderDAO.getInstance().insert(o);
			model.Order order = OrderDAO.getInstance().selectLastestOrderOfUser(u.getId());
			System.out.println("test: " + order.toString());
			String createdBy = order.getUser().getFullname();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	        
			result = "Giao dịch thành công";
			OnlinePayment onlPayment = new OnlinePayment(order,Integer.parseInt(amount) ,PaymentTransactionNo,LocalDateTime.parse(transactionDate,formatter),createdBy);
			OnlinePaymentDAO.getInstance().insert(onlPayment);
			
			List<ProductDetail> productDetailList = ProductDetailDAO.getInstance().selectAll();
			
			
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
			}
				
//				
		}else {
			result = "Giao dịch thất bại";
			SessionUtil.getInstance().removeValue(request, "ORDER");
		}
		request.setAttribute("transactionNo", PaymentTransactionNo);
		request.setAttribute("paymentResult", result);
		request.getRequestDispatcher("/views/web/vnpay_return.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
