package aiss.model.resource;

import java.util.logging.Logger;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import aiss.model.bitbucket.FileItem;
import aiss.model.bitbucket.Files;
import aiss.model.bitbucket.Owner;
import aiss.model.bitbucket.Repos;
import aiss.model.bitbucket.ReposItem;

public class BitbucketResource {
	private static final Logger log = Logger.getLogger(BitbucketResource.class.getName());

    private final String access_token;
    private final String uri = "https://api.bitbucket.org/2.0/";
    
  
    

    public BitbucketResource(String access_token) {
        this.access_token = access_token;
    }
    public Files getDirectoryContent(String dir,String commitId,String reponame) {
    	Files res = null;
    	ClientResource cr = null;
    	try {
    		cr = new ClientResource(uri+"user/?access_token="+access_token);
    		Owner users = cr.get(Owner.class);
    		String username = users.getUsername(); 
    		cr = new ClientResource(uri+"repositories/"+username+"/"+reponame+"/src/"+commitId+"/"+dir);
    		log.warning(uri+"repositories/"+username+"/"+reponame+"/src/"+commitId+"/"+dir);
    		res = cr.get(Files.class);
    	}catch(ResourceException re){
    		log.warning("Error when retrieving all files: " + cr.getResponse().getStatus());
    	}
    	return res;
    }
    
    public ReposItem postRepos(String reponame) {
    	ClientResource cr = null;
    	ReposItem repos = null;
    	try {
        	log.warning(access_token);
            cr = new ClientResource(uri + "user/?access_token="+access_token);
            Owner users = cr.get(Owner.class);
            String accountId = users.getAccountId();
           
            cr = new  ClientResource(uri+ "repositories/"+accountId+"/"+reponame+"?access_token="+access_token); 
            repos = cr.post(repos,ReposItem.class);
        } catch (ResourceException re) {
            log.warning("Error when retrieving all files: " + cr.getResponse().getStatus());
        }

        return repos;
    }
    public Boolean deleteRepos(String reponame) {
    	ClientResource cr = null;
    	Boolean res = true;
    	try {
        	log.warning(access_token);
        
            cr = new ClientResource(uri + "user/?access_token="+access_token);
            Owner users = cr.get(Owner.class);
            String accountId = users.getUsername();
           
            cr = new  ClientResource(uri+"repositories/"+accountId+"/"+reponame+"/?access_token="+access_token);
            cr.delete();
        } catch (ResourceException re) {
            log.warning("Error when retrieving all files: " + cr.getResponse().getStatus());
            res =  false;
        }

        return res;
    }
    public Repos getRepos() {
    	ClientResource cr = null;
        Repos repos = null;
        try {
        	log.warning(access_token);
            cr = new ClientResource(uri + "user/?access_token="+access_token);
            Owner users = cr.get(Owner.class);
            String accountId = users.getUsername();
           
            cr = new  ClientResource(uri+ "repositories/"+accountId); 
            repos = cr.get(Repos.class);
        } catch (ResourceException re) {
            log.warning("Error when retrieving all files: " + cr.getResponse().getStatus());
        }

        return repos;
    }
    public Owner getUserInfo() {
    	ClientResource cr = null;
        Owner user = null;
        try {
        	log.warning(access_token);
            cr = new ClientResource(uri+"user/?access_token="+access_token);
            user = cr.get(Owner.class);

        } catch (ResourceException re) {
            log.warning("Error when retrieving all files: " + cr.getResponse().getStatus());
        }

        return user;
    }
	public Files getFiles(String reponame) {
		ClientResource cr = null;
        Files files = null;
        try {
        	log.warning(access_token);
            cr = new ClientResource(uri+"user/?access_token="+access_token);
            Owner user  = cr.get(Owner.class);
            String accountId = user.getUsername();
            cr = new ClientResource(uri+"repositories/"+accountId+"/"+reponame+"/src");
            files = cr.get(Files.class);
        } catch (ResourceException re) {
            log.warning("Error when retrieving all files: " + cr.getResponse().getStatus());
        }

        return files;
	} 
	
}
