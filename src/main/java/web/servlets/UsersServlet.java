package web.servlets;

import domain.UserDetails;
import repository.ApplicationUserRepository;
import repository.HsqlUserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {

	private ApplicationUserRepository repository;

	public void init() {
		repository = new HsqlUserRepository();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		List<UserDetails> users = repository.getAll();
		StringBuilder usersString = new StringBuilder();
		for (UserDetails user : users) {
			usersString.append(user.getUsername())
					.append(" ")
					.append(user.getRole())
					.append("<br/>");
		}

		resp.setContentType("text/html");
		resp.getWriter().print(usersString.toString());
	}
}
