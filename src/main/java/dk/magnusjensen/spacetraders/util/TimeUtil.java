package dk.magnusjensen.spacetraders.util;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {

	private static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter
			.ofPattern("yyyy-MM-dd'T'HH:mm:ss:SSS");

	public static ZonedDateTime parseDateTimeString(String datetime) {
		return ZonedDateTime.parse(datetime, DATE_TIME_FORMATTER);
	}
}
