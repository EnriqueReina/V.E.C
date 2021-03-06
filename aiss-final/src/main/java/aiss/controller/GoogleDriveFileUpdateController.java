package aiss.controller;

import aiss.model.google.drive.FileItem;
import aiss.model.resource.GoogleDriveResource;
import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoogleDriveFileUpdateController extends HttpServlet {

    private static final Logger log = Logger.getLogger(GoogleDriveFileUpdateController.class.getName());

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String id = req.getParameter("id");
        String content = req.getParameter("content");
        if (id != null && !"".equals(id)) {
            String accessToken = (String) req.getSession().getAttribute("GoogleDrive-token");
            if (accessToken != null && !"".equals(accessToken)) {
                GoogleDriveResource gdResource = new GoogleDriveResource(accessToken);
                gdResource.updateFileContent(id, content);
                resp.sendRedirect(req.getContextPath()+"/googleDocList?id="+ id);
            } else {
                log.info("Trying to access Google Drive without an access token, redirecting to OAuth servlet");
                req.getRequestDispatcher("/AuthController/GoogleDrive").forward(req, resp);
            }
        } else {
            log.warning("Invalid id for update!");
            req.getRequestDispatcher("/googleDriveFileList").forward(req, resp);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
       
        doGet(req,resp);
    }
}
