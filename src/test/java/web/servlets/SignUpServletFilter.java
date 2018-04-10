package web.servlets;

import domain.ApplicationUser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import repository.HsqlUserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class SignUpServletFilter {

	@Mock
	private HttpServletRequest request;

	@Mock
	private HttpServletResponse response;

	@Mock
	private HsqlUserRepository repository;

	@Mock
	private PrintWriter writer;

	@InjectMocks
	private SignUpServlet servlet;

	@Before
	public void init() throws IOException {
		initMocks(this);

		when(request.getParameter("username")).thenReturn("username");
		when(request.getParameter("password")).thenReturn("password");
		when(request.getParameter("email")).thenReturn("email");

		when(response.getWriter()).thenReturn(writer);
	}

	@Test
	public void saveUser() throws IOException {
		servlet.doPost(request, response);

		verify(repository).add(any(ApplicationUser.class));
	}

	@Test
	public void writeInfoAboutSuccessfulRegistration() throws IOException {
		servlet.doPost(request, response);

		verify(writer).print(anyString());
	}

}
