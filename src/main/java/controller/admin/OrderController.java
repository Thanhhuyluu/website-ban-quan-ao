package controller.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderDAO;
import model.Order;
import model.OrderItem;
import service.OrderManager;

@WebServlet(urlPatterns = {"/admin-order", "/admin-order-new", "/admin-order-insert", "/admin-order-deleteSoft", "/admin-order-edit", "/admin-order-update", "/admin-orderDetail", "/admin-order-acceptOrder"})
public class OrderController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	

	
	 private OrderDAO orderDAO = new OrderDAO();
		
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			// TODO Auto-generated method stub

			String action = req.getServletPath();
			req.setCharacterEncoding("UTF-8");
			System.out.println(action);
	        try {
	            switch (action) {
	            case "/admin-order-deleteSoft":
	            	deleteSoftOrder(req, resp);
	                break;
	            case "/admin-orderDetail":
//	            	deleteSoftOrder(req, resp);
	                break;
	            case "/admin-order-acceptOrder":
	            	acceptOrder(req, resp);
	                break;
	            default:
	                listOrder(req, resp);
	                break;
	            }
	        } catch (SQLException ex) {
	            throw new ServletException(ex);
	        }
		}
		
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doGet(req, resp);
		}
		
		private void listOrder(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        List<Order> orders = orderDAO.selectAll();    
	        
	        List<OrderItem> lOrderItems = OrderManager.getInstance().oders2OrderItems(orders);
	        request.setAttribute("lOrderItems", lOrderItems);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/order/orderList.jsp");
	        dispatcher.forward(request, response);
	    }
	 
	    private void deleteSoftOrder(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	    	  int id = Integer.parseInt(request.getParameter("id"));
		        
		        Order order = orderDAO.selectById(id);
		        if (order.getStatus() != 2) {
		        	order.setStatus(3);
			        orderDAO.update(order);
		        }
		        
		        response.sendRedirect("admin-order");
	    }
	    private void acceptOrder(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	    	  int id = Integer.parseInt(request.getParameter("id"));
		        
		        Order order = orderDAO.selectById(id);
		        if(order.getStatus() == 0) {
		        	order.setStatus(1);
			        orderDAO.update(order);
		        }
		        
		        response.sendRedirect("admin-order");
	    }
	 
	    
}
