package web.filters;

import domain.ApplicationUser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

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
public class AdminFilterTest {

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

	@InjectMocks
	private AdminFilter filter;

	@Before
	public void init() throws IOException {
		initMocks(this);
		when(request.getSession()).thenReturn(session);
		when(response.getWriter()).thenReturn(writer);
	}

	@Test
	public void shouldWriteInfoWhenNotAdmin() throws IOException, ServletException {
		ApplicationUser user = new ApplicationUser();
		user.setRole("USER");

		when(session.getAttribute("user")).thenReturn(user);

		filter.doFilter(request, response, filterChain);

		verify(writer).print(anyString());
	}

	@Test
	public void shouldWriteInfoWhenNoUserInSession() throws IOException, ServletException {
		when(session.getAttribute("user")).thenReturn(null);

		filter.doFilter(request, response, filterChain);

		verify(writer).print(anyString());
	}

	@Test
	public void shouldDoFilterWhenAdmin() throws IOException, ServletException {
		ApplicationUser user = new ApplicationUser();
		user.setRole("ADMIN");

		when(session.getAttribute("user")).thenReturn(user);

		filter.doFilter(request, response, filterChain);

		verify(filterChain).doFilter(request, response);
	}

}
