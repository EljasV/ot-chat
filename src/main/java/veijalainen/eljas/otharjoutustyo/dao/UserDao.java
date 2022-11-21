package veijalainen.eljas.otharjoutustyo.dao;

import veijalainen.eljas.otharjoutustyo.domain.User;
import veijalainen.eljas.otharjoutustyo.util.Result;

import java.util.List;

public interface UserDao {
	User create(User user);


	Result<User, Void> findByUsername(String username);

	List<User> getAll();
}
