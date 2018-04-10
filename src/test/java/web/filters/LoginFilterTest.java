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
public class LoginFilterTest {

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
	private LoginFilter filter;

	@Before
	public void init() {
		initMocks(this);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("username")).thenReturn(null);
	}

	@Test
	public void shouldRedirectWhenEmptyParameter() throws IOException, ServletException {
		when(request.getParameter("username")).thenReturn("");

		filter.doFilter(request, response, filterChain);

		verify(response).sendRedirect("/");
	}

	@Test
	public void shouldRedirectWhenNullParameter() throws IOException, ServletException {
		when(request.getParameter("password")).thenReturn(null);

		filter.doFilter(request, response, filterChain);

		verify(response).sendRedirect("/");
	}

	@Test
	public void shouldWriteInfoWhenWrongUsername() throws IOException, ServletException {
		when(request.getParameter("username")).thenReturn("username");
		when(request.getParameter("password")).thenReturn("password");
		when(repository.findByUsername(anyString())).thenReturn(null);
		when(response.getWriter()).thenReturn(writer);

		filter.doFilter(request, response, filterChain);

		verify(writer).print(anyString());
	}

	@Test
	public void shouldWriteInfoWhenWrongPassword() throws IOException, ServletException {
		ApplicationUser user = new ApplicationUser();
		user.setUsername("username");
		user.setPassword("pass");

		when(request.getParameter("username")).thenReturn("username");
		when(request.getParameter("password")).thenReturn("password");
		when(repository.findByUsername("username")).thenReturn(user);
		when(response.getWriter()).thenReturn(writer);

		filter.doFilter(request, response, filterChain);

		verify(writer).print(anyString());
	}

	@Test
	public void shouldSaveUserInSession() throws IOException, ServletException {
		ApplicationUser user = new ApplicationUser();
		user.setUsername("username");
		user.setPassword("password");

		when(request.getParameter("username")).thenReturn("username");
		when(request.getParameter("password")).thenReturn("password");
		when(repository.findByUsername("username")).thenReturn(user);
		when(request.getSession()).thenReturn(session);

		filter.doFilter(request, response, filterChain);

		verify(session).setAttribute("username", "username");
		verify(session).setAttribute("user", user);
	}

}
