package veijalainen.eljas.otharjoitustyo.domain;

public class Session {
	User user;

	public Session(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}
}
