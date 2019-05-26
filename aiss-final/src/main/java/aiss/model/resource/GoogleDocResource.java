package aiss.model.resource;

import java.util.logging.Logger;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import aiss.model.google.doc.Document;

public class GoogleDocResource {
public static final Logger log = Logger.getLogger(GoogleDriveResource.class.getName());
	
	private final String access_token;	
	private final String uri = "https://docs.googleapis.com/v1/documents/";
		
	public GoogleDocResource(String access_token)
	{
		this.access_token = access_token;
	}
	
	public Document getDocs(String id)
	{
		ClientResource cr = null;
		Document doc = null;
		
		try
		{
			cr = new ClientResource(uri + id + "?access_token=" + access_token);
			doc = cr.get(Document.class);
		}
		catch(ResourceException re)
		{
	
			log.warning("Error:" + uri + id + "?access_token=" + access_token + "   --" + cr.getResponse().getStatus());
		}
		
		return doc;
	}
	
	public Boolean updateContentFile(String id)
	{
		String uri="https://docs.googleapis.com/v1/documents/"+id+":batchUpdate&access_token="+ access_token;
		ClientResource cr = null;
		Boolean res = false;
		
		try
		{
			cr = new ClientResource(uri);
			cr.post(Document.class);
			res = true;
		}
		catch(ResourceException re)
		{
	
			log.warning("Error:" + uri + id + "?access_token=" + access_token + "   --" + cr.getResponse().getStatus());
		}
		return res;
	}
}
