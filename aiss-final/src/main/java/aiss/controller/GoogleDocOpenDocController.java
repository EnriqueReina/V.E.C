package aiss.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.google.doc.Document;
import aiss.model.resource.GoogleDocResource;

public class GoogleDocOpenDocController extends HttpServlet{
	
private static final Logger log = Logger.getLogger(GoogleDocOpenDocController.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		String access_token = (String) req.getSession().getAttribute("GoogleDoc-token");
		String id = (String) req.getParameter("id");
		
		if(access_token != null && !"".equals(access_token))
		{
			GoogleDocResource gdResource = new GoogleDocResource(access_token);
			
			log.warning("Aski" + id);
			Document doc = gdResource.getDocs(id);
			
			if(doc != null)
			{
				req.setAttribute("doc", doc);
				req.getRequestDispatcher("/GoogleDocListing.jsp").forward(req, res);
			}
			else
			{
				log.warning("fkjsjfes");
				req.getRequestDispatcher("/GoogleDocListing.jsp").forward(req, res);
			}
		}
		else
		{
			req.getRequestDispatcher("/AuthController/GoogleDoc").forward(req, res);
		}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		doGet(req,res);
	}
}
