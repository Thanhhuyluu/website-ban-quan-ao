package controller.web;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Utils.SessionUtil;
import constant.SystemConstant;
import dao.CategoryDAO;
import dao.ProductDAO;
import dao.UserDAO;
import model.Category;
import model.Product;
import model.User;
import service.ProductManager;
import service.UserManager;

/**
 * Servlet implementation class HomeController
 */
@WebServlet(urlPatterns = {"/trang-chu", "/dang-nhap", "/thoat", "/dang-ki"})
public class HomeAllController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeAllController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ResourceBundle resourceBundle =  ResourceBundle.getBundle("message");
		String action = request.getParameter("action");
		if(action != null &&action.equals("logout")) {
			System.out.println("enter logout");
			SessionUtil.getInstance().removeValue(request, "USER");
			response.sendRedirect(request.getContextPath() + "/trang-chu");
			
		}else {
			
			String genderRaw = request.getParameter("gender");
			String cateIdRaw = request.getParameter("cateId");
			String saleoffRaw=request.getParameter("saleoff");
			String searchKey = request.getParameter("searchKey");
			List<Product> rawList = null;
			
			//xử lý login form
			Cookie[] cArr= request.getCookies();
			if(cArr!= null) {
				for(Cookie c : cArr) {
					if(c.getName().equals("passC")) {
						request.setAttribute("userPassword", c.getValue());
					}else if(c.getName().equals("emailOrPhoneC")) {
						request.setAttribute("userEmailOrPhone", c.getValue());
					}
				}
			}
			
			
			if(action!= null && action.equals("login")){
					String message = request.getParameter("message");
					if(message != null) {
						System.out.print(message);
						request.setAttribute("alert", resourceBundle.getString(message));
					}
					
			}else if(action != null && action.equals("register")) {
				String[] message = request.getParameterValues("message");
				if(message != null) {
					
					for(String mes: message) {
						if(mes.equals("register_email_error")) {
							request.setAttribute("isInvalidEmail", resourceBundle.getString(mes));
						}else if(mes.equals("register_password_error")) {
							request.setAttribute("isInvalidPassword", resourceBundle.getString(mes));
						}else if(mes.equals("register_phonenumber_error")) {
							request.setAttribute("isInvalidPhoneNumber", resourceBundle.getString(mes));
						}else if(mes.equals("register_account_exist")) {
							request.setAttribute("isExistAccount", resourceBundle.getString(mes));
						}
					}
					
					
					
//					if(message.equals("register_email_error")) {
//						request.setAttribute("isInvalidEmail", resourceBundle.getString(message));
//					}else if(message.equals("register_password_error")) {
//						request.setAttribute("isInvalidPassword", resourceBundle.getString(message));
//					}else if(message.equals("register_phonenumber_error")) {
//						request.setAttribute("isInvalidPhoneNumber", resourceBundle.getString(message));
//					}else if(message.equals("register_account_exist")) {
//						request.setAttribute("isExistAccount", resourceBundle.getString(message));
//					}
				}
			}
			
			ProductDAO pd = ProductDAO.getInstance();
			if(cateIdRaw == null ) {
				
				rawList = pd.selectAll();
			}
			else {
				
				 int cateId[] =  new int[] { Integer.parseInt(cateIdRaw)};
				 rawList = pd.selectByCateId(cateId);
			 
			}
			
			
			
			
			if(genderRaw != null) {
				int gender = Integer.parseInt(genderRaw);
				
				rawList = ProductManager.getInstance().genderFilter(rawList, gender);
				
				request.setAttribute("gender", gender);
				
			}
			if(saleoffRaw != null) {
				rawList = ProductManager.getInstance().saleOffFilter(rawList);
				request.setAttribute("saleoff", Integer.parseInt(saleoffRaw));
			}
			
			if(searchKey != null) {
				rawList = ProductManager.getInstance().searchByKey(rawList, searchKey);
			}
			
			int page,numPerPage = 9;
			int size = rawList.size();
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
			List<Product> list = pd.getListByPage(rawList, start, end);
			

			CategoryDAO categoryDao = CategoryDAO.getInstance();
			List<Category> upperList = categoryDao.selectByType(1);
			List<Category> lowerList = categoryDao.selectByType(2);
			
					
			
			
			request.setAttribute("upperList", upperList);
			request.setAttribute("lowerList", lowerList);
			request.setAttribute("list", list);
			request.setAttribute("page", page);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("searchKey", searchKey);
			request.getRequestDispatcher("/views/web/home.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");	
		String action = request.getParameter("action");
			if(action!= null && action.equals("login")) {
				String password = request.getParameter("passwordLogin");
				String emailOrPhoneNumber = request.getParameter("emailOrPhoneNumberLogin");
				String remember= request.getParameter("remember");
				User user = UserDAO.getInstance().seletUserByPasswordAndStatusAndPhoneNumberOrEmail(password, emailOrPhoneNumber, 0);
				if(user!=  null) {
					SessionUtil.getInstance().putValue(request, "USER", user);
					Cookie passC = new Cookie("passC", password);
					Cookie emailOrPhoneC = new Cookie("emailOrPhoneC",emailOrPhoneNumber);
					if(remember != null) {
						passC.setMaxAge(60*60*24*30);
						emailOrPhoneC.setMaxAge(60*60*24*30);
					}else {
						passC.setMaxAge(0);
						emailOrPhoneC.setMaxAge(0);
					}
					response.addCookie(passC);
					response.addCookie(emailOrPhoneC);
					
					if(user.getRole() == SystemConstant.CUSTOMER) {
						response.sendRedirect(request.getContextPath() + "/trang-chu");
					}else if(user.getRole() ==  SystemConstant.ADMIN) {
						response.sendRedirect(request.getContextPath() + "/admin-home");
					}
						
				}else {
					response.sendRedirect(request.getContextPath() + "/dang-nhap?action=login&message=login_infor_invalid");
				}
			}else if(action != null && action.equals("register")) {
				
						request.setCharacterEncoding("UTF-8");
						String fullname = request.getParameter("registerName");
						String email = request.getParameter("registerEmail");
						String phoneNumber = request.getParameter("registerPhoneNumber");
						String password = request.getParameter("registerPassword");
						
						User u = new User(fullname, email, phoneNumber, "", password,   Date.valueOf(LocalDate.now()), null, SystemConstant.USER_NORMAL, SystemConstant.CUSTOMER);
						SessionUtil.getInstance().putValue(request, "registUser", u);
						List<String> userError = UserManager.getInstance().IsValidAccount(u);
						
						if(userError.size() > 0) {
							String url = "/dang-ki?action=register&";
							for(String s : userError) {
								url +="message=" + s + "&";
							}
							url = url.substring(0, url.length()-1);
							response.sendRedirect(request.getContextPath() + url);
						}else {
							UserDAO.getInstance().insert(u);
							SessionUtil.getInstance().putValue(request, "USER", u);
							response.sendRedirect(request.getContextPath() + "/trang-chu");
						}
						
			}
	}
	


}
