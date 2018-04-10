package web.servlets;

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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class AddPremiumServletTest {

	@Mock
	private HttpServletRequest request;

	@Mock
	private HttpServletResponse response;

	@Mock
	private HsqlUserRepository repository;

	@InjectMocks
	private AddPremiumServlet servlet;

	@Before
	public void init() {
		initMocks(this);
	}

	@Test
	public void updateToPremium() throws IOException {
		when(request.getParameter("premium")).thenReturn("add");
		when(request.getParameter("username")).thenReturn("username");

		servlet.doPost(request, response);

		verify(repository).updateToPremium("username");
	}

	@Test
	public void removePremium() throws IOException {
		when(request.getParameter("premium")).thenReturn("remove");
		when(request.getParameter("username")).thenReturn("username");

		servlet.doPost(request, response);

		verify(repository).removePremium("username");
	}
}
