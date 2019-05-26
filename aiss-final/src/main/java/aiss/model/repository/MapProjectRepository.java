package aiss.model.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import aiss.model.Collaborator;
import aiss.model.Event;
import aiss.model.File;
import aiss.model.Message;
import aiss.model.Project;

public class MapProjectRepository implements ProjectRepository {
	
	Map<String,Project> projectMap;
	Map<String,File> fileMap;
	Map<String,Collaborator> collaboratorMap;
	Map<String,Message> messageMap;
	Map<String,Event> eventMap;
	private static MapProjectRepository instance = null;
	private int index = 0;
	
	public static MapProjectRepository getInstance() {
		if(instance == null) {
			instance = new MapProjectRepository();
			instance.init();
		}
		return instance;
	}
	
	public void init() {
		projectMap = new HashMap<String,Project>();
		fileMap = new HashMap<String,File>();
		collaboratorMap = new HashMap<String,Collaborator>();
		messageMap = new HashMap<String,Message>();
		eventMap = new HashMap<String,Event>();
		
		//Create collaborators
		Collaborator c1 = new Collaborator();
		c1.setName("Víctor");
		c1.setSurname("Muñoz");
		c1.setEmail("vicmunram@gmail.com");
		addCollaborator(c1);
		
		Collaborator c2 = new Collaborator();
		c2.setName("Enrique");
		c2.setSurname("Reina");
		c2.setEmail("enrreigut@gmail.com");
		addCollaborator(c2);
		
		Collaborator c3 = new Collaborator();
		c3.setName("Carlos");
		c3.setSurname("Pardo");
		c3.setEmail("carparpas@gmail.com");
		addCollaborator(c3);
		
		//Create messages
		Message m1 = new Message();
		m1.setCollaboratorId(c1.getId());
		m1.setContent("Hola");
		addMessage(m1);
		
		Message m2 = new Message();
		m2.setCollaboratorId(c2.getId());
		m2.setContent("Adios");
		addMessage(m2);
		
		//Create events
		Event e1 = new Event();
		e1.setTitle("Reunion");
		e1.setDescription("Llevar impresos los informes");
		e1.setDate("2019-12-03");
		addEvent(e1);
		
		Event e2 = new Event();
		e2.setTitle("Presentacion");
		e2.setDescription("Los inversores estaran presentes");
		e2.setDate("2019-05-27");
		addEvent(e2);
		
		//Create files
		
		File f1 = new File("Lista de la compra", "doc");
		f1.setContent("- Huevos\n- Mayonesa\n- Papel Higiénico");
		addFile(f1);
		
		File f2 = new File("Contraseña de taquilla", "doc");
		f2.setContent("2678");
		addFile(f2);
		
		File f3 = new File("Fichero en blanco", "doc");
		addFile(f3);
		
		//Create folders
		File f4 = new File("Carpeta con contenido", "folder");
		addFile(f4);
		
		File f5 = new File("Carpeta vacía", "folder");
		addFile(f5);
	
		//Add files to folders
		addFileToFolder(f4.getId(),f1);
		addFileToFolder(f4.getId(),f2);
		
		//Create projects
		Project p1 = new Project("Proyecto 1", c1);
		addProject(p1);
		Project p2 = new Project("Proyecto 2", c2);
		addProject(p2);
		
		//Add files, collaborators, messages and events to projects
		addCollaboratorToProject(p1.getId(), c2.getId());
		addCollaboratorToProject(p1.getId(), c3.getId());
		
		addFileToProject(p1.getId(), f5.getId());
		addFileToProject(p1.getId(), f3.getId());
		addFileToProject(p2.getId(), f4.getId());
		
		addMessageToProject(p1.getId(),m1.getId());
		addMessageToProject(p1.getId(),m2.getId());
		
		addEventToProject(p2.getId(),e1.getId());
		addEventToProject(p2.getId(),e2.getId());
	}

	@Override
	public void addCollaborator(Collaborator c) {
		String id = "c" + index++;
		c.setId(id);
		collaboratorMap.put(id, c);
	}

	@Override
	public Collection<Collaborator> getAllCollaborators() {
		return collaboratorMap.values();
	}

	@Override
	public Collaborator getCollaborator(String collaboratorId) {
		return collaboratorMap.get(collaboratorId);
	}

