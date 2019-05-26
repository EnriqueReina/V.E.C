package aiss.api.resources;

import java.net.URI;
import java.util.Collection;
import java.util.stream.Collectors;

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

import aiss.model.Collaborator;
import aiss.model.repository.MapProjectRepository;
import aiss.model.repository.ProjectRepository;

@Path("/collaborators")
public class CollaboratorResource {
	
	public static CollaboratorResource _instance = null;
	ProjectRepository repository;
	
	private CollaboratorResource() {
		repository=MapProjectRepository.getInstance();
	}
	
	public static CollaboratorResource getInstance() {
		if(_instance==null)
			_instance=new CollaboratorResource();
		return _instance; 
	}
	
	@GET
	@Produces("application/json")
	public Collection<Collaborator> getAllCollaborators() {
		return repository.getAllCollaborators();
	}
	
	@GET
	@Path("/{collaboratorId}")
	@Produces("application/json")
	public Collaborator getCollaborator(@PathParam("collaboratorId") String collaboratorId) {
		Collaborator c = repository.getCollaborator(collaboratorId);
		if(c == null) {
			throw new NotFoundException("The collaborator with id="+ collaboratorId +" was not found");	
		}
		
		return c;
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addCollaborator(@Context UriInfo uriInfo, Collaborator c) {
		if(c.getEmail() == null || "".equals(c.getEmail())) {
			throw new BadRequestException("The email of the collaborator must not be null");
		}
		for(Collaborator c2 : repository.getAllCollaborators()) {
			if(c2.getEmail().equals(c.getEmail())) {
				throw new BadRequestException("A collaborator already owns that email");
			}
		}
		repository.addCollaborator(c);

		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "getCollaborator");
		URI uri = ub.build(c.getId());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(c);			
		
		return resp.build();
	}
	
	@PUT
	@Consumes("application/json")
	public Response updateCollaborator(Collaborator c) {
		Collaborator oldC = repository.getCollaborator(c.getId());
		if (oldC == null) {
			throw new NotFoundException("The collaborator with id="+ c.getId() +" was not found");			
		}
		if(c.getName()!=null) {
			oldC.setName(c.getName());;
		}
		if(c.getSurname()!=null) {
			oldC.setSurname(c.getSurname());
		}
		if(c.getEmail()!=null) {
			oldC.setEmail(c.getEmail());
		}
		
		return Response.noContent().build();
	}
	
	@DELETE
	@Path("/{collaboratorId}")
	public Response removeCollaborator(@PathParam("collaboratorId") String collaboratorId) {
		Collaborator toBeRemoved = repository.getCollaborator(collaboratorId);
		if (toBeRemoved == null)
			throw new NotFoundException("The collaborator with id="+ collaboratorId +" was not found");
		else
			repository.deleteCollaborator(collaboratorId);
		
		return Response.noContent().build();
	}
}
