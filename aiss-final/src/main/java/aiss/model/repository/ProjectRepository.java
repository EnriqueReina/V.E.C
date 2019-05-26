package aiss.model.repository;

import java.util.Collection;

import aiss.model.Collaborator;
import aiss.model.Event;
import aiss.model.File;
import aiss.model.Message;
import aiss.model.Project;

public interface ProjectRepository {

	//Collaborators
	public void addCollaborator(Collaborator c);
	public Collection<Collaborator> getAllCollaborators();
	public Collaborator getCollaborator(String collaboratorId);
	public void updateCollaborator(Collaborator c);
	public void deleteCollaborator(String collaboratorId);
	
	//Messages
	public void addMessage(Message m);
	public Message getMessage(String messageId);
	
	//Events
	public void addEvent(Event e);
	public Collection<Event> getAllEvents();
	public Event getEvent(String eventId);
	public void updateEvent(Event e);
	public void deleteEvent(String eventId);
	
	//Files
	public void addFile(File f);
	public Collection<File> getAllFiles();
	public File getFile(String fileId);
	public void updateFile(File f);
	public void deleteFile(String fileId);
	public Collection<File> getAllFromFolder(String fileId);
	public void addFileToFolder(String folderId, File file);
	public void removeFileFromFolder(String folderId, String fileId);
	
	//Projects
	public void addProject(Project p);
	public Collection<Project> getAllProjects();
	public Project getProject(String projectId);
	public void updateProject(Project p);
	public void deleteProject(String projectId);
	public Collection<File> getAllFilesFromProject(String projectId);
	public void addFileToProject(String projectId, String fileId);
	public void removeFileFromProject(String projectId, String fileId);
	public Collection<Collaborator> getAllCollaboratorsFromProject(String projectId);
	public void addCollaboratorToProject(String projectId, String collaboratorId);
	public void removeCollaboratorFromProject(String projectId, String collaboratorId);
	public Collection<Message> getAllMessagesFromProject(String projectId);
	public void addMessageToProject(String projectId, String messageId);
	public Collection<Event> getAllEventsFromProject(String projectId);
	public void addEventToProject(String projectId, String eventId);
	public void removeEventFromProject(String projectId, String eventId);
}
