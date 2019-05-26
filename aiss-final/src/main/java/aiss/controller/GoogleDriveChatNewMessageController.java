package aiss.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.google.drive.Reply;
import aiss.model.resource.GoogleDriveResource;

/**
 * Servlet implementation class GoogleDriveChatNewMessage
 */
@SuppressWarnings("serial")
public class GoogleDriveChatNewMessageController extends HttpServlet {
	
	private static final Logger log = Logger.getLogger(GoogleDriveChatNewMessageController.class.getName());

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String accessToken = (String) req.getSession().getAttribute("GoogleDrive-token");
		if (accessToken != null && !"".equals(accessToken)) {
			GoogleDriveResource gdResource = new GoogleDriveResource(accessToken);
			String proyectTitle = req.getParameter("proyectTitle");
			String chatId = req.getParameter("chatId");
			String fileId = req.getParameter("fileId");
			String chatMessage = req.getParameter("chatMessage");
			Reply reply = new Reply();
			reply.setContent(chatMessage);
			if(chatMessage!=null && !"".equals(chatMessage)) {
				String replyId = gdResource.insertReply(fileId, chatId, reply);
				if(replyId==null) {
					log.info("Token expired, redirecting to OAuth servlet");
					req.getRequestDispatcher("/AuthController/GoogleDrive").forward(req, res);
				}
			}
			res.sendRedirect(req.getContextPath() + "/googleDriveChatDisplay?fileId=" + fileId + "&chatId=" + chatId + "&proyectTitle=" + proyectTitle + "&picked=true");
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
