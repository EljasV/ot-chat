package veijalainen.eljas.otharjoitustyo.domain;

import java.util.Objects;

public class Message {
	public final String fromName;
	public final String toName;
	public final String data;
	public final long timestamp;

	public Message(String fromName, String toName, String data, long timestamp) {
		this.fromName = fromName;
		this.toName = toName;
		this.data = data;
		this.timestamp = timestamp;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Message message = (Message) o;
		return timestamp == message.timestamp && fromName.equals(message.fromName) && toName.equals(message.toName) && data.equals(message.data);
	}

	@Override
	public int hashCode() {
		return Objects.hash(fromName, toName, data, timestamp);
	}
}
