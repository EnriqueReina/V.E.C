package aiss.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.bitbucket.FileItem;
import aiss.model.bitbucket.Files;
import aiss.model.bitbucket.Repos;
import aiss.model.resource.BitbucketResource;

/**
 * Servlet implementation class DirectoryController
 */
public class DirectoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(DirectoryController.class.getName());
    
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String accessToken = (String) req.getSession().getAttribute("Bitbucket-token");
		String dir = req.getParameter("dir");
		String reponame = req.getParameter("reponame");
		String commitId = req.getParameter("commitId");
		if (accessToken != null && !"".equals(accessToken)) {
        	
        	log.warning("error2");
            BitbucketResource bbResource = new BitbucketResource(accessToken);
            Files directory = bbResource.getDirectoryContent(dir,commitId,reponame);
            req.setAttribute("dir", directory);
            log.warning(directory.toString());
            req.getRequestDispatcher("childs2.jsp").forward(req, resp);
           
        
    } else {
        log.warning("error1");
        req.getRequestDispatcher("/AuthController/Bitbucket").forward(req, resp);
    }
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
