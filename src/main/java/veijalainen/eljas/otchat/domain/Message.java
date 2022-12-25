package veijalainen.eljas.otchat.domain;

import java.util.Objects;


/**
 * Represents a message between two users.
 */
public class Message {


	public final String fromName;
	public final String toName;
	public final String data;
	public final long timestamp;

	/**
	 * Constructs a new message.
	 * @param fromName The username of the user who sent the message.
	 * @param toName The username of the user who will receive the message
	 * @param data A piece of text which user sees.
	 * @param timestamp Tells when the message was sent.
	 */
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
