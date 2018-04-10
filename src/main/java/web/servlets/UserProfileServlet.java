package web.servlets;

import domain.ApplicationUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/profile")
public class UserProfileServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		ApplicationUser user = (ApplicationUser) session.getAttribute("user");

		resp.getWriter().print(user.getUsername() + " " + user.getEmail() + " " + user.getRole());

		if (user.getRole().equals("ADMIN")) {
			resp.setContentType("text/html");
			String link = "<a href=\"admin.jsp\"> Nadaj uprawnienia premium</a>";
			resp.getWriter().print(link);
		}
	}
}
