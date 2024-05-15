package controller.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderDAO;
import dao.OrderDetailDAO;
import model.Cart;
import model.Order;
import model.OrderDetail;

/**
 * Servlet implementation class CartDetailPageController
 */
@WebServlet(urlPatterns = {"/chi-tiet-don-hang"})
public class OrderDetailPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderDetailPageController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//lấy cart để hiển thị số lượng trên header
		Cart.setCartAttribute(request, response, null);
		
		String orderId_raw = request.getParameter("orderId");
		int orderId= -1;
		try {
			orderId= Integer.parseInt(orderId_raw);
		} catch (Exception e) {
			// TODO: handle exception
		}
		Order od = OrderDAO.getInstance().selectById(orderId);
		List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
		if(od != null) {
			 orderDetails = OrderDetailDAO.getInstance().selectByOrderId(od);
		}
		request.setAttribute("orderDetails", orderDetails);
		request.getRequestDispatcher("/views/web/order-detail-page.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
