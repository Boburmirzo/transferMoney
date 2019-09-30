package home;

import org.junit.After;
import org.junit.Before;

import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import home.app.Application;
import home.model.Account;
import home.resource.requests.CreateAccountRequest;

public abstract class BaseTest {

	WebTarget baseClient = ClientBuilder.newClient().target("http://localhost:8080");
	private Application application;

	@Before
	public void setUpBaseTest() throws Exception {
		application = new Application();
		application.run();
	}

	@After
	public void tearDownBaseTest() throws Exception {
		application.shoutDown();
	}

	Account createAccount(long initialBalance) {
		return baseClient
				.path("accounts")
				.request()
				.post(Entity.entity(new CreateAccountRequest(initialBalance),
						MediaType.APPLICATION_JSON), Account.class);
	}

	Account getAccountById(String id) {
		return baseClient
				.path("accounts/" + id)
				.request(MediaType.APPLICATION_JSON)
				.get(Account.class);
	}


	List<Account> getAccounts() {
		return baseClient
				.path("accounts")
				.request(MediaType.APPLICATION_JSON)
				.get()
				.readEntity(new GenericType<List<Account>>() {
				});
	}

}
