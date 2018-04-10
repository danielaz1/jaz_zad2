package web.filters;

import domain.ApplicationUser;
import org.apache.commons.lang3.StringUtils;
import repository.ApplicationUserRepository;
import repository.HsqlUserRepository;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/login")
public class LoginFilter implements Filter {

	private ApplicationUserRepository repository;

	public void init(FilterConfig filterConfig) {
		repository = new HsqlUserRepository();
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();

		if (session.getAttribute("username") != null) {
			response.sendRedirect("/profile");
			return;
		}


		if (StringUtils.isEmpty(request.getParameter("username"))
				|| StringUtils.isEmpty(request.getParameter("password"))) {
			response.sendRedirect("/");
			return;
		}

		ApplicationUser user = repository.findByUsername(request.getParameter("username"));

		if (user == null) {
			response.getWriter().print("Nie ma takiego uzytkownika");
			return;
		}

		if (! user.getPassword().equals(request.getParameter("password"))) {
			response.getWriter().print("Nieprawidlowe haslo");
			return;
		}

		session.setAttribute("username", request.getParameter("username"));
		session.setAttribute("user", user);

		filterChain.doFilter(servletRequest, servletResponse);
	}

	public void destroy() {

	}
}
