package aiss.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.google.drive.FileItem;
import aiss.model.google.drive.Files;
import aiss.model.google.drive.Parent;
import aiss.model.google.drive.Permission;
import aiss.model.resource.GoogleDriveResource;


public class GoogleDriveNewProyectController extends HttpServlet{
	
	private static final Logger log = Logger.getLogger(GoogleDriveNewProyectController.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String accessToken = (String) req.getSession().getAttribute("GoogleDrive-token");
        String title = "#AISSVEC-" + req.getParameter("nombre");
               
        
        if (accessToken != null && !"".equals(accessToken)) {
            if (title != null && !"".equals(title)) {
            	Boolean test = false;
            	GoogleDriveResource gdResource = new GoogleDriveResource(accessToken);
                Files files = gdResource.getFiles();
                                
                for(FileItem f: files.getItems())
                {
                	if(title.matches(f.getTitle()))
                	{
                		test = true;
                		break;
                	}
                }
                
                if(!test)
                {
	                String collabs[] = req.getParameterValues("collab");
	                List<String> collabsList = new ArrayList<>();
	              
	                
	                for(String s: collabs)
	                {
	                	s = s.trim();
	                	if(s == null || s.matches(""))
	                		break;
	                	else
	                		collabsList.add(s);
	                }	                
	                          	
                	FileItem rootFolder = new FileItem();
	                rootFolder.setTitle(title);
	                rootFolder.setMimeType("application/vnd.google-apps.folder");
	                String rootFolderId = gdResource.CreaProyecto(rootFolder);         

	                FileItem configFolder = new FileItem();
	                
	                List<Parent> p = new ArrayList<>();
	                Parent par = new Parent();
	                par.setId(rootFolderId);
	                p.add(par);
	                
	                configFolder.setParents(p);
	                configFolder.setTitle("CONFIG(" + req.getParameter("nombre") + ")");
	                configFolder.setMimeType("application/vnd.google-apps.folder");
	                String configFolderId = gdResource.CreaProyecto(configFolder);
	                
	                FileItem chatFile = new FileItem();
	                
	                List<Parent> p2 = new ArrayList<>();
	                Parent par2 = new Parent();
	                par2.setId(configFolderId);
	                p2.add(par2);
	                
	                chatFile.setParents(p2);
	                chatFile.setTitle("VECchat(" + req.getParameter("nombre") + ")");
	                chatFile.setMimeType("text/plain");
	                String newID = gdResource.CreaProyecto(chatFile);
	                
	                //Permissions
	              	               
	                String getCollabFormat = "";
	                if(!collabsList.isEmpty())
	                {	                	
	                	for(String s: collabsList)
	                	{	               
	                		
	                		Permission newPerm = new Permission();
	                		newPerm.setRole("writer");
	                		newPerm.setType("user");
	                		newPerm.setEmailAddress(s);
	                		gdResource.createPermission(rootFolderId, newPerm);	     
	                		
	                		s.trim();
	                		getCollabFormat += "&collab="+s;
	                	}
	                }
	                else
	                {
	                	getCollabFormat="&collab=";
	                }
	                	                
	                req.setAttribute("message", "File '" + title + "' added to your Drive!");
	                
	                //resp.sendRedirect(req.getContextPath()+"/googleDriveFileList");
	                resp.sendRedirect(req.getContextPath()+"/googleDriveNewChat?proyectTitle="+ req.getParameter("nombre")+"&fileId="+newID+getCollabFormat);
                }
                else
                {
                	 req.setAttribute("message", "Ya existe un proyecto con ese nombre");
                     req.getRequestDispatcher("/googleDriveFileList2").forward(req, resp);
                }
            } else {
                req.setAttribute("message", "You must provide a valid title for file");
                req.getRequestDispatcher("/googleDriveFileList2").forward(req, resp);
            }
        } else {
            log.info("Trying to access Google Drive without an access token, redirecting to OAuth servlet");
            req.getRequestDispatcher("/AuthController/GoogleDrive").forward(req, resp);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doGet(req, resp);
    }
}