package veijalainen.eljas.otharjoitustyo.domain;

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


}
