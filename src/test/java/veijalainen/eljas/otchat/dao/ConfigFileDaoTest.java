package veijalainen.eljas.otchat.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import veijalainen.eljas.otchat.domain.Moderator;
import veijalainen.eljas.otchat.util.Result;

import java.io.File;
import java.io.FileWriter;

import static org.junit.Assert.*;

public class ConfigFileDaoTest {

	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();

	private File file;

	@Before
	public void setUp() throws Exception {
		file = temporaryFolder.newFile("test.json");
		try (FileWriter writer = new FileWriter(file)) {
			writer.write("{\n" +
					  "  \"moderators\": [\n" +
					  "    {\n" +
					  "      \"name\": \"testModerator\",\n" +
					  "      \"password\": \"aaaaa\"\n" +
					  "    }\n" +
					  "  ]\n" +
					  "}");
		}
	}

	@Test
	public void constructorCalledExistingFile() {
		ConfigFileDao messageFileDao = new ConfigFileDao(file.getAbsolutePath());
		assertTrue(messageFileDao.isWorking());
	}

	@Test
	public void constructorCalledMissingFile() {
		File file1 = new File(temporaryFolder.getRoot().getPath() + "/test1");

		ConfigFileDao messageFileDao = new ConfigFileDao(file1.getAbsolutePath());


		assertTrue(messageFileDao.isWorking());

		file1.delete();
	}


	@Test
	public void loginCorrectly() {
		ConfigFileDao configFileDao = new ConfigFileDao(file.getAbsolutePath());
		Result<Moderator, Void> result = configFileDao.login("testModerator", "aaaaa");
		assertTrue(result.success());
	}

	@Test
	public void loginInvalid() {
		ConfigFileDao configFileDao = new ConfigFileDao(file.getAbsolutePath());
		Result<Moderator, Void> result = configFileDao.login("nobody", "1234");
		assertFalse(result.success());
	}


	@After
	public void tearDown() throws Exception {
		file.delete();
	}
}