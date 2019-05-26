package aiss.model.resource;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.MediaType;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import aiss.model.google.drive.ChildReference;
import aiss.model.google.drive.Comment;
import aiss.model.google.drive.CommentList;
import aiss.model.google.drive.FileItem;
import aiss.model.google.drive.Files;
import aiss.model.google.drive.Permission;
import aiss.model.google.drive.PermissionList;
import aiss.model.google.drive.Permissions;
import aiss.model.google.drive.Reply;
import aiss.model.google.drive.ReplyList;


public class GoogleDriveResource {
	
	public static final Logger log = Logger.getLogger(GoogleDriveResource.class.getName());
	
	private final String access_token;	
	private final String uri = "https://www.googleapis.com/drive/v2/files";
	private final String uri_upload = "https://www.googleapis.com/upload/drive/v3/files";
	private final String uri_upload2 = "https://www.googleapis.com/upload/drive/v2/files";
	private final String uri_perm = "https://www.googleapis.com/drive/v3/files/";
	private final String uri_perm2 = "https://www.googleapis.com/drive/v2/files/";
	
	public GoogleDriveResource(String access_token)
	{
		this.access_token = access_token;
	}
	
	public Files getFiles()
	{
		ClientResource cr = null;
		Files files = null;
		
		try
		{
			cr = new ClientResource(uri + "?access_token=" + access_token);
			files = cr.get(Files.class);
		}
		catch(ResourceException re)
		{
			log.warning("Error al conseguir todos los archivos " + cr.getResponse().getStatus());
		}
		
		return files;
	}
	
	public String CreaProyecto(FileItem file) {
		ClientResource cr = null;
		String newId = null;
		try {
			cr = new ClientResource(uri + "?access_token=" + access_token);
			FileItem newFile = cr.post(file, FileItem.class);
			newId = newFile.getId();
			cr = new ClientResource(uri_upload + "/" + newId + "?uploadType=media&access_token=" + access_token);
			
		} catch (ResourceException re) {
			log.warning("Error when inserting file: " + cr.getResponse().getStatus());
		}
		log.warning(file.getTitle() + ": " + newId);
		return newId;
	}
	
	public String insertarArchivo(FileItem file)
	{
		ClientResource cr = null;
		String newId = null;
		try {
			cr = new ClientResource(uri + "?access_token=" + access_token);
			FileItem newFile = cr.post(file, FileItem.class);
			newId = newFile.getId();
			cr = new ClientResource(uri_upload + "/" + newId + "?uploadType=media&access_token=" + access_token);
			
		} catch (ResourceException re) {
			log.warning("Error when inserting file: " + cr.getResponse().getStatus());
		}
		log.warning(file.getTitle() + ": " + newId);
		return newId;
	}

	public boolean borrarFile(String fileId) {
		ClientResource cr = null;
		boolean result = true;
		try {
			cr = new ClientResource(uri + "/" + fileId + "?access_token=" + access_token);
			cr.delete();
		} catch (ResourceException re) {
			log.warning("Error when deleting file: " + cr.getResponse().getStatus());
			result = false;
		}
		return result;
	}
	
	 public boolean updateFile(FileItem file) {

	        ClientResource cr = null;
	        boolean result = true;
	        try {
	            cr = new ClientResource(uri + "/" + file.getId() + "?access_token=" + access_token);
	            cr.put(file);
	        } catch (ResourceException re) {
	            log.warning("Error when updating file: " + cr.getResponse().getStatus());
	            result = false;
	        }
	        return result;
	    }

	 
	 public FileItem getFile(String id) {

	        ClientResource cr = null;
	        FileItem file = null;
	        try {
	            cr = new ClientResource(uri + "/" + id + "?access_token=" + access_token);
	            file = cr.get(FileItem.class);

	        } catch (ResourceException re) {
	            log.warning("Error when retrieving file: " + cr.getResponse().getStatus());
	        }

	        return file;

	    }   

	    public boolean deleteFile(String id) {

	        ClientResource cr = null;
	        boolean result = true;
	        try {
	            cr = new ClientResource(uri + "/" + id + "?access_token=" + access_token);
	            cr.delete();
	        } catch (ResourceException re) {
	            log.warning("Error when deleting file: " + cr.getResponse().getStatus());
	            result = false;
	        }
	        return result;

	    }

