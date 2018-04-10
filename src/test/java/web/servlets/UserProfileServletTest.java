package web.servlets;

import domain.ApplicationUser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import repository.HsqlUserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class UserProfileServletTest {

	private ApplicationUser user;

	@Mock
	private HttpServletRequest request;

	@Mock
	private HttpServletResponse response;

	@Mock
	private HttpSession session;

	@Mock
	private PrintWriter writer;

	@InjectMocks
	private UserProfileServlet servlet;

	@Before
	public void init() throws IOException {
		initMocks(this);
		user = new ApplicationUser();
		user.setUsername("username");
		user.setEmail("email");

		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(user);
		when(response.getWriter()).thenReturn(writer);
	}

	@Test
	public void writeUserDetails() throws ServletException, IOException {
		user.setRole("USER");

		servlet.doGet(request, response);

		verify(writer).print(user.getUsername() + " " + user.getEmail() + " " + user.getRole());
	}

	@Test
	public void showAddPremiumLinkWhenAdmin() throws ServletException, IOException {
		user.setRole("ADMIN");

		servlet.doGet(request, response);

		verify(writer).print("<a href=\"admin.jsp\"> Nadaj uprawnienia premium</a>");
	}

}
