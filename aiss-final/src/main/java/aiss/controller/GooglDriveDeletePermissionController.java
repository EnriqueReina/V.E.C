package aiss.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.google.drive.Permission;
import aiss.model.resource.GoogleDriveResource;

/**
 * Servlet implementation class GooglDriveDeletePermissionController
 */
public class GooglDriveDeletePermissionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	
	private static final Logger log = Logger.getLogger(GooglDriveDeletePermissionController.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String update = req.getParameter("actualizar");
		if( update == null || update == "")
		{		
			String id[] = req.getParameterValues("checkEmail");
	        String fileId = req.getParameter("fileId");
	        List<String> l = Arrays.asList(id);
	        log.warning("ID a borrar: " + l.toString());
	        for(String s: id)
	        {	        
		        if (s != null && !"".equals(s)) {
		            String accessToken = (String) req.getSession().getAttribute("GoogleDrive-token");
		            if (accessToken != null && !"".equals(accessToken)) {
		                GoogleDriveResource gdResource = new GoogleDriveResource(accessToken);
		                gdResource.deletePermission(fileId, s);
		         
		                resp.sendRedirect(req.getContextPath()+"/googleDriveFileList2");
		            } else {
		               
		                req.getRequestDispatcher("/AuthController/GoogleDrive").forward(req, resp);
		            }
		        } else {
		           
		            req.getRequestDispatcher("/googleDriveFileList2").forward(req, resp);
		        }
	        }
		}
		else
		{
			String id[] = req.getParameterValues("collab");
			String title = req.getParameter("proyectTitle");
			String collabs = "";
	        String fileId = req.getParameter("fileId");
	        List<String> collabsList = Arrays.asList(id);
			
	        String accessToken = (String) req.getSession().getAttribute("GoogleDrive-token");
	        
	        
            if (accessToken != null && !"".equals(accessToken)) {
                GoogleDriveResource gdResource = new GoogleDriveResource(accessToken);
	        
				 if(!collabsList.isEmpty())
	             {	                	
	             	for(String s: collabsList)
	             	{	             	
	             		Permission newPerm = new Permission();
	             		newPerm.setRole("writer");
	             		newPerm.setType("user");
	             		newPerm.setEmailAddress(s);
	             		gdResource.createPermission(fileId, newPerm);	  
	             		
	             		s = s.trim();
	             		collabs += "&collaborator=" + s;
	             	}
	             }
				 
				  resp.sendRedirect(req.getContextPath()+"/googleCalendarUpdateCalendar?proyectTitle="+title+collabs);
            }
            else
            {
            	req.getRequestDispatcher("/AuthController/GoogleDrive").forward(req, resp);
            }
		}
	}



}
