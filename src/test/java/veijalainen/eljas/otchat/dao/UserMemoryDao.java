package veijalainen.eljas.otchat.dao;

import veijalainen.eljas.otchat.domain.User;
import veijalainen.eljas.otchat.util.Result;

import java.util.*;

public class UserMemoryDao implements UserDao {
	List<User> users = new ArrayList<>();

	@Override
	public boolean isWorking() {
		return true;
	}

	@Override
	public User create(User user) {
		users.add(user);
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
