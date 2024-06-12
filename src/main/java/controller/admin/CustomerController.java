package controller.admin;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constant.SystemConstant;
import dao.CategoryDAO;
import dao.UserDAO;
import model.User;


@WebServlet(urlPatterns = {"/admin-customer", "/admin-customer-edit", "/admin-customer-new", "/admin-customer-insert", "/admin-customer-delete", "/admin-customer-update" })
public class CustomerController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	 private UserDAO customerDao = new UserDAO();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

//		CategoryDAO categoryDAO = new CategoryDAO();
//		req.setAttribute("categories", categoryDAO.selectAll());
//		RequestDispatcher rq = req.getRequestDispatcher("/views/admin/category/showCategory.jsp");
//		rq.forward(req, resp);
		req.setCharacterEncoding("UTF-8");
		String action = req.getServletPath();
		System.out.println(action);
        try {
            switch (action) {
            case "/admin-customer-new":
                showNewForm(req, resp);
                break;
            case "/admin-customer-insert":
                insertCustomer(req, resp);
                break;
            case "/admin-customer-delete":
                deleteCustomer(req, resp);
                break;
            case "/admin-customer-edit":
                showEditForm(req, resp);
                break;
            case "/admin-customer-update":
                updateCustomer(req, resp);
                break;
            default:
                listCustomer(req, resp);
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
	
	private void listCustomer(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
		String searchKey = request.getParameter("txtSearch");
		String indexPage = request.getParameter("index");
		if(indexPage == null) {
			indexPage = "1";
		}
		int index = Integer.parseInt(indexPage);
		int count = UserDAO.getInstance().getCountTotalCustomer();
		int endPage = count/5;
		if(count % 5 != 0) {
			endPage++;
		}
		
		List<User> customers = UserDAO.getInstance().pagingAcountCustomer(index);
		
		if(searchKey != null) {
			customers = UserDAO.getInstance().searchByKey(customers, searchKey);
		}
		request.setAttribute("customers", customers);
		request.setAttribute("endPage", endPage);
		request.setAttribute("tag", index);
		request.setAttribute("txtSearch", searchKey);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/customer/customerList.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/customer/customerAdd.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User existingCustomer = customerDao.selectById(id);
        request.setAttribute("customer", existingCustomer);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/customer/customerEdit.jsp");
        dispatcher.forward(request, response);
 
    }
 
    private void insertCustomer(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("fullname");
        String enail = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        String password = "12341234";
        int status = 0; 
        int role = 0;
         
        User newStaff = new User(name, enail, phoneNumber, address, password, new Date(System.currentTimeMillis()), null, status, role);
        customerDao.insert(newStaff);
        response.sendRedirect("admin-customer");
    }
 
    private void updateCustomer(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("fullname");
        String enail = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        String password = "12341234";
        Date createdAt = Date.valueOf(request.getParameter("createdAt"));
        int status = 0; 
        int role = 1;
        User staff = new User(id, name, enail, phoneNumber, address, password, createdAt, new Date(System.currentTimeMillis()), status, role);
        customerDao.update(staff);
        response.sendRedirect("admin-customer");
    }
 
    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        
        User customer = customerDao.selectById(id);
        if (customer.getStatus() == SystemConstant.USER_BANDED) {
        	customer.setStatus(SystemConstant.USER_NORMAL);
		} 
        else if (customer.getStatus() == SystemConstant.USER_NORMAL) {
        	customer.setStatus(SystemConstant.USER_BANDED);
		}
        customerDao.update(customer);
        response.sendRedirect("admin-customer");
        
    }
}