	    public String getFileContent(FileItem item) {
	        String result = null;
	        String contentURL = item.getDownloadUrl();
	        try {
	            ClientResource cr = new ClientResource(contentURL);
	            /*Map<String, Object> reqAttribs = cr.getRequestAttributes(); 
		        Series<Header> headers = (Series<Header>)reqAttribs.get("org.restlet.http.headers"); 
		        if (headers == null) { 
		            headers = new Series<Header>(Header.class); 
		            reqAttribs.put("org.restlet.http.headers", headers); 
		        } 
		        headers.add(new Header("Authorization:", "Bearer "+access_token));*/
	            ChallengeResponse chr = new ChallengeResponse(ChallengeScheme.HTTP_OAUTH_BEARER);
	            chr.setRawValue(access_token);
	            cr.setChallengeResponse(chr);

	            result = cr.get(String.class);
	        } catch (ResourceException re) {
	            log.warning("Error when obtaining the content of file: " + item.getId());
	        }
	        return result;
	    }

	    public boolean updateFileContent(String id, String content) {
	        ClientResource cr = new ClientResource(uri_upload2 + "/" + id + "?uploadType=media");
	        log.warning(uri_upload2 + "/" + id + "?uploadType=media");
	        try {
	            ChallengeResponse chr = new ChallengeResponse(ChallengeScheme.HTTP_OAUTH_BEARER);
	            chr.setRawValue(access_token);
	            cr.setChallengeResponse(chr);
	            StringRepresentation rep = new StringRepresentation(content, MediaType.TEXT_PLAIN);
	            cr.put(rep);
	        } catch (ResourceException re) {
	            log.warning("Error when updating the content of file: " + id);
	            log.warning(re.getMessage());
	            return false;
	        }
	        return true;
	    }
	    
	public String createPermission(String fileId, Permission p) {
		ClientResource cr = null;
		String newId = null;
		try {
			cr = new ClientResource(uri_perm  + fileId + "/permissions?access_token=" + access_token);
			Permission newPerm = cr.post(p, Permission.class);
			newId = newPerm.getId();
			
		} catch (ResourceException re) {
			log.warning("Error when permission file: " + cr.getResponse().getStatus());
		}
		return newId;
	}
	 
	public Permissions getPermissions(String fileId)
	{
		ClientResource cr = null;
		try {
			cr = new ClientResource(uri_perm  + fileId + "/permissions?access_token=" + access_token);
			Permissions newPerm = cr.get(Permissions.class);
			return newPerm;
			
		} catch (ResourceException re) {
			log.warning("Error when permission file: " + cr.getResponse().getStatus());
			return null;
		}
	}
	
	public PermissionList getPermissionsList(String fileId) {
    	ClientResource cr = null;
		PermissionList permissions = null;
		try{
			cr = new ClientResource(uri + "/" + fileId + "/permissions?access_token=" + access_token);
			permissions = cr.get(PermissionList.class);
		}
		catch(ResourceException re){
			log.warning("Error when retrieving the permissions: " + cr.getResponse().getStatus());
		}
		return permissions;
    }
	
	public Permission getPermission(String fileId, String permId)
	{
		ClientResource cr = null;
		try {
			cr = new ClientResource(uri_perm2  + fileId + "/permissions/" + permId + "?access_token=" + access_token);
			Permission newPerm = cr.get(Permission.class);
			return newPerm;
			
		} catch (ResourceException re) {
			log.warning("Error when permission file: " + cr.getResponse().getStatus());
			return null;
		}
	}
	
	
	public Boolean downloadFile(String id)
	{
		ClientResource cr = new ClientResource("https://www.googleapis.com/drive/v3/files/" + id + "?alt=media&access_token=" + access_token);
		ChallengeResponse chr = new ChallengeResponse(ChallengeScheme.HTTP_OAUTH_BEARER);
	    chr.setRawValue(access_token);
	    cr.setChallengeResponse(chr);
		
	    Boolean res = true;
		
	    try
		{
	    	log.warning("JAJA EL VEKTO: " + "https://www.googleapis.com/drive/v3/files/" + id + "?alt=media&access_token="+access_token);
			cr.get();
		}
		catch (ResourceException re)
		{
			res = false;
			log.warning("Error when permission file: " + cr.getResponse().getStatus());
		}
		
		return res;
	}
	
