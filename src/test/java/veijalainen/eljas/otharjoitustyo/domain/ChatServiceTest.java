package veijalainen.eljas.otharjoitustyo.domain;

import org.junit.Before;
import org.junit.Test;
import veijalainen.eljas.otharjoitustyo.dao.MessageMemoryDao;
import veijalainen.eljas.otharjoitustyo.dao.UserMemoryDao;
import veijalainen.eljas.otharjoitustyo.util.Result;

import static org.junit.Assert.*;

public class ChatServiceTest {
	ChatService chatService;

	@Before
	public void setup() {
		chatService = new ChatService(new UserMemoryDao(), new MessageMemoryDao());
	}


	@Test
	public void createUserWithSameUsername() {
		chatService.createUser("Aatami", "cba321", "cba321");
		Result<Void, ChatService.CreateUserErrorCode> result = chatService.createUser("Aatami", "aaaaa", "aaaaa");
		assertFalse(result.success());
		assertEquals(ChatService.CreateUserErrorCode.USERNAME_EXISTS, result.getError());
	}

	@Test
	public void createUserDifferentPassword() {
		Result<Void, ChatService.CreateUserErrorCode> result = chatService.createUser("Bea", "123aaaaa", "321aaaaa");
		assertFalse(result.success());
		assertEquals(ChatService.CreateUserErrorCode.DIFFERENT_PASSWORDS, result.getError());
	}

	@Test
	public void createUserIllegalPassword() {
		Result<Void, ChatService.CreateUserErrorCode> result = chatService.createUser("Charlie", "1", "1");
		assertFalse(result.success());
		assertEquals(ChatService.CreateUserErrorCode.ILLEGAL_PASSWORD, result.getError());
	}

	@Test
	public void createUserSuccessfully() {
		Result<Void, ChatService.CreateUserErrorCode> result = chatService.createUser("Daavid", "a1b2c3d4e5f6g7", "a1b2c3d4e5f6g7");
		assertTrue(result.success());
	}

}