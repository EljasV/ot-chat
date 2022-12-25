package veijalainen.eljas.otchat.dao;

import veijalainen.eljas.otchat.domain.Moderator;
import veijalainen.eljas.otchat.util.Result;

import java.util.HashSet;
import java.util.Set;

public class ConfigMemoryDao implements ConfigDao {

	final HashSet<Moderator> moderators = new HashSet<>();

	@Override
	public Set<Moderator> getModerators() {
		return moderators;
	}

	@Override
	public Result<Moderator, Void> login(String name, String password) {
		return Result.unsuccessful(null);
	}

	@Override
	public boolean isWorking() {
		return false;
	}
}
