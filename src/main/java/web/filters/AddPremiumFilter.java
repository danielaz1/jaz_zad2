package web.filters;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/addPremium")
public class AddPremiumFilter implements Filter {

	public void init(FilterConfig filterConfig) {
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		if (StringUtils.isEmpty(request.getParameter("username"))) {
			response.getWriter().print("Nie uzupelniono pola");
			return;
		}
		filterChain.doFilter(servletRequest, servletResponse);
	}

	public void destroy() {

	}
}
