package aiss.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.bitbucket.ReposItem;
import aiss.model.resource.BitbucketResource;

/**
 * Servlet implementation class NewRepoController
 */
public class NewRepoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger log = Logger.getLogger(NewRepoController.class.getName());
    
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String accesToken = (String) req.getSession().getAttribute("Bitbucket-token");
		String reponame = req.getParameter("reponame");
		reponame = reponame.toLowerCase();
		if(accesToken != null && !"".equals(accesToken)) {
			if(reponame != null && !"".equals(reponame)) {
				BitbucketResource bbResource = new BitbucketResource(accesToken);
				ReposItem repos = new ReposItem();
				repos.setName(reponame);
				bbResource.postRepos(reponame);
				req.getRequestDispatcher("/repoLists").forward(req, resp);
			}else {
				req.setAttribute("message", "You must provide a valid title for r");
               
                req.getRequestDispatcher("/repoListing2.jsp").forward(req, resp);
			}
		}else {
			log.info("Trying to access Bitbucket without an access token, redirecting to OAuth servlet");
            req.getRequestDispatcher("/AuthController/Bitbucket").forward(req, resp);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
