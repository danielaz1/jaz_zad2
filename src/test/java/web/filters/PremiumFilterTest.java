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
public class PremiumFilterTest {

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
	private PremiumFilter filter;

	@Before
	public void init() {
		initMocks(this);
	}

	@Test
	public void shouldWriteInfoWhenNotPremium() throws IOException, ServletException {
		ApplicationUser user = new ApplicationUser();
		user.setRole("USER");

		when(response.getWriter()).thenReturn(writer);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(user);

		filter.doFilter(request, response, filterChain);

		verify(writer).print(anyString());
	}

	@Test
	public void shouldWriteInfoWhenNoUserInSession() throws IOException, ServletException {
		when(response.getWriter()).thenReturn(writer);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(null);

		filter.doFilter(request, response, filterChain);

		verify(writer).print(anyString());
	}

	@Test
	public void shouldDoFilterWhenAdmin() throws IOException, ServletException {
		ApplicationUser user = new ApplicationUser();
		user.setRole("ADMIN");

		when(response.getWriter()).thenReturn(writer);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(user);

		filter.doFilter(request, response, filterChain);

		verify(filterChain).doFilter(request, response);
	}

	@Test
	public void shouldDoFilterWhenPremiumUser() throws IOException, ServletException {
		ApplicationUser user = new ApplicationUser();
		user.setRole("PREMIUM");

		when(response.getWriter()).thenReturn(writer);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("user")).thenReturn(user);

		filter.doFilter(request, response, filterChain);

		verify(filterChain).doFilter(request, response);
	}

}