	public Files getSearchByTitle(String title) {
        ClientResource cr = null;
        Files busqueda = null;
        try {
        	cr = new ClientResource(uri + "?q=title%20%3D%20%27" + title +"%27");
        	ChallengeResponse chr = new ChallengeResponse(ChallengeScheme.HTTP_OAUTH_BEARER);
            chr.setRawValue(access_token);
            cr.setChallengeResponse(chr);
            busqueda = cr.get(Files.class);
        } 
        catch (ResourceException re) {
            log.warning("Error when retrieving the search: " + cr.getResponse().getStatus());
        }
        return busqueda;
    }

    public String insertFile(FileItem file, String content) {
        ClientResource cr = null;
        String newId = null;
        try {
            cr = new ClientResource(uri + "?access_token=" + access_token);
            FileItem newFile = cr.post(file, FileItem.class);
            newId = newFile.getId();
            cr = new ClientResource(uri_upload + "/" + newId + "?uploadType=media&access_token=" + access_token);
            Map<String, Object> headers = cr.getRequestAttributes();
            headers.put("Content-Type", "text/plain");
            cr.put(content);
        } 
        catch (ResourceException re) {
            log.warning("Error when inserting file: " + cr.getResponse().getStatus());
        }
        return newId;
    }
    
    public String insertComment(String fileId, Comment comment) {
    	ClientResource cr = null;
        String newId = null;
        try {
            cr = new ClientResource(uri + "/" + fileId + "/comments?access_token=" + access_token);
            Comment newComment = cr.post(comment, Comment.class);
            newId = newComment.getCommentId();
        } 
        catch (ResourceException re) {
            log.warning("Error when inserting the comment: " + cr.getResponse().getStatus());
        }
        return newId;
    }
    
    public CommentList getComments(String fileId) {
    	ClientResource cr = null;
        CommentList comments = null;
        try {
        	cr = new ClientResource(uri + "/" + fileId + "/comments?access_token=" + access_token);
        	comments = cr.get(CommentList.class);
        } 
        catch (ResourceException re) {
            log.warning("Error when retrieving the comments: " + cr.getResponse().getStatus());
        }
        return comments;
    }
    
    public String insertReply(String fileId, String commentId, Reply reply) {
    	ClientResource cr = null;
    	String newId = null;
    	try {
            cr = new ClientResource(uri + "/" + fileId + "/comments/" + commentId + "/replies?access_token=" + access_token);
            Reply newReply = cr.post(reply, Reply.class);
            newId = newReply.getReplyId();
        } 
        catch (ResourceException re) {
            log.warning("Error when inserting the reply: " + cr.getResponse().getStatus());
        }
        return newId;
    }
    
    public ReplyList getReplies(String fileId, String commentId) {
    	ClientResource cr = null;
        ReplyList replies = null;
        try {
        	cr = new ClientResource(uri + "/" + fileId + "/comments/" + commentId + "/replies?access_token=" + access_token);
            replies = cr.get(ReplyList.class);
        } 
        catch (ResourceException re) {
            log.warning("Error when retrieving the replies: " + cr.getResponse().getStatus());
        }
        return replies;
    }
    
    public String insertPermission(String fileId, Permission permission) {
    	ClientResource cr = null;
    	String newId = null;
    	try {
            cr = new ClientResource(uri + "/" + fileId + "/permissions?access_token=" + access_token);
            Permission newPermission = cr.post(permission, Permission.class);
            newId = newPermission.getId();
        } 
        catch (ResourceException re) {
            log.warning("Error when inserting the permission: " + cr.getResponse().getStatus());
        }
        return newId;
    }
    
    public boolean deletePermission(String fileId, String permissionId) {
        ClientResource cr = null;
        boolean result = true;
        try {
            cr = new ClientResource(uri + "/" + fileId + "/permissions/" + permissionId + "?access_token=" + access_token);
            cr.delete();
        } catch (ResourceException re) {
            log.warning("Error when deleting permission: " + cr.getResponse().getStatus());
            result = false;
        }
        return result;
    }
    
 
	
}
