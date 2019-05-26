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

import aiss.model.File;
import aiss.model.Project;
import aiss.model.repository.MapProjectRepository;
import aiss.model.repository.ProjectRepository;


@Path("/projects")
public class FileResource {
	
	public static FileResource _instance=null;
	ProjectRepository repository;
	
	private FileResource(){
		repository=MapProjectRepository.getInstance();
	}
	
	public static FileResource getInstance()
	{
		if(_instance==null)
			_instance=new FileResource();
		return _instance; 
	}
	
	@GET
	@Path("/{projectId}/files")
	@Produces("application/json")
	public Collection<File> getAllFiles(@PathParam("projectId") String projectId) {
		Project project = repository.getProject(projectId);
		
		if (project==null)
			throw new NotFoundException("The project with id=" + projectId + " was not found");
		
		return repository.getAllFilesFromProject(projectId);
	}
	
	@GET
	@Path("/{projectId}/files/{fileId}")
	@Produces("application/json")
	public File getFile(@PathParam("projectId") String projectId, @PathParam("fileId") String fileId) {
		
		Project project = repository.getProject(projectId);
		
		if (project==null)
			throw new NotFoundException("The project with id=" + projectId + " was not found");
		
		File file = repository.getFile(fileId);
		
		if(file == null)
			throw new NotFoundException("The file with id="+ fileId +" was not found");	
		
		return file;
	}
	
	@POST
	@Path("/{projectId}/files")
	@Consumes("application/json")
	@Produces("application/json")
	public Response addFile(@Context UriInfo uriInfo, File file, @PathParam("projectId") String projectId) {
		
		Project project = repository.getProject(projectId);
		
		if (project==null)
			throw new NotFoundException("The project with id=" + projectId + " was not found");
		
		if(file.getTitle() == null || "".equals(file.getTitle()))
			throw new BadRequestException("The name of the file must not be null");
		
		if(file.getType() == null || "".equals(file.getType()))
			throw new BadRequestException("The type of the file must not be null");
		
		if(!file.getType().equals("doc") && !file.getType().equals("folder"))
			throw new BadRequestException("The type of the file must be doc or folder");
		
		if(file.getChildren() != null)
			throw new BadRequestException("The children property is not editable");
		
		repository.addFile(file);
		if(file.getParent()!=null && repository.getFile(file.getParent())!=null) {
			repository.addFileToFolder(file.getParent(), file);
		}
		else {
			repository.addFileToProject(projectId, file.getId());
		}

		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "getFile");
		URI uri = ub.build(projectId,file.getId());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(file);			
		return resp.build();
	}	
	
	@PUT
	@Path("/{projectId}/files")
	@Consumes("application/json")
	public Response updateFile(File file, @PathParam("projectId") String projectId) {
		
		Project project = repository.getProject(projectId);
		
		if (project==null)
			throw new NotFoundException("The project with id=" + projectId + " was not found");
		
		File oldF = repository.getFile(file.getId());
		if (oldF == null) {
			throw new NotFoundException("The file with id="+ file.getId() +" was not found");			
		}

		if(file.getTitle()!=null) {
			oldF.setTitle(file.getTitle());
		}
		if(file.getContent()!=null) {
			oldF.setContent(file.getContent());
		}
		if(file.getParent()!=null && file.getParent()!=oldF.getParent() && repository.getFile(file.getParent())!=null) {
			repository.addFileToFolder(file.getParent(), file);
			repository.removeFileFromFolder(oldF.getParent(), file.getId());
			oldF.setParent(file.getParent());
		}
		
		return Response.noContent().build();
	}
	
	
	@DELETE
	@Path("/{projectId}/files/{fileId}")
	public Response removeFile(@PathParam("fileId") String fileId, @PathParam("projectId") String projectId) {
		
		Project project = repository.getProject(projectId);
		
		if (project==null)
			throw new NotFoundException("The project with id=" + projectId + " was not found");
			
		File toBeRemoved = repository.getFile(fileId);
		if (toBeRemoved == null) {
			throw new NotFoundException("The file with id="+ fileId +" was not found");
		}
		else {
			if(toBeRemoved.getParent()!=null) {
				repository.removeFileFromFolder(toBeRemoved.getParent(), toBeRemoved.getId());
			}
			else {
				repository.removeFileFromProject(projectId, toBeRemoved.getId());
			}
			repository.deleteFile(toBeRemoved.getId());
		}
		
		return Response.noContent().build();
	}
	
}
