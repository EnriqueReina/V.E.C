package aiss.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.databind.util.StdConverter;

public class DeserializeToLocalDate extends StdConverter<String, LocalDate> {
	public LocalDate convert(String s) {
		return LocalDate.parse(s, DateTimeFormatter.ISO_DATE);
	}
}
