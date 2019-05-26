package aiss.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.resource.BitbucketResource;




/**
 * Servlet implementation class RepoDeleteController
 */
public class RepoDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(RepoDeleteController.class.getName());

   
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String reponame = req.getParameter("reponame");
		if (reponame != null && !"".equals(reponame)) {
            String accessToken = (String) req.getSession().getAttribute("Bitbucket-token");
            if (accessToken != null && !"".equals(accessToken)) {
            	
                BitbucketResource bbResource = new BitbucketResource(accessToken);
                bbResource.deleteRepos(reponame);
                
                	req.getRequestDispatcher("/repoLists").forward(req, res);
                
            
            } else {
                log.info("Trying to access Bitbucket without an access token, redirecting to OAuth servlet");
                req.getRequestDispatcher("/AuthController/Bitbucket").forward(req, res);
            }
        } else {
            log.warning("Invalid id for delete!");
            req.getRequestDispatcher("/RepoListController").forward(req, res);
        }
	}

	
	
}
