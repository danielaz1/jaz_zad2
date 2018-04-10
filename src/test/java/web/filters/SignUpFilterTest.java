package web.filters;

import domain.ApplicationUser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import repository.HsqlUserRepository;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class SignUpFilterTest {

	@Mock
	private HttpServletRequest request;

	@Mock
	private HttpServletResponse response;

	@Mock
	private FilterChain filterChain;

	@Mock
	private PrintWriter writer;

	@Mock
	private HttpSession session;

	@Mock
	private HsqlUserRepository repository;

	@InjectMocks
	private SignUpFilter filter;

	@Before
	public void init() throws IOException {
		initMocks(this);
		when(response.getWriter()).thenReturn(writer);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("username")).thenReturn(null);
	}

	@Test
	public void shouldWriteInfoWhenEmptyParameter() throws IOException, ServletException {
		when(request.getParameter("username")).thenReturn("");

		filter.doFilter(request, response, filterChain);

		verify(writer).print(anyString());
	}

	@Test
	public void shouldWriteInfoWhenNullParameter() throws IOException, ServletException {
		when(request.getParameter("username")).thenReturn(null);

		filter.doFilter(request, response, filterChain);

		verify(writer).print(anyString());
	}

	@Test
	public void shouldWriteInfoWhenDifferentPasswords() throws IOException, ServletException {
		when(request.getParameter("username")).thenReturn("username");
		when(request.getParameter("password")).thenReturn("password");
		when(request.getParameter("confirmpassword")).thenReturn("pass");
		when(request.getParameter("email")).thenReturn("email");

		filter.doFilter(request, response, filterChain);

		verify(writer).print(anyString());
	}

	@Test
	public void shouldWriteInfoWhenUserInDB() throws IOException, ServletException {
		when(request.getParameter("username")).thenReturn("username");
		when(request.getParameter("password")).thenReturn("password");
		when(request.getParameter("confirmpassword")).thenReturn("password");
		when(request.getParameter("email")).thenReturn("email");
		when(repository.findByUsername("username")).thenReturn(new ApplicationUser());

		filter.doFilter(request, response, filterChain);

		verify(writer).print(anyString());
	}

}
