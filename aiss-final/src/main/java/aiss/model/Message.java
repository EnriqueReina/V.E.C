package aiss.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
	
	private String id;
	private String collaboratorId;
	private String content;
	private String time;
	
	public Message() {
		
	}
	
	public Message(String collaboratorId, String content) {
		this.collaboratorId = collaboratorId;
		this.content = content;
		this.time = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCollaboratorId() {
		return collaboratorId;
	}

	public void setCollaboratorId(String collaboratorId) {
		this.collaboratorId = collaboratorId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
