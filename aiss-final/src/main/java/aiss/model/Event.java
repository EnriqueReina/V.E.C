package aiss.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event {
	
	private String id;
	private String title;
	private String description;
	private String date;
	
	public Event() {
		
	}
	
	public Event(String title, String description, String date) {
		this.title = title;
		this.description = description;
		try {
			LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
			this.date = date;
		}
		catch(DateTimeParseException e) {
			this.date=null;
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		try {
			LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
			this.date = date;
		}
		catch(DateTimeParseException e) {
			
		}
	}
	
}
