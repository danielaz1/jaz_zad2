package web.servlets;

import repository.ApplicationUserRepository;
import repository.HsqlUserRepository;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addPremium")
public class AddPremiumServlet extends HttpServlet {

	private ApplicationUserRepository repository;

	public void init() {
		repository = new HsqlUserRepository();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		if (req.getParameter("premium").equals("add")) {
			repository.updateToPremium(req.getParameter("username"));

		} else {
			repository.removePremium(req.getParameter("username"));
		}

		resp.sendRedirect("/users");
	}
}
