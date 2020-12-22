package entities;

import java.util.HashMap;

public class AppData {

	private static HashMap<String, Object> attributes = new HashMap<String, Object>();

	private AppData() {
	}

	public static void setAttribute(String key, Object obj) {
		attributes.put(key, obj);
	}

	public static Object getAttribute(String key) {
		return attributes.get(key);
	}

	public void remove(String key) {
		attributes.remove(key);
	}
}
