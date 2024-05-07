package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Utils.SessionUtil;
import constant.SystemConstant;
import model.User;

public class AuthorizationFilter implements Filter	{

	
	private ServletContext context;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.context = filterConfig.getServletContext();
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String url = request.getRequestURI();
		if(url.contains("/admin")) {
			User user = (User)SessionUtil.getInstance().getValue(request,"USER");
			if(user != null) {
				if(user.getRole() == SystemConstant.ADMIN) {
					filterChain.doFilter(servletRequest, servletResponse);

				}else if(user.getRole() == SystemConstant.CUSTOMER ||user.getRole() == SystemConstant.STAFF  ) {

					
					response.sendRedirect(request.getContextPath() + "/trang-chu");
					
				}
			}else {
				response.sendRedirect(request.getContextPath() + "/dang-nhap?action=login&message=not_login_yet");
				System.out.println("not_login_yet");
			}
		}else {
			filterChain.doFilter(servletRequest, servletResponse);
		}
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
