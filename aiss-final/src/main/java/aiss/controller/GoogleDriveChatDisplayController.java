package aiss.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.google.drive.Files;
import aiss.model.google.drive.ReplyList;
import aiss.model.resource.GoogleDriveResource;

@SuppressWarnings("serial")
public class GoogleDriveChatDisplayController extends HttpServlet {
	
	private static final Logger log = Logger.getLogger(GoogleDriveChatDisplayController.class.getName());

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String accessToken = (String) req.getSession().getAttribute("GoogleDrive-token");
		
		String collabs[] = req.getParameterValues("collab");
		String getFormat = "";
		
		if(collabs != null)
		{
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
		}
		
		if (accessToken != null && !"".equals(accessToken)) {
			GoogleDriveResource gdResource = new GoogleDriveResource(accessToken);
			String fileId = req.getParameter("fileId");
			String chatId = req.getParameter("chatId");
			String proyectTitle = req.getParameter("proyectTitle");
			if(proyectTitle!=null) {
				if(proyectTitle.startsWith("#AISSVEC-")) {
					proyectTitle = proyectTitle.substring(9);
				}
				Files files = gdResource.getSearchByTitle("VECchat(" + proyectTitle + ")");
				if(files!=null) {
					if(!files.getItems().isEmpty()) {
						fileId = files.getItems().get(0).getId();
						chatId = gdResource.getComments(fileId).getItems().get(0).getCommentId();
						log.info("Chat found, displaying.");
						ReplyList chat = gdResource.getReplies(fileId, chatId);
						req.setAttribute("proyectTitle", proyectTitle);
						req.setAttribute("fileId", fileId);
						req.setAttribute("chatId", chatId);
						req.setAttribute("chat", chat);
						//req.getRequestDispatcher("/googleDriveChat.jsp").forward(req, res);
						
						if(req.getParameter("picked")!=null)
						{
							log.severe("ASKI");
							req.getRequestDispatcher("/googleDriveChat2.jsp").forward(req, res);
						}
						else
						{
							res.sendRedirect(req.getContextPath()+"/googleCalendarNewCalendar?proyectTitle="+proyectTitle+getFormat);
						}
						
					}
					else {
						log.info("The chatIDs are null. Creating new chat.");
						req.setAttribute("proyectTitle", proyectTitle);
						req.setAttribute("collaborator", req.getParameterValues("collaborator"));
						//req.getRequestDispatcher("/googleDriveNewChat").forward(req, res);
						
						if(req.getParameter("picked")!=null)
						{
							res.sendRedirect(req.getContextPath()+"/googleDriveChat2.jsp");
						}
						else
						{
							res.sendRedirect(req.getContextPath()+"/googleCalendarEventList?proyectTitle="+proyectTitle+getFormat);
						}
					}
				}
				else {
					log.info("Token expired, redirecting to OAuth servlet");
					req.getRequestDispatcher("/AuthController/GoogleDrive").forward(req, res);
				}
			}
			else {
				log.info("Proyect title is null, select one");
				//req.getRequestDispatcher("/googleDriveChat.jsp").forward(req, res);
				res.sendRedirect(req.getContextPath()+"/googleDriveFileList2");
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
