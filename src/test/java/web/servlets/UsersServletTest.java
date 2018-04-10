package web.servlets;

import domain.UserDetails;
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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class UsersServletTest {

	@Mock
	private HttpServletRequest request;

	@Mock
	private HttpServletResponse response;

	@Mock
	private PrintWriter writer;

	@Mock
	private HsqlUserRepository repository;

	@InjectMocks
	private UsersServlet servlet;

	@Before
	public void init() {
		initMocks(this);

	}

	@Test
	public void printUserDetails() throws ServletException, IOException {
		UserDetails user = new UserDetails();
		user.setUsername("username");
		user.setRole("USER");

		List<UserDetails> users = new ArrayList<UserDetails>();
		users.add(user);

		when(repository.getAll()).thenReturn(users);
		when(response.getWriter()).thenReturn(writer);

		servlet.doGet(request, response);

		verify(writer).print(user.getUsername() + " " + user.getRole() + "<br/>");
	}

	@Test
	public void printEmptyList() throws IOException {
		List<UserDetails> users = new ArrayList<UserDetails>();

		when(repository.getAll()).thenReturn(users);
		when(response.getWriter()).thenReturn(writer);

		servlet.doGet(request, response);

		verify(writer).print("");
	}

}
