package veijalainen.eljas.otharjoitustyo.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import veijalainen.eljas.otharjoitustyo.domain.User;
import veijalainen.eljas.otharjoitustyo.util.Result;

import java.io.File;

import static org.junit.Assert.*;

public class UserFileDaoTest {

	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();
	private File file;


	@Before
	public void setUp() throws Exception {
		file = temporaryFolder.newFile("userTest.txt");
	}


	@Test
	public void constructorCalled() {
		UserFileDao userFileDao = new UserFileDao(file.getAbsolutePath());
		assertTrue(userFileDao.working);
	}


	@Test
	public void savingWorks() {
		{
			UserFileDao userFileDao = new UserFileDao(file.getAbsolutePath());
			userFileDao.create(new User("Kaapo", "cba321"));

		}
		{
			UserFileDao userFileDao = new UserFileDao(file.getAbsolutePath());
			Result<User, Void> result = userFileDao.findByUsername("Kaapo");
			assertTrue(result.success());
		}
	}

	@Test
	public void noUserFound() {
		UserFileDao userFileDao = new UserFileDao(file.getAbsolutePath());
		Result<User, Void> result = userFileDao.findByUsername("Liina");
		assertFalse(result.success());
	}

	@After
	public void tearDown() {
		file.delete();
	}

}