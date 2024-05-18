package controller.admin;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.RevenueDAO;
import model.MyItem;

@WebServlet(urlPatterns = {"/admin-home"})
public class HomeController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private RevenueDAO revenueDAO = new RevenueDAO();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// Ngay hien tai
		Date currentDate = new Date();
		
		// Doanh thu 1 tuan vua roi
		List<MyItem> receipts = revenueDAO.reportReceipt(currentDate, 7);
        req.setAttribute("receipts", receipts);	
        
        // Doanh thu ngay hom nay
		List<MyItem> receipt = revenueDAO.reportReceipt(currentDate, 1);
		int revenueOfDayRaw = 0; 
		for (MyItem myItem : receipt) {
			revenueOfDayRaw = myItem.getValue();
		}
		String revenueOfDay = revenueDAO.formatRevenue(revenueOfDayRaw);
        req.setAttribute("revenueOfDay", revenueOfDay);	
        
        // Doanh thu thang truoc
        int lastRevenue = revenueDAO.revenueOfLastMonth(currentDate);
        String revenueOfLastMonth = revenueDAO.formatRevenue(lastRevenue);
        req.setAttribute("revenueOfLastMonth", revenueOfLastMonth);	
        
     
        // Doanh thu thang hien tai
        int currentRevenue = revenueDAO.revenueOfCurrentMonth(currentDate);
        
        // Tang truong doanh thu thang truoc
        double revenueGrowth = 0;
        if (lastRevenue != 0){
        	revenueGrowth = ((double)(currentRevenue - lastRevenue) / lastRevenue) * 100;
        }      
        req.setAttribute("revenueGrowth", revenueGrowth);	
        
        // So don hang trong ngay
        
        int orderOfDate = revenueDAO.countOrderByDate(currentDate);
        
        req.setAttribute("orderOfDate", orderOfDate);	
        RequestDispatcher rq = req.getRequestDispatcher("/views/admin/home.jsp");
		rq.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
	
	
}
