package veijalainen.eljas.otharjoitustyo.dao;

import veijalainen.eljas.otharjoitustyo.domain.User;
import veijalainen.eljas.otharjoitustyo.util.Result;

import java.util.List;

public interface UserDao {
	User create(User user);


	Result<User, Void> findByUsername(String username);

	List<User> getAll();
}
