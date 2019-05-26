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


public class GoogleDriveDeleteProyectController extends HttpServlet{
	
	private static final Logger log = Logger.getLogger(GoogleDriveDeleteProyectController.class.getName());

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String id = req.getParameter("id");
        if (id != null && !"".equals(id)) {
            String accessToken = (String) req.getSession().getAttribute("GoogleDrive-token");
            if (accessToken != null && !"".equals(accessToken)) {
                GoogleDriveResource gdResource = new GoogleDriveResource(accessToken);
                                
                gdResource.borrarFile(id);
                log.info("File with id '" + id + "' deleted!");
                resp.sendRedirect(req.getContextPath()+"/googleDriveFileList2");
                
            } else {
                log.info("Trying to access Google Drive without an access token, redirecting to OAuth servlet");
                req.getRequestDispatcher("/AuthController/GoogleDrive").forward(req, resp);
            }
        } else {
            log.warning("Invalid id for delete!");
            req.getRequestDispatcher("/googleDriveFileList2").forward(req, resp);
        }
    }
}