package aiss.api.resources;

import java.net.URI;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;

import aiss.model.Message;
import aiss.model.Project;
import aiss.model.repository.MapProjectRepository;
import aiss.model.repository.ProjectRepository;

@Path("/projects")
public class MessageResource {
	
	public static MessageResource _instance=null;
	ProjectRepository repository;
	
	private MessageResource(){
		repository=MapProjectRepository.getInstance();
	}
	
	public static MessageResource getInstance()
	{
		if(_instance==null)
			_instance=new MessageResource();
		return _instance; 
	}
	
	@GET
	@Path("/{projectId}/messages")
	@Produces("application/json")
	public Collection<Message> getAllMessages(@PathParam("projectId") String projectId) {
		Project project = repository.getProject(projectId);
		
		if (project==null)
			throw new NotFoundException("The project with id=" + projectId + " was not found");
		
		return repository.getAllMessagesFromProject(projectId);
	}
	
	@GET
	@Path("/{projectId}/messages/{messageId}")
	@Produces("application/json")
	public Message getMessage(@PathParam("projectId") String projectId, @PathParam("messageId") String messageId) {
		
		Project project = repository.getProject(projectId);
		
		if (project==null)
			throw new NotFoundException("The project with id=" + projectId + " was not found");
		
		Message message = repository.getMessage(messageId);
		
		if(message == null)
			throw new NotFoundException("The message with id="+ messageId +" was not found");	
		
		return message;
	}
	
	@POST
	@Path("/{projectId}/messages")
	@Consumes("application/json")
	@Produces("application/json")
	public Response addMessage(@Context UriInfo uriInfo, Message message, @PathParam("projectId") String projectId) {
		
		Project project = repository.getProject(projectId);
		
		if (project==null)
			throw new NotFoundException("The project with id=" + projectId + " was not found");
		
		if(message.getCollaboratorId() == null || "".equals(message.getCollaboratorId()))
			throw new BadRequestException("The collaboratorID of the message must not be null");
		
		if(message.getContent() == null || "".equals(message.getContent()))
			throw new BadRequestException("The content of the message must not be null");
		
		if(message.getTime() != null)
			throw new BadRequestException("The time of the message must be null");
		
		repository.addMessage(message);
		repository.addMessageToProject(projectId, message.getId());
		
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "getMessage");
		URI uri = ub.build(projectId,message.getId());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(message);			
		return resp.build();
	}	
}
