package veijalainen.eljas.otharjoutustyo.domain;

import org.junit.Before;
import org.junit.Test;
import veijalainen.eljas.otharjoutustyo.dao.UserMemoryDao;
import veijalainen.eljas.otharjoutustyo.util.Result;

import static org.junit.Assert.*;

public class ChatServiceTest {
	ChatService chatService;

	@Before
	public void setup() {
		chatService = new ChatService(new UserMemoryDao());
	}


	@Test
	public void createUserWithSameUsername() {
		chatService.createUser("Aatami", "cba321", "cba321");
		Result<Void, ChatService.CreateUserErrorCode> result = chatService.createUser("Aatami", "aaaaa", "aaaaa");
		assertFalse(result.success());
		assertEquals(ChatService.CreateUserErrorCode.USERNAME_EXISTS, result.getError());
	}
}