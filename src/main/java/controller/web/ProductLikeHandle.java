package controller.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProductLikeHandle
 */
@WebServlet(urlPatterns = {"/like-product"})
public class ProductLikeHandle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductLikeHandle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String proId_raw = request.getParameter("productId");
		if(proId_raw != null) {
			//int proId = Integer.parseInt(proId_raw);
			Cookie[] cookies = request.getCookies();
			String txt = "";
			if(cookies != null) {
				for(Cookie c : cookies) {
					if(c.getName().equals("likedProducts"))
						txt+=c.getValue();
				}
			}
			if(txt.isEmpty()) {
				txt+=proId_raw;
			}else {
				String []a = txt.split("/");
				txt="";
				int flat = -1;
				for(int i = 0; i< a.length; i++) {
					if(a[i].equals(proId_raw)) {
						flat = i;
					}
				}
				if(flat != -1) {
					for(int i = flat; i< a.length - 1; i++) {
						a[i] = a[i+1];
					}
					for(int i = 0; i < a.length-1; i++) {
						txt+=a[i] + "/";
					}
					//txt = txt.substring(0,txt.length()-1);
				}else {
					for(int i = 0; i < a.length; i++) {
						txt+=a[i] + "/";
					}
					txt += proId_raw;
				}
			}
			Cookie co = new Cookie("likedProducts",txt);
			response.addCookie(co);
		}
	}
	public static List<Integer> getLikesProducst(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		String txt = "";
		if(cookies != null) {
			for(Cookie c : cookies) {
				if(c.getName().equals("likedProducts"))
					txt+=c.getValue();
			}
		}
		if(!txt.isEmpty()) {
			String a[] = txt.split("/");
			List<Integer> b = new ArrayList<Integer>();
			for(int i = 0; i < a.length; i++) {
				b.add(Integer.parseInt(a[i])) ;
			}
			
			return b;
		}
		return null;
	}

}
