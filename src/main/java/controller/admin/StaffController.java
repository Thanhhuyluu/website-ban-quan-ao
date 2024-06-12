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
import dao.ProductDAO;
import dao.UserDAO;
import model.Product;
import model.ProductItem;
import model.User;
import service.ProductManager;


@WebServlet(urlPatterns = {"/admin-staff", "/admin-staff-edit", "/admin-staff-new", "/admin-staff-insert", "/admin-staff-delete", "/admin-staff-update", "/admin-staff-band" })
public class StaffController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	 private UserDAO staffDAO = new UserDAO();
	
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
            case "/admin-staff-new":
                showNewForm(req, resp);
                break;
            case "/admin-staff-insert":
                insertStaff(req, resp);
                break;
            case "/admin-staff-delete":
                deleteStaff(req, resp);
                break;
            case "/admin-staff-edit":
                showEditForm(req, resp);
                break;
            case "/admin-staff-update":
            	updateStaff(req, resp);
                break;
            case "/admin-staff-band":
            	bandStaff(req, resp);
            	break;
            default:
                listStaff(req, resp);
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
	
	private void listStaff(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
		String searchKey = request.getParameter("txtSearch");
		String indexPage = request.getParameter("index");
		if(indexPage == null) {
			indexPage = "1";
		}
		int index = Integer.parseInt(indexPage);
		int count = UserDAO.getInstance().getCountTotalStaff();
		int endPage = count/8;
		if(count % 8 != 0) {
			endPage++;
		}
		
		List<User> staffs = UserDAO.getInstance().pagingAcountStaff(index);
		if(searchKey != null) {
			staffs = UserDAO.getInstance().searchByKey(staffs, searchKey);
		}
		request.setAttribute("staffs", staffs);
		request.setAttribute("endPage", endPage);
		request.setAttribute("tag", index);
		request.setAttribute("txtSearch", searchKey);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/staff/staffList.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/staff/staffAdd.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User existingStaff = staffDAO.selectById(id);
        request.setAttribute("staff", existingStaff);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/staff/staffEdit.jsp");
        dispatcher.forward(request, response);
 
    }
 
    private void insertStaff(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("fullname");
        String enail = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        String password = "12341234";
        int status = SystemConstant.USER_NORMAL; 
        int role = SystemConstant.STAFF;
        User newStaff = new User(name, enail, phoneNumber, address, password, new Date(System.currentTimeMillis()), null, status, role);
        staffDAO.insert(newStaff);
        System.out.println(newStaff);
        response.sendRedirect("admin-staff");
    }
 
    private void updateStaff(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("fullname");
        String enail = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        String password = "12341234";
        Date createdAt = Date.valueOf(request.getParameter("createdAt"));
        int status = 0; 
        int role = SystemConstant.STAFF;
        User staff = new User(id, name, enail, phoneNumber, address, password, createdAt, new Date(System.currentTimeMillis()), status, role);
        staffDAO.update(staff);
        response.sendRedirect("admin-staff");
    }
 
    
    
    private void bandStaff(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
       
        User staff = staffDAO.selectById(id);
        if (staff.getStatus() == SystemConstant.USER_BANDED) {
        	staff.setStatus(SystemConstant.USER_NORMAL);
		} 
        else if (staff.getStatus() == SystemConstant.USER_NORMAL) {
        	staff.setStatus(SystemConstant.USER_BANDED);
		}
        staffDAO.update(staff);
        response.sendRedirect("admin-staff");
 
    }
    private void deleteStaff(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	
    	int id = Integer.parseInt(request.getParameter("id"));
    	String message = "";
        User staff = new User();
        staff.setId(id);
        if(staffDAO.delete(staff) != 0) {
        	message = "Xoá nhân viên thành công!"; 
        }else {
        	message = "Xoá nhân viên không thành công!";
        }
        request.setAttribute("message", message);
        System.out.println("++" + message);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin-staff");
        dispatcher.forward(request, response);
 
    }
}
