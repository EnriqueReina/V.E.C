package aiss.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.fasterxml.jackson.databind.util.StdConverter;

public class DeserializeToLocalDateTime extends StdConverter<String, LocalDateTime> {
	public LocalDateTime convert(String s) {
		return LocalDateTime.parse(s, DateTimeFormatter.ISO_DATE_TIME);
	}
}
