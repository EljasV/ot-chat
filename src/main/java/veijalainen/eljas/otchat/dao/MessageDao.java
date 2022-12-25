package veijalainen.eljas.otchat.dao;

import veijalainen.eljas.otchat.domain.Message;

import java.util.List;


/**
 * A DAO interface responsible for accessing messages
 */
public interface MessageDao {

	/**
	 * @param userA Any user
	 * @param userB Any other user
	 * @return Returns messages sent between userA and userB
	 */
	List<Message> getMessageHistory(String userA, String userB);


	/**
	 * Send message, aka. save message inside DAO, so it can be retrieved later.
	 * @param message Message to be saved in DAO
	 */
	void sendMessage(Message message);

	/**
	 * Sometimes DAO cannot be initialised correctly, so this method should be checked before using it.
	 * @return Is the DAO working
	 */
	boolean isWorking();

	/**
	 * @return Every message known to DAO
	 */
	List<Message> getAllMessages();

	/**
	 * Delete a message from DAO
	 * @param message Message to be deleted from DAO
	 */
	void deleteMessage(Message message);
}
