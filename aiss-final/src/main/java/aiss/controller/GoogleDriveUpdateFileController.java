package aiss.controller;

import aiss.model.google.drive.FileItem;
import aiss.model.resource.GoogleDriveResource;
import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoogleDriveUpdateFileController extends HttpServlet {

    private static final Logger log = Logger.getLogger(GoogleDriveDeleteProyectController.class.getName());

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    String id = req.getParameter("folderId");
    String esProyecto = req.getParameter("esProyecto");
    	
   	 log.warning("Id: " + esProyecto);
   	
       if (id != null && !"".equals(id)) {
           String accessToken = (String) req.getSession().getAttribute("GoogleDrive-token");
           String titulo = req.getParameter("folderName");
           if (accessToken != null && !"".equals(accessToken)) {
           	
           	GoogleDriveResource gdResource = new GoogleDriveResource(accessToken);
               FileItem file = gdResource.getFile(id);
               if(esProyecto.matches("yes"))
            	   file.setTitle("#AISSVEC-" + titulo);
               else
            	   file.setTitle(titulo);
               
               gdResource.updateFile(file);
               
               resp.sendRedirect(req.getContextPath() + "/googleDriveFileList2");
           } else {
               log.info("Trying to access Google Drive without an access token, redirecting to OAuth servlet");
               req.getRequestDispatcher("/AuthController/GoogleDrive").forward(req, resp);
           }
       } else {
           log.warning("Invalid id for update!");
           req.getRequestDispatcher("/googleDriveFileList2").forward(req, resp);
       }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    	doGet(req,resp);
    }
}