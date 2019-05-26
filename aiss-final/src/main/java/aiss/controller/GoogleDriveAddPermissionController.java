package aiss.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.google.drive.Permission;
import aiss.model.resource.GoogleDriveResource;

/**
 * Servlet implementation class GoogleDriveAddPermission
 */
@SuppressWarnings("serial")
public class GoogleDriveAddPermissionController extends HttpServlet {
	
	private static final Logger log = Logger.getLogger(GoogleDriveAddPermissionController.class.getName());

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String accessToken = (String) req.getSession().getAttribute("GoogleDrive-token");
		if (accessToken != null && !"".equals(accessToken)) {
			GoogleDriveResource gdResource = new GoogleDriveResource(accessToken);
			String newCommenter = req.getParameter("newCommenter");
			if(newCommenter != null && !"".equals(newCommenter)) {
				String chatId = req.getParameter("chatId");
				String fileId = req.getParameter("fileId");
				Permission permission = new Permission();
				permission.setRole("reader");
				permission.setType("user");
				permission.setValue(newCommenter);
				List<String> roles = new ArrayList<>();
				roles.add("commenter");
				permission.setAdditionalRoles(roles);
				gdResource.insertPermission(fileId, permission);
				res.sendRedirect(req.getContextPath() + "/googleDriveChatDisplay?fileId=" + fileId + "&chatId=" + chatId);
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
