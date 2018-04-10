package web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/", "/login", "/signup", "signup.jsp"})
public class UserInSessionFilter implements Filter {

	public void init(FilterConfig filterConfig) {

	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();

		if (session.getAttribute("username") != null) {
			response.sendRedirect("/profile");
			return;
		}
		filterChain.doFilter(servletRequest, servletResponse);
	}

	public void destroy() {

	}
}
