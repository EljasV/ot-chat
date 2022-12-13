package veijalainen.eljas.otharjoitustyo.dao;

import veijalainen.eljas.otharjoitustyo.domain.Message;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MessageFileDao implements MessageDao {
	List<Message> messageList;
	private final File file;
	private final String filename;

	boolean working;

	public MessageFileDao(String filename) {
		this.filename = filename;
		file = new File(filename);
		working = true;
		messageList = new ArrayList<>();
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

	@Override
	public List<Message> getMessageHistory(String userA, String userB) {
		return messageList.stream().filter(message -> message.fromName.equals(userA) && message.toName.equals(userB) || message.toName.equals(userA) && message.fromName.equals(userB))
				  .sorted((o1, o2) -> (int) (o1.timestamp - o2.timestamp)).collect(Collectors.toList());
	}

	@Override
	public void sendMessage(Message message) {
		messageList.add(message);
		save();
	}

	@Override
	public boolean isWorking() {
		return working;
	}

	@Override
	public List<Message> getAllMessages() {
		return messageList.stream().sorted((o1, o2) -> (int) (o1.timestamp - o2.timestamp)).collect(Collectors.toList());
	}

	@Override
	public void deleteMessage(Message message) {
		messageList.remove(message);
		save();
	}

	private void load() {
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String fromName;
				String toName;
				String timestampString;
				long timestamp;
				String data;

				String line = scanner.nextLine();

				int i = line.indexOf(":");
				fromName = line.substring(0, i);

				int i2 = line.indexOf(":", i + 1);
				toName = line.substring(i + 1, i2);

				int i3 = line.indexOf(":", i2 + 1);
				timestampString = line.substring(i2 + 1, i3);
				timestamp = Long.parseLong(timestampString);

				data = line.substring(i3 + 1);

				Message message = new Message(fromName, toName, data, timestamp);
				messageList.add(message);
			}
		} catch (FileNotFoundException ignored) {
		}
	}

	private void save() {
		try (FileWriter fileWriter = new FileWriter(file)) {
			for (Message message :
					  messageList) {
				String str = message.fromName + ":" + message.toName + ":" + message.timestamp + ":" + message.data + "\n";
				fileWriter.write(str);
			}
		} catch (IOException ignored) {

		}
	}
}
