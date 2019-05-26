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


public class GoogleDriveFileListController2 extends HttpServlet{
	
	private static final Logger log = Logger.getLogger(GoogleDriveFileListController2.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		String access_token = (String) req.getSession().getAttribute("GoogleDrive-token");
		
		if(access_token != null && !"".equals(access_token))
		{
			GoogleDriveResource gdResource = new GoogleDriveResource(access_token);
			Map<String,List<Permission>> perm = new TreeMap<>();
			Files files = gdResource.getFiles();
			
			if(files != null)
			{
				req.setAttribute("files", files);
				
				for(FileItem f: files.getItems())
				{
					List<Permission> permList = new ArrayList<>();
					if(f.getMimeType().matches("application/vnd.google-apps.folder") && f.getTitle().contains("#AISSVEC"))
					{
						Permissions p = gdResource.getPermissions(f.getId());
						List<Permission> p2 = p.getPermissions();
						
						for(Permission p3: p2)
						{	
							Permission tempP = gdResource.getPermission(f.getId(), p3.getId());
							permList.add(tempP);
						}
						
						perm.put(f.getId(), permList);
					}
				}
				
				req.setAttribute("perm", perm);

				req.getRequestDispatcher("/GoogleDriveFileListing2.jsp").forward(req, res);
			}
			else
			{
				req.getRequestDispatcher("/AuthController/GoogleDrive").forward(req, res);
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