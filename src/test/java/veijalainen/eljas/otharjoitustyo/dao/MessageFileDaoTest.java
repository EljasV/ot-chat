package veijalainen.eljas.otharjoitustyo.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import veijalainen.eljas.otharjoitustyo.domain.Message;
import veijalainen.eljas.otharjoitustyo.domain.User;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

public class MessageFileDaoTest {
	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();
	private File file;


	@Before
	public void setUp() throws Exception {
		file = temporaryFolder.newFile("messageTest.txt");
	}

	@Test
	public void constructorCalled() {
		MessageFileDao messageFileDao = new MessageFileDao(file.getAbsolutePath());
		assertTrue(messageFileDao.working);
	}

	@Test
	public void historyWorks() {
		MessageFileDao messageFileDao = new MessageFileDao(file.getAbsolutePath());
		Message message1 = new Message("Jonne", "Leena", "moi!", 0);
		Message message2 = new Message("Leena", "Jonne", "Heippa!", 1);

		messageFileDao.sendMessage(message1);
		messageFileDao.sendMessage(message2);

		List<Message> history = messageFileDao.getMessageHistory("Jonne", "Leena");

		assertTrue(history.contains(message1));
		assertTrue(history.contains(message2));
	}

	@Test
	public void savingWorks() {
		Message message = new Message("Aino", "Liisa", "abcd", 0);
		{
			MessageFileDao messageFileDao = new MessageFileDao(file.getAbsolutePath());
			messageFileDao.sendMessage(message);
		}
		{
			MessageFileDao messageFileDao = new MessageFileDao(file.getAbsolutePath());
			List<Message> history = messageFileDao.getMessageHistory("Aino", "Liisa");
			assertTrue(history.contains(message));
		}
	}

	@After
	public void tearDown() throws Exception{
		file.delete();
	}
}