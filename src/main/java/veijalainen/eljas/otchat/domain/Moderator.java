package veijalainen.eljas.otchat.domain;

public class Moderator {
	String name;
	String password;

	public Moderator(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}
}