	@Override
	public void updateCollaborator(Collaborator c) {
		collaboratorMap.put(c.getId(), c);
	}

	@Override
	public void deleteCollaborator(String collaboratorId) {
		collaboratorMap.remove(collaboratorId);

	}
	
	@Override
	public void addMessage(Message m) {
		String id = "m" + index++;
		m.setId(id);
		m.setTime(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
		messageMap.put(id, m);
	}
	
	@Override
	public Message getMessage(String messageId) {
		return messageMap.get(messageId);
	}

	@Override
	public void addFile(File f) {
		String id = "f" + index++;
		f.setId(id);
		fileMap.put(id, f);
	}

	@Override
	public Collection<File> getAllFiles() {
		return fileMap.values();
	}

	@Override
	public File getFile(String fileId) {
		return fileMap.get(fileId);
	}

	@Override
	public void updateFile(File f) {
		fileMap.put(f.getId(), f);
	}

	@Override
	public void deleteFile(String fileId) {
		if(getFile(fileId).getChildren()!=null && !getFile(fileId).getChildren().isEmpty()) {
			for(File c : getFile(fileId).getChildren()) {
				deleteFile(c.getId());
			}
		}
		fileMap.remove(fileId);
	}

	@Override
	public Collection<File> getAllFromFolder(String fileId) {
		return getFile(fileId).getChildren();
	}

	@Override
	public void addFileToFolder(String folderId, File file) {
		File folder = getFile(folderId);
		folder.addFile(fileMap.get(file.getId()));
		file.setParent(folderId);
	}

	@Override
	public void removeFileFromFolder(String folderId, String fileId) {
		getFile(folderId).removeFile(fileId);
	}

	@Override
	public void addProject(Project p) {
		String id = "p" + index++;
		p.setId(id);
		projectMap.put(id, p);
	}

	@Override
	public Collection<Project> getAllProjects() {
		return projectMap.values();
	}

	@Override
	public Project getProject(String projectId) {
		return projectMap.get(projectId);
	}

	@Override
	public void updateProject(Project p) {
		projectMap.put(p.getId(), p);
	}

	@Override
	public void deleteProject(String projectId) {
		projectMap.remove(projectId);
	}

	@Override
	public Collection<File> getAllFilesFromProject(String projectId) {
		return getProject(projectId).getFiles();
	}

	@Override
	public void addFileToProject(String projectId, String fileId) {
		Project project = getProject(projectId);
		project.addFile(fileMap.get(fileId));
	}

	@Override
	public void removeFileFromProject(String projectId, String fileId) {
		getProject(projectId).removeFile(fileId);
	}

	@Override
	public Collection<Collaborator> getAllCollaboratorsFromProject(String projectId) {
		return getProject(projectId).getCollaborators();
	}

	@Override
	public void addCollaboratorToProject(String projectId, String collaboratorId) {
		Project project = getProject(projectId);
		project.addCollaborator(collaboratorMap.get(collaboratorId));
	}

	@Override
	public void removeCollaboratorFromProject(String projectId, String collaboratorId) {
		getProject(projectId).removeCollaborator(collaboratorId);
	}
	
	@Override
	public Collection<Message> getAllMessagesFromProject(String projectId) {
		return getProject(projectId).getMessages();
	}

	@Override
	public void addMessageToProject(String projectId, String messageId) {
		Project project = getProject(projectId);
		project.addMessage(messageMap.get(messageId));
	}

	@Override
	public void addEvent(Event e) {
		String id = "e" + index++;
		e.setId(id);
		eventMap.put(id, e);
	}

	@Override
	public Collection<Event> getAllEvents() {
		return eventMap.values();
	}

	@Override
	public Event getEvent(String eventId) {
		return eventMap.get(eventId);
	}

	@Override
	public void updateEvent(Event e) {
		eventMap.put(e.getId(), e);
	}

	@Override
	public void deleteEvent(String eventId) {
		eventMap.remove(eventId);
	}

	@Override
	public Collection<Event> getAllEventsFromProject(String projectId) {
		return getProject(projectId).getEvents();
	}

	@Override
	public void addEventToProject(String projectId, String eventId) {
		Project project = getProject(projectId);
		project.addEvent(getEvent(eventId));
		
	}

	@Override
	public void removeEventFromProject(String projectId, String eventId) {
		getProject(projectId).removeEvent(eventId);
	}

}
