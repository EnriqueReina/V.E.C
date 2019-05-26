package aiss.api.resources;

import java.net.URI;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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

import aiss.model.Event;
import aiss.model.Project;
import aiss.model.repository.MapProjectRepository;
import aiss.model.repository.ProjectRepository;

@Path("/projects")
public class EventResource {
	
	public static EventResource _instance=null;
	ProjectRepository repository;
	
	private EventResource(){
		repository=MapProjectRepository.getInstance();
	}
	
	public static EventResource getInstance()
	{
		if(_instance==null)
			_instance=new EventResource();
		return _instance; 
	}
	
	@GET
	@Path("/{projectId}/events")
	@Produces("application/json")
	public Collection<Event> getAllEvents(@PathParam("projectId") String projectId) {
		Project project = repository.getProject(projectId);
		
		if (project==null)
			throw new NotFoundException("The project with id=" + projectId + " was not found");
		
		return repository.getAllEventsFromProject(projectId);
	}
	
	@GET
	@Path("/{projectId}/events/{eventId}")
	@Produces("application/json")
	public Event getEvent(@PathParam("projectId") String projectId, @PathParam("eventId") String eventId) {
		
		Project project = repository.getProject(projectId);
		
		if (project==null)
			throw new NotFoundException("The project with id=" + projectId + " was not found");
		
		Event event = repository.getEvent(eventId);
		
		if(event == null)
			throw new NotFoundException("The event with id="+ eventId +" was not found");	
		
		return event;
	}
	
	@POST
	@Path("/{projectId}/events")
	@Consumes("application/json")
	@Produces("application/json")
	public Response addEvent(@Context UriInfo uriInfo, Event event, @PathParam("projectId") String projectId) {
		
		Project project = repository.getProject(projectId);
		
		if (project==null)
			throw new NotFoundException("The project with id=" + projectId + " was not found");
		
		if(event.getTitle() == null || "".equals(event.getTitle()))
			throw new BadRequestException("The name of the event must not be null");
		
		if(event.getDate() == null || "".equals(event.getDate()))
			throw new BadRequestException("The date of the event must not be null");
		
		repository.addEvent(event);
		repository.addEventToProject(projectId, event.getId());

		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "getEvent");
		URI uri = ub.build(projectId,event.getId());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(event);			
		return resp.build();
	}	
	
	@PUT
	@Path("/{projectId}/events")
	@Consumes("application/json")
	public Response updateEvent(Event event, @PathParam("projectId") String projectId) {
		
		Project project = repository.getProject(projectId);
		
		if (project==null)
			throw new NotFoundException("The project with id=" + projectId + " was not found");
		
		Event oldE = repository.getEvent(event.getId());
		if (oldE == null) {
			throw new NotFoundException("The event with id="+ event.getId() +" was not found");			
		}

		if(event.getTitle()!=null) {
			oldE.setTitle(event.getTitle());
		}
		if(event.getDescription()!=null) {
			oldE.setDescription(event.getDescription());
		}
		if(event.getDate()!=null) {
			oldE.setDate(event.getDate());
		}
		
		return Response.noContent().build();
	}
	
	
	@DELETE
	@Path("/{projectId}/events/{eventId}")
	public Response removeEvent(@PathParam("eventId") String eventId, @PathParam("projectId") String projectId) {
		
		Project project = repository.getProject(projectId);
		
		if (project==null)
			throw new NotFoundException("The project with id=" + projectId + " was not found");
			
		Event toBeRemoved = repository.getEvent(eventId);
		if (toBeRemoved == null) {
			throw new NotFoundException("The event with id="+ eventId +" was not found");
		}
		else {
			repository.removeEventFromProject(projectId, eventId);
			repository.deleteEvent(eventId);
		}
		
		return Response.noContent().build();
	}
}
