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
import dao.ProductDAO;
import dao.UserDAO;
import model.Order;
import model.OrderItem;
import model.Product;
import model.ProductItem;
import service.OrderManager;
import service.ProductManager;

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
			String searchKey = request.getParameter("txtSearch");
			String indexPage = request.getParameter("index");
			if(indexPage == null) {
				indexPage = "1";
			}
			int index = Integer.parseInt(indexPage);
			int count = OrderDAO.getInstance().getCountTotal();
			int endPage = count/5;
			if(count % 5 != 0) {
				endPage++;
			}
			
			List<Order> orders = OrderDAO.getInstance().pagingAcount(index);
			if(searchKey != null) {
				orders = OrderDAO.getInstance().searchByKey(orders, searchKey);
			}
			List<OrderItem> lOrderItems = OrderManager.getInstance().oders2OrderItems(orders);
			request.setAttribute("lOrderItems", lOrderItems);
			request.setAttribute("endPage", endPage);
			request.setAttribute("tag", index);
			request.setAttribute("txtSearch", searchKey);
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
