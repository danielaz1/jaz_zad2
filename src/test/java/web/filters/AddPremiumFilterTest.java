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
import java.io.IOException;
import java.io.PrintWriter;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class AddPremiumFilterTest {

	@Mock
	private HttpServletRequest request;

	@Mock
	private HttpServletResponse response;

	@Mock
	private FilterChain filterChain;

	@Mock
	private PrintWriter writer;

	@InjectMocks
	private AddPremiumFilter filter;

	@Before
	public void init() {
		initMocks(this);
	}

	@Test
	public void shouldWriteInfoWhenEmptyParameter() throws IOException, ServletException {
		when(request.getParameter("username")).thenReturn("");
		when(response.getWriter()).thenReturn(writer);

		filter.doFilter(request, response, filterChain);

		verify(writer).print(anyString());
	}

	@Test
	public void shouldWriteInfoWhenNullParameter() throws IOException, ServletException {
		when(request.getParameter("username")).thenReturn(null);
		when(response.getWriter()).thenReturn(writer);

		filter.doFilter(request, response, filterChain);

		verify(writer).print(anyString());
	}

	@Test
	public void shouldDoFilterWhenUsernameProvided() throws IOException, ServletException {
		when(request.getParameter("username")).thenReturn("username");

		filter.doFilter(request, response, filterChain);

		verify(filterChain).doFilter(request, response);
	}

}
