package aiss.model;

import java.util.ArrayList;
import java.util.List;

public class Project {
	
	private String id;
	private String title;
	private Collaborator owner;
	private List<Collaborator> collaborators;
	private List<File> files;
	private List<Message> messages;
	private List<Event> events;
	
	public Project() {
		
	}
	
	public Project(String title, Collaborator owner) {
		this.title = title;
		this.owner = owner;
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

	public Collaborator getOwner() {
		return owner;
	}

	public void setOwner(Collaborator owner) {
		this.owner = owner;
	}

	public List<Collaborator> getCollaborators() {
		return collaborators;
	}

	public void setCollaborators(List<Collaborator> collaborators) {
		this.collaborators = collaborators;
	}

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public File getFile(String id) {
		if(this.files==null) {
			return null;
		}
		else {
			File file = null;
			for(File f: this.files)
				if (f.getId().equals(id))
				{
					file=f;
					break;
				}
			return file;
		}
	}
	
	public void addFile(File f) {
		if(this.files==null) {
			this.files = new ArrayList<File>();	
		}
		this.files.add(f);
	}
	
	public void removeFile(File f) {
		this.files.remove(f);
	}
	
	public void removeFile(String id) {
		File f = getFile(id);
		if (f!=null)
			this.files.remove(f);
	}
	
	public Collaborator getCollaborator(String id) {
		if(this.collaborators==null) {
			return null;
		}
		else {
			Collaborator collaborator = null;
			for(Collaborator c: this.collaborators)
				if (c.getId().equals(id))
				{
					collaborator=c;
					break;
				}
			return collaborator;
		}
	}
	
	public void addCollaborator(Collaborator c) {
		if(this.collaborators==null) {
			this.collaborators = new ArrayList<Collaborator>();	
		}
		this.collaborators.add(c);
	}
	
	public void removeCollaborator(Collaborator c) {
		this.collaborators.remove(c);
	}
	
	public void removeCollaborator(String id) {
		Collaborator c = getCollaborator(id);
		if (c!=null)
			this.collaborators.remove(c);
	}
	
	public void addMessage(Message m) {
		if(this.messages==null) {
			this.messages = new ArrayList<Message>();	
		}
		this.messages.add(m);
	}
	
	public Event getEvent(String id) {
		if(this.events==null) {
			return null;
		}
		else {
			Event event = null;
			for(Event e: this.events)
				if (e.getId().equals(id))
				{
					event=e;
					break;
				}
			return event;
		}
	}
	
	public void addEvent(Event e) {
		if(this.events==null) {
			this.events = new ArrayList<Event>();	
		}
		this.events.add(e);
	}
	
	public void removeEvent(Event e) {
		this.events.remove(e);
	}
	
	public void removeEvent(String id) {
		Event e = getEvent(id);
		if (e!=null)
			this.events.remove(e);
	}
}
