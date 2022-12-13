package veijalainen.eljas.otharjoitustyo.dao;

import com.google.gson.Gson;
import veijalainen.eljas.otharjoitustyo.domain.Moderator;
import veijalainen.eljas.otharjoitustyo.util.Result;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class ConfigFileDao implements ConfigDao {

	private boolean isWorking;

	private class JsonData {
		Set<Moderator> moderators;

		JsonData() {
			moderators = new HashSet<>();
			moderators.add(new Moderator("ExampleModerator", "1234"));
		}
	}

	JsonData jsonData;

	public ConfigFileDao(String filename) {
		Gson gson = new Gson();
		File file = new File(filename);
		isWorking = true;
		try {
			boolean created = file.createNewFile();
			if (created) {
				FileWriter writer = new FileWriter(file);
				jsonData = new JsonData();
				gson.toJson(jsonData, writer);
				writer.close();
			} else {
				FileReader fileReader = new FileReader(filename);
				jsonData = gson.fromJson(fileReader, JsonData.class);
			}
		} catch (IOException e) {
			isWorking = false;
		}
	}

	@Override
	public Set<Moderator> getModerators() {
		return jsonData.moderators;
	}

	@Override
	public Result<Moderator, Void> login(String name, String password) {
		List<Moderator> moderators = jsonData.moderators.stream().filter(moderator -> Objects.equals(moderator.getName(), name) && Objects.equals(moderator.getPassword(), password)).collect(Collectors.toList());
		if (moderators.isEmpty()) {
			return Result.unsuccessful(null);
		}
		return Result.successful(moderators.get(0));
	}

	@Override
	public boolean isWorking() {
		return isWorking;
	}
}
