package aiss.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.bitbucket.Files;
import aiss.model.resource.BitbucketResource;

/**
 * Servlet implementation class FileListController
 */
public class FileListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(FileListController.class.getName());
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String accesToken = (String) req.getSession().getAttribute("Bitbucket-token");
		String reponame = req.getParameter("reponame");
		if(accesToken != null && !"".equals(accesToken) && reponame != null && !"".equals(reponame)) {
			BitbucketResource bbResource = new BitbucketResource(accesToken);
			Files files = bbResource.getFiles(reponame);
			req.setAttribute("files", files);
			req.setAttribute("reponame", reponame);
			req.getRequestDispatcher("fileListing.jsp").forward(req, resp);
			
		}else {
			req.getRequestDispatcher("/AuthController/Bitbucket").forward(req, resp);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
