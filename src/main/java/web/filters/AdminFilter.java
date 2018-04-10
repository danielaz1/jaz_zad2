package web.filters;

import domain.ApplicationUser;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"admin.jsp", "/addPremium"})
public class AdminFilter implements Filter {

	public void init(FilterConfig filterConfig) {
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();

		ApplicationUser user = (ApplicationUser) session.getAttribute("user");
		if (user == null || ! user.getRole().equals("ADMIN")) {
			response.getWriter().print("Tylko administrator ma dostep do strony");
			return;
		}
		filterChain.doFilter(servletRequest, servletResponse);

	}

	public void destroy() {

	}
}
