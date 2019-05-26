package aiss.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.google.drive.Comment;
import aiss.model.google.drive.FileItem;
import aiss.model.google.drive.Files;
import aiss.model.google.drive.Parent;
import aiss.model.google.drive.Permission;
import aiss.model.resource.GoogleDriveResource;

@SuppressWarnings("serial")
public class GoogleDriveNewChatController extends HttpServlet {
	
	private static final Logger log = Logger.getLogger(GoogleDriveNewChatController.class.getName());

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String accessToken = (String) req.getSession().getAttribute("GoogleDrive-token");
		
		String collabs[] = req.getParameterValues("collab");
		String getFormat = "";
		
		log.warning("Size: "+collabs.length);
		
		if(collabs.length > 0)
		{
			for(String s:collabs)
			{
				s = s.trim();
				getFormat += "&collab="+s;
			}
		}
		else
		{
			getFormat = "&collab=";
		}
		
		log.warning("Formato: "+getFormat);
		
		if (accessToken != null && !"".equals(accessToken)) {
			GoogleDriveResource gdResource = new GoogleDriveResource(accessToken);
			String proyectTitle = req.getParameter("proyectTitle");
			if(proyectTitle.startsWith("#AISSVEC-")) {
				proyectTitle = proyectTitle.substring(9);
			}
			Files busquedaChatFolder = gdResource.getSearchByTitle("CONFIG(" + proyectTitle + ")");
			if(busquedaChatFolder!=null && !busquedaChatFolder.getItems().isEmpty()) {
				String configId = busquedaChatFolder.getItems().get(0).getId();
				String fileId = req.getParameter("fileId");
				Files files = gdResource.getSearchByTitle("VECchat(" + proyectTitle + ")");
				
				if(files.getItems().isEmpty()) {
					log.info("Creating support file.");
					FileItem file = new FileItem();
					file.setTitle("VECchat(" + proyectTitle + ")");
					file.setMimeType("text/plain");
					List<Parent> parents = new ArrayList<>();
					Parent p = new Parent();
					p.setId(configId);
					parents.add(p);
					file.setParents(parents);
					fileId = gdResource.insertFile(file, " ");
				}
				Comment initChat = new Comment();
				initChat.setContent(" ");
				String chatId = gdResource.insertComment(fileId, initChat);
				String[] collaborators = req.getParameterValues("collaborator");
				if(collaborators!=null && !(collaborators.length==0)) {
					for(String email : collaborators) {
						Permission permission = new Permission();
						permission.setRole("reader");
						permission.setType("user");
						permission.setValue(email);
						List<String> roles = new ArrayList<>();
						roles.add("commenter");
						permission.setAdditionalRoles(roles);
						gdResource.insertPermission(fileId, permission);
					}
				}
				
				
				res.sendRedirect(req.getContextPath() + "/googleDriveChatDisplay?fileId=" + fileId + "&chatId=" + chatId + "&proyectTitle=" + proyectTitle + getFormat);
			}
			else {
				log.info("Token expired or you have deleted the config folder, redirecting to OAuth servlet");
				req.getRequestDispatcher("/AuthController/GoogleDrive").forward(req, res);
			}

		}
		else {
			log.info("Trying to access Google Drive without an access token, redirecting to OAuth servlet");
			req.getRequestDispatcher("/AuthController/GoogleDrive").forward(req, res);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
