package veijalainen.eljas.otharjoitustyo.domain;

import veijalainen.eljas.otharjoitustyo.dao.ConfigDao;
import veijalainen.eljas.otharjoitustyo.dao.MessageDao;
import veijalainen.eljas.otharjoitustyo.dao.UserDao;
import veijalainen.eljas.otharjoitustyo.util.Result;

import java.util.List;
import java.util.Objects;

public class ChatService {

	UserDao userDao;
	MessageDao messageDao;

	ConfigDao configDao;

	public ChatService(UserDao userDao, MessageDao messageDao, ConfigDao configDao) {
		this.userDao = userDao;
		this.messageDao = messageDao;
		this.configDao = configDao;
	}

	public Result<Moderator, Void> loginModerator(String name, String password) {
		return configDao.login(name, password);
	}

	public List<User> getUsers() {
		return userDao.getAll();
	}

	public enum CreateUserErrorCode { USERNAME_EXISTS, DIFFERENT_PASSWORDS, ILLEGAL_PASSWORD }

	public Result<Void, CreateUserErrorCode> createUser(String username, String password, String password2) {
		if (userDao.findByUsername(username).success()) {
			return Result.unsuccessful(CreateUserErrorCode.USERNAME_EXISTS);
		}
		if (!Objects.equals(password, password2)) {
			return Result.unsuccessful(CreateUserErrorCode.DIFFERENT_PASSWORDS);
		}
		if (password.length() < 5) {
			return Result.unsuccessful(CreateUserErrorCode.ILLEGAL_PASSWORD);
		}
		userDao.create(new User(username, password));
		return Result.successful(null);
	}

	public Result<Session, Void> login(String username, String password) {
		Result<User, Void> userResult = userDao.findByUsername(username);
		if (!userResult.success()) {
			return Result.unsuccessful(null);
		}
		User user = userResult.get();

		if (!Objects.equals(user.password, password)) {
			return Result.unsuccessful(null);
		}
		return Result.successful(new Session(user));
	}

	public List<Message> getMessageHistory(String userA, String userB) {
		return messageDao.getMessageHistory(userA, userB);
	}

	public void sendMessage(Message message) {
		messageDao.sendMessage(message);
	}
}
