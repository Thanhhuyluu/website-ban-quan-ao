package controller.web;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import Utils.SessionUtil;
import dao.UserDAO;
import model.Address;
import model.Cart;
import model.User;

/**
 * Servlet implementation class PersonalInfor
 */
@WebServlet(urlPatterns = {"/thong-tin-ca-nhan"})
public class PersonalInforController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonalInforController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		String action = request.getParameter("action");
		if(SessionUtil.getInstance().getValue(request, "USER") == null) {
			response.sendRedirect(request.getContextPath() + "/dang-nhap?action=login&message=not_login_yet");
			
		}else {
			Cart.setCartAttribute(request, response, null);
			
			ProductLikeHandle.setLikedProductCountAttribute(request, response);
			if(action  != null && action.equals("change")) {
				
				String message = request.getParameter("message");
				if(message != null) {
					if(message.equals("register_password_error")){
						System.out.println("lỗi password");
						String error = "This password has been used";
						String error_password = request.getParameter("error_password");
						request.setAttribute("error", error);
						System.out.println(error_password);
						request.setAttribute("error_password", error_password);
					}
				}
						
			}
			
			
			request.getRequestDispatcher("/views/web/change-infor-page.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		if(action  != null && action.equals("change")) {
			String fullname = request.getParameter("fullname");
			String password = request.getParameter("password");
			String email = request.getParameter("email");

			String phoneNum = request.getParameter("phoneNumber");
			String address = request.getParameter("address");
			String province = request.getParameter("province");
			String district = request.getParameter("district");
			String wards = request.getParameter("wards");
			
			User user = (User) SessionUtil.getInstance().getValue(request, "USER");
			User u = new User(user.getId(),fullname, email, phoneNum, address+"/" + wards + "/" + district + "/" + province +"/" , password,   user.getCreatedAt(), Date.valueOf(LocalDate.now()), user.getStatus(), user.getRole());
			System.out.println(u.toString());
			List<User> users = UserDAO.getInstance().selectAll();
			int flat = 0;
			for(User us : users) {
				if(us.getPassword().equals(password.trim()) && !us.getEmail().equals(email)) {
					flat = 1;
					System.out.println(email + us.getEmail());
					break;
				}
			}
			if(flat ==1) {
				System.out.println("have error");
				String url = "/thong-tin-ca-nhan?action=change&message=register_password_error&error_password="+password;
				response.sendRedirect(request.getContextPath() + url);
			}else {
				System.out.println("don't have error");
				u.setPassword(password);
				System.out.println(u.getAddress());
				UserDAO.getInstance().update(u);
				
				System.out.println(u.getAddress());
				Address a = new Address(address, province, district, wards);
				SessionUtil.getInstance().putValue(request, "ADDRESS", a);
				SessionUtil.getInstance().putValue(request, "USER", u);
				response.sendRedirect(request.getContextPath() + "/thong-tin-ca-nhan");
			}
			
			
			
			
	}
	}

}
