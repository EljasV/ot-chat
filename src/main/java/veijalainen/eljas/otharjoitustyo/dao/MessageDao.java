package veijalainen.eljas.otharjoitustyo.dao;

import veijalainen.eljas.otharjoitustyo.domain.Message;

import java.util.List;

public interface MessageDao {

	List<Message> getMessageHistory(String userA, String userB);

	void sendMessage(Message message);

	boolean isWorking();

}
