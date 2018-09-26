package manytag.framework.util;

import java.util.UUID;

public class UUIDBuilder {
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.replaceAll("-", "");
		return uuid;
	}
}