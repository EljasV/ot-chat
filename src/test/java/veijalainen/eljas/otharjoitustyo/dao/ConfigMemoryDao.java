package veijalainen.eljas.otharjoitustyo.dao;

import veijalainen.eljas.otharjoitustyo.domain.Moderator;
import veijalainen.eljas.otharjoitustyo.util.Result;

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
