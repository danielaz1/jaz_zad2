package web.filters;

import domain.ApplicationUser;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/premium.jsp")
public class PremiumFilter implements Filter {

	public void init(FilterConfig filterConfig) {
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();

		ApplicationUser user = (ApplicationUser) session.getAttribute("user");
		if (user == null || ! StringUtils.containsAny(user.getRole(), "PREMIUM", "ADMIN")) {
			response.getWriter().print("Tylko uzytkownik premium ma dostep do strony");
			return;
		}
		filterChain.doFilter(servletRequest, servletResponse);
	}

	public void destroy() {

	}
}
