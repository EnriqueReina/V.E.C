package aiss.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.resource.GoogleCalendarResource;

@SuppressWarnings("serial")
public class GoogleCalendarDeleteCalendarController extends HttpServlet {
	
	private static final Logger log = Logger.getLogger(GoogleCalendarDeleteCalendarController.class.getName());

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String accessToken = (String) req.getSession().getAttribute("GoogleCalendar-token");
		if (accessToken != null && !"".equals(accessToken)) {
			String proyectTitle = req.getParameter("proyectTitle").trim();
			log.severe("ASKIIIIIIIIIIII");
			if(proyectTitle!=null && !"".equals(proyectTitle)) {
				GoogleCalendarResource gcResource = new GoogleCalendarResource(accessToken);
				String calendarId = gcResource.getProyectCalendar(proyectTitle);
				log.severe("Project ID: "+ calendarId);
				
				Boolean exito = gcResource.deleteCalendar(calendarId);
				
				if(exito)
					log.warning("Se borro correctamente");
				
				req.getRequestDispatcher("/googleCalendarEventList2.jsp").forward(req, res);
			}
			else {
				log.info("Proyect Title is null. Redirecting to select page.");
				req.getRequestDispatcher("/googleCalendarEventList2.jsp").forward(req, res);
			}
			
		}
		else {
			log.info("Trying to access Google Calendar without an access token, redirecting to OAuth servlet");
			req.getRequestDispatcher("/AuthController/GoogleCalendar").forward(req, res);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
