package aiss.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.google.drive.ChildReference;
import aiss.model.google.drive.FileItem;
import aiss.model.google.drive.Files;
import aiss.model.google.drive.Parent;
import aiss.model.resource.GoogleDriveResource;

public class GoogleDriveInsertFileController extends HttpServlet {

	private static final Logger log = Logger.getLogger(GoogleDriveInsertFileController.class.getName());

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String accessToken = (String) req.getSession().getAttribute("GoogleDrive-token");
		String parentId = (String) req.getParameter("parentId");
		String folderName = (String) req.getParameter("folderName");
		String folderType = (String) req.getParameter("folderType");

		if (accessToken != null && !"".equals(accessToken)) {

			GoogleDriveResource gdResource = new GoogleDriveResource(accessToken);

			List<Parent> parents = new ArrayList<>();
			Parent p = new Parent();
			p.setId(parentId);
			parents.add(p);

			FileItem newFile = new FileItem();
			newFile.setTitle(folderName);
			newFile.setMimeType(folderType);
			newFile.setParents(parents);

			gdResource.insertarArchivo(newFile);

			resp.sendRedirect(req.getContextPath() + "/googleDriveFileList2");

		} else {
			req.setAttribute("message", "Ya existe un proyecto con ese nombre");
			req.getRequestDispatcher("/googleDriveFileList2").forward(req, resp);
		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doGet(req, resp);
	}
}