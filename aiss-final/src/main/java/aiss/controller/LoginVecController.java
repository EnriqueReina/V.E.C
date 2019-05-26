package aiss.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.google.drive.FileItem;
import aiss.model.google.drive.Files;
import aiss.model.google.drive.Permission;
import aiss.model.google.drive.Permissions;
import aiss.model.resource.GoogleDriveResource;


public class LoginVecController extends HttpServlet{
	
	private static final Logger log = Logger.getLogger(LoginVecController.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		String access_tokenGDr = (String) req.getSession().getAttribute("GoogleDrive-token");
		String access_tokenGC = (String) req.getSession().getAttribute("GoogleCalendar-token");
		String access_tokenGDc = (String) req.getSession().getAttribute("GoogleDoc-token");
		String access_tokenBB = (String) req.getSession().getAttribute("Bitbucket-token");
		
		if(access_tokenGDr != null && !"".equals(access_tokenGDr))
		{
			if(access_tokenGC != null && !"".equals(access_tokenGC)) 
			{
				if(access_tokenGDc != null && !"".equals(access_tokenGDc)) 
				{
					if(access_tokenBB != null && !"".equals(access_tokenBB)) 
					{
						req.getRequestDispatcher("/main.jsp").forward(req, res);
					}
					else
					{
						req.getRequestDispatcher("/AuthController/Bitbucket").forward(req, res);
					}
				}
				else
				{
					req.getRequestDispatcher("/AuthController/GoogleDoc").forward(req, res);
				}
			}
			else
			{
				req.getRequestDispatcher("/AuthController/GoogleCalendar").forward(req, res);
			}
		}
		else
		{
			req.getRequestDispatcher("/AuthController/GoogleDrive").forward(req, res);
		}
			
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		doGet(req,res);
	}

}