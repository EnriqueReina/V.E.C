package aiss.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.bitbucket.Repos;
import aiss.model.resource.BitbucketResource;






/**
 * Servlet implementation class RepoListController
 */
public class RepoListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(RepoListController.class.getName());

   
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
       
            String accessToken = (String) req.getSession().getAttribute("Bitbucket-token");
            
            if (accessToken != null && !"".equals(accessToken)) {
                BitbucketResource bbResource = new BitbucketResource(accessToken);
                Repos repos = bbResource.getRepos();
                req.setAttribute("repos", repos);
                req.getRequestDispatcher("repoListing2.jsp").forward(req, resp);
               
            
        } else {
            req.getRequestDispatcher("/AuthController/Bitbucket").forward(req, resp);
        }
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}