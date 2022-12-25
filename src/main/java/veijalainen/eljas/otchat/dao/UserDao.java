package veijalainen.eljas.otchat.dao;

import veijalainen.eljas.otchat.domain.User;
import veijalainen.eljas.otchat.util.Result;

import java.util.List;

/**
 * A DAO responsible for saving and accessing user data.
 */
public interface UserDao {
	/**
	 * This method should be checked before using, because DAO might not have been initialized correctly.
	 * @return Returns if the DAO is working.
	 */
	boolean isWorking();


	/**
	 * Creates new user into DAO, so it can be saved and accessed later.
	 * @param user User to be created
	 * @return User created
	 */
	User create(User user);


	/**
	 * Finds an user by username
	 * @param username Name of the user being searched
	 * @return If user was found, returns successful result containing the user created, otherwise returns unsuccessfull result
	 */
	Result<User, Void> findByUsername(String username);

	/**
	 * @return Returns a list of all users
	 */
	List<User> getAll();
}
