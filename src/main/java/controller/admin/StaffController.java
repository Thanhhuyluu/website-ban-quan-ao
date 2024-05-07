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
import dao.UserDAO;
import model.User;


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
        List<User> staffs = staffDAO.selecteByRole(SystemConstant.STAFF);
        request.setAttribute("staffs", staffs);
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
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
 
        User staff = new User();
        staff.setId(id);
        staffDAO.delete(staff);
        response.sendRedirect("admin-staff");
 
    }
}
