package veijalainen.eljas.otchat.dao;

import veijalainen.eljas.otchat.domain.Moderator;
import veijalainen.eljas.otchat.util.Result;

import java.util.Set;

/**
 * A DAO responsible for configuration.
 */
public interface ConfigDao {
	/**
	 * @return A set of all moderators
	 */
	Set<Moderator> getModerators();

	/**
	 * Tries to login as a moderator
	 * @param name Name of the moderator wishing to log in
	 * @param password Password the user has supplied
	 * @return If name and password match with correct data, returns successfull result of a Moderator object, otherwise unsuccessfull result.
	 */
	Result<Moderator, Void> login(String name, String password);

	/**
	 * Tells if this DAO has not encountered a problem and can be used. Should be checked before using.
	 * @return true if this is working, false otherwise.
	 */
	boolean isWorking();
}
