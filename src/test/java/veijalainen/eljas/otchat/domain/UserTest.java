package veijalainen.eljas.otchat.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

	User user;

	@Before
	public void setUp() {
		user = new User("aaaaa", "cba321");
	}

	@Test
	public void getUsernameReturnsCorrectName() {
		assertEquals("aaaaa", user.getUsername());
	}

	@Test
	public void getPasswordReturnsCorrectPassword() {
		assertEquals("cba321", user.getPassword());
	}
}