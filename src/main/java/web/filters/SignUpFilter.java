package web.filters;

import org.apache.commons.lang3.StringUtils;
import repository.ApplicationUserRepository;
import repository.HsqlUserRepository;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/signup")
public class SignUpFilter implements Filter {

	ApplicationUserRepository repository;

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

		if (StringUtils.isEmpty(servletRequest.getParameter("username"))
				|| StringUtils.isEmpty(servletRequest.getParameter("password"))
				|| StringUtils.isEmpty(servletRequest.getParameter("confirmpassword"))
				|| StringUtils.isEmpty(servletRequest.getParameter("email"))) {
			servletResponse.getWriter().print("Nie wypelniono wszystkich pol");
			return;
		}

		if (! servletRequest.getParameter("password").equals(servletRequest.getParameter("confirmpassword"))) {
			servletResponse.getWriter().print("Podane hasla nie zgadzaja sie");
			return;
		}

		if (repository.findByUsername(servletRequest.getParameter("username")) != null) {
			servletResponse.getWriter().print("Uzytkownik o takiej nazwie juz istnieje");
			return;
		}

		filterChain.doFilter(servletRequest, servletResponse);
	}

	public void destroy() {

	}
}
