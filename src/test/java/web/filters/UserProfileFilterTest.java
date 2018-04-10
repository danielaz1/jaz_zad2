package web.filters;

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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class UserProfileFilterTest {

	@Mock
	private HttpServletRequest request;

	@Mock
	private HttpServletResponse response;

	@Mock
	private FilterChain filterChain;

	@Mock
	private HttpSession session;

	@InjectMocks
	private UserProfileFilter filter;

	@Before
	public void init() {
		initMocks(this);
		when(request.getSession()).thenReturn(session);
	}

	@Test
	public void shouldRedirectWhenNoUserInSession() throws IOException, ServletException {
		when(session.getAttribute("username")).thenReturn(null);

		filter.doFilter(request, response, filterChain);

		verify(response).sendRedirect("/");
	}

	@Test
	public void shouldDoFilterWhenUserInSession() throws IOException, ServletException {
		when(session.getAttribute("username")).thenReturn("username");

		filter.doFilter(request, response, filterChain);

		verify(filterChain).doFilter(request, response);
	}

}
