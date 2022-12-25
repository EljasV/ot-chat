package veijalainen.eljas.otchat.dao;

import veijalainen.eljas.otchat.domain.User;
import veijalainen.eljas.otchat.util.Result;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class UserFileDao implements UserDao {

	List<User> users;
	private final File file;
	private final String filename;

	@Override
	public boolean isWorking() {
		return working;
	}

	boolean working;

	public UserFileDao(String filename) {
		this.filename = filename;
		file = new File(filename);
		working = true;
		users = new ArrayList<>();
		try {
			file.createNewFile();
		} catch (IOException e) {
			working = false;
		}
		working &= file.canRead();
		working &= file.canWrite();
		if (working) {
			load();
		}
	}

	private void save() {
		try (FileWriter fileWriter = new FileWriter(file)) {
			for (User user :
					  users) {
				fileWriter.write(user.getUsername() + "," + user.getPassword() + "\n");
			}
		} catch (IOException ignored) {

		}
	}

	private void load() {
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String[] split = scanner.nextLine().split(",");
				users.add(new User(split[0], split[1]));
			}
		} catch (FileNotFoundException ignored) {
		}
	}

	@Override
	public User create(User user) {
		users.add(user);
		save();
		return user;
	}

	@Override
	public Result<User, Void> findByUsername(String username) {
		for (User user :
				  users) {
			if (Objects.equals(user.getUsername(), username)) {
				return Result.successful(user);
			}
		}
		return Result.unsuccessful(null);
	}

	@Override
	public List<User> getAll() {
		return users;
	}
}
