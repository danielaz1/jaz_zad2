package web.servlets;

import domain.ApplicationUser;
import repository.ApplicationUserRepository;
import repository.HsqlUserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {

	private ApplicationUserRepository repository;

	@Override
	public void init() {
		repository = new HsqlUserRepository();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		repository.add(getUserFromRequest(req));

		resp.getWriter().print("Zarejestrowano");
	}

	private ApplicationUser getUserFromRequest(HttpServletRequest req) {
		ApplicationUser user = new ApplicationUser();
		user.setUsername(req.getParameter("username"));
		user.setPassword(req.getParameter("password"));
		user.setEmail(req.getParameter("email"));
		user.setRole("USER");

		return user;
	}
}
