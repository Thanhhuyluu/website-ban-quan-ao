package controller.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Utils.Paging;
import Utils.SessionUtil;
import constant.SystemConstant;
import dao.OrderDAO;
import model.Cart;
import model.Order;
import model.Product;
import model.User;

/**
 * Servlet implementation class OrderPageController
 */
@WebServlet(urlPatterns = {"/don-hang"})
public class OrderPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderPageController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ProductLikeHandle.setLikedProductCountAttribute(request, response);
		String action = request.getParameter("action");
		//lấy cart để hiển thị số lượng trên header
				Cart.setCartAttribute(request, response, null);
		if(action.equals("xem-tat-ca")) {
			if(SessionUtil.getInstance().getValue(request, "USER") == null) {
				response.sendRedirect(request.getContextPath() + "/dang-nhap?action=login&message=not_login_yet");
				
			}else {
				User u =(User) SessionUtil.getInstance().getValue(request, "USER");
				List<Order> userOrderList = OrderDAO.getInstance().selectByUserId(u.getId());
				
				
				
				int page,numPerPage = 4;
				int size = userOrderList.size();
				int pageNum = size%numPerPage == 0 ? (size /numPerPage) : (size /numPerPage + 1);
				String xpage = request.getParameter("page");
				if(xpage == null) {
					page = 1;
				}else {
					page = Integer.parseInt(xpage);
					
				}
				
				int start, end;
				start = (page-1) * numPerPage;
				end = Math.min(page * numPerPage, size);
				Paging<Order> pagingInstance = Paging.getInstance();
				List<Order> list = pagingInstance.getListByPage(userOrderList, start, end);
				
				

				request.setAttribute("page", page);
				request.setAttribute("pageNum", pageNum);
				request.setAttribute("userOrderList", list);
				request.getRequestDispatcher("/views/web/order-page.jsp").forward(request, response);
			}
		}else if(action.equals("huy")) {
			String orderId_raw = request.getParameter("orderId");
			int orderId;
			try {
				orderId = Integer.parseInt(orderId_raw);
			} catch (Exception e) {
				orderId = -1;
			}
			if(orderId!= -1) {
				Order o = OrderDAO.getInstance().selectById(orderId);
				o.setStatus(SystemConstant.CANCELED);
				OrderDAO.getInstance().update(o);
				request.getRequestDispatcher("don-hang?action=xem-tat-ca").forward(request, response);
			}
			
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
