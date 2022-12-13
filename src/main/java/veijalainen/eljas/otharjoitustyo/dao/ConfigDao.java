package veijalainen.eljas.otharjoitustyo.dao;

import veijalainen.eljas.otharjoitustyo.domain.Moderator;
import veijalainen.eljas.otharjoitustyo.util.Result;

import java.util.Set;

public interface ConfigDao {
	Set<Moderator> getModerators();

	Result<Moderator, Void> login(String name, String password);

	boolean isWorking();
}
