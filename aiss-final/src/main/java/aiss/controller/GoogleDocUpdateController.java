package aiss.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.google.drive.Files;
import aiss.model.google.drive.Permission;
import aiss.model.resource.GoogleDocResource;
import aiss.model.resource.GoogleDriveResource;

@SuppressWarnings("serial")
public class GoogleDocUpdateController extends HttpServlet {
	
	private static final Logger log = Logger.getLogger(GoogleDocUpdateController.class.getName());

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String accessToken = (String) req.getSession().getAttribute("GoogleDoc-token");
		if (accessToken != null && !"".equals(accessToken)) {
			GoogleDocResource gdResource = new GoogleDocResource(accessToken);
			String docId = req.getParameter("documentId");
			gdResource.updateContentFile(docId);
			res.sendRedirect(req.getContextPath()+"/googleDocList?id="+docId);
		
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
