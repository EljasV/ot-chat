package veijalainen.eljas.otharjoitustyo.dao;

import veijalainen.eljas.otharjoitustyo.domain.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MessageMemoryDao implements MessageDao {

	public MessageMemoryDao() {
		messageList = new ArrayList<>();
	}

	List<Message> messageList;

	@Override
	public List<Message> getMessageHistory(String userA, String userB) {
		return messageList.stream().filter(message -> message.fromName.equals(userA) && message.toName.equals(userB) || message.toName.equals(userA) && message.fromName.equals(userB))
				  .sorted((o1, o2) -> (int) (o1.timestamp - o2.timestamp)).collect(Collectors.toList());
	}

	@Override
	public void sendMessage(Message message) {
		messageList.add(message);
	}

	@Override
	public boolean isWorking() {
		return true;
	}
}
