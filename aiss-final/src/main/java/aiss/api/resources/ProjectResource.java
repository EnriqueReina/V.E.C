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

import aiss.model.Collaborator;
import aiss.model.Project;
import aiss.model.repository.MapProjectRepository;
import aiss.model.repository.ProjectRepository;

@Path("/projects")
public class ProjectResource {
	
	private static ProjectResource _instance=null;
	ProjectRepository repository;
	
	private ProjectResource() {
		repository=MapProjectRepository.getInstance();
	}
	
	public static ProjectResource getInstance() {
		if(_instance==null)
			_instance=new ProjectResource();
		return _instance; 
	}
	
	@GET
	@Produces("application/json")
	public Collection<Project> getAllProjects() {
		return repository.getAllProjects();
	}
	
	@GET
	@Path("/{projectId}")
	@Produces("application/json")
	public Project getProject(@PathParam("projectId") String projectId)
	{
		Project project = repository.getProject(projectId);
		
		if (project == null) {
			throw new NotFoundException("The project wit id="+ projectId +" was not found");			
		}
		
		return project;
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addProject(@Context UriInfo uriInfo, Project project) {
		if (project.getTitle() == null || "".equals(project.getTitle()))
			throw new BadRequestException("The title of the project must not be null");
		
		if(project.getOwner() == null || project.getOwner().getEmail() == null || "".equals(project.getOwner().getEmail()))
			throw new BadRequestException("The owner of the project must not be null.");
		
		if(project.getCollaborators()!=null)
			throw new BadRequestException("The collaborators property is not editable.");
		
		if(project.getFiles()!=null)
			throw new BadRequestException("The files property is not editable.");
		
		if(project.getMessages()!=null)
			throw new BadRequestException("The messages property is not editable.");
		
		if(project.getEvents()!=null)
			throw new BadRequestException("The events property is not editable.");

		repository.addProject(project);

		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "getProject");
		URI uri = ub.build(project.getId());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(project);			
		return resp.build();
	}
	
	@PUT
	@Consumes("application/json")
	public Response updateProject(Project project) {
		Project oldP = repository.getProject(project.getId());
		if (oldP == null) {
			throw new NotFoundException("The project with id="+ project.getId() +" was not found");			
		}
		
		if (project.getOwner()!=null)
			throw new BadRequestException("The owner property is not editable.");
		
		if (project.getFiles()!=null)
			throw new BadRequestException("The files property is not editable.");
		
		if (project.getCollaborators()!=null)
			throw new BadRequestException("The collaborators property is not editable.");
		
		if(project.getMessages()!=null)
			throw new BadRequestException("The messages property is not editable.");
		
		if(project.getEvents()!=null)
			throw new BadRequestException("The events property is not editable.");

		if (project.getTitle()!=null)
			oldP.setTitle(project.getTitle());
		
		return Response.noContent().build();
	}
	
	@DELETE
	@Path("/{projectId}")
	public Response removeProject(@PathParam("projectId") String projectId) {
		Project toberemoved = repository.getProject(projectId);
		if (toberemoved == null)
			throw new NotFoundException("The project with id="+ projectId +" was not found");
		else
			repository.deleteProject(projectId);
		
		return Response.noContent().build();
	}
	
	@POST	
	@Path("/{projectId}/{collaboratorId}")
	@Consumes("text/plain")
	@Produces("application/json")
	public Response addCollaboratorToProject(@Context UriInfo uriInfo,@PathParam("projectId") String projectId, @PathParam("collaboratorId") String collaboratorId) {				
		Project project = repository.getProject(projectId);
		Collaborator collab = repository.getCollaborator(collaboratorId);
		
		if (project==null)
			throw new NotFoundException("The project with id=" + projectId + " was not found");
		
		if (collab == null)
			throw new NotFoundException("The collaborator with id=" + collaboratorId + " was not found");
		
		if (project.getCollaborator(collaboratorId)!=null)
			throw new BadRequestException("The collaborator is already included in the project.");
			
		repository.addCollaboratorToProject(projectId, collaboratorId);		

		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "getProject");
		URI uri = ub.build(projectId);
		ResponseBuilder resp = Response.created(uri);
		resp.entity(project);			
		return resp.build();
	}
	
	
	@DELETE
	@Path("/{projectId}/{collaboratorId}")
	public Response removeCollaboratorFromProject(@PathParam("projectId") String projectId, @PathParam("collaboratorId") String collaboratorId) {
		Project project = repository.getProject(projectId);
		Collaborator collab = repository.getCollaborator(collaboratorId);
		
		if (project==null)
			throw new NotFoundException("The project with id=" + projectId + " was not found");
		
		if (collab == null)
			throw new NotFoundException("The collaborator with id=" + collaboratorId + " was not found");
		
		repository.removeCollaboratorFromProject(projectId, collaboratorId);	
		
		return Response.noContent().build();
	}	
	
}
