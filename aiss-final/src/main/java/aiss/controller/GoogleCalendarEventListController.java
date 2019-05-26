package aiss.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.google.calendar.EventList;
import aiss.model.resource.GoogleCalendarResource;

@SuppressWarnings("serial")
public class GoogleCalendarEventListController extends HttpServlet {
	
	private static final Logger log = Logger.getLogger(GoogleCalendarEventListController.class.getName());
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String accessToken = (String) req.getSession().getAttribute("GoogleCalendar-token");
		if (accessToken != null && !"".equals(accessToken)) {
			log.warning("ASKI estanis ACCESS TOKEN encontradoBRO: " + accessToken);
			GoogleCalendarResource gcResource = new GoogleCalendarResource(accessToken);
			String proyectTitle = req.getParameter("proyectTitle");
			log.warning("Project Title xD: " + proyectTitle);
			if(proyectTitle!=null && !proyectTitle.equals("")) {
				String calendarId = req.getParameter("calendarId");
				
				
				if(calendarId==null || calendarId.equals("")) {
					calendarId = gcResource.getProyectCalendar(proyectTitle);
				}
				if(calendarId==null) {
					log.info("Calendar not found, creating a new one");
					req.setAttribute("proyectTitle", proyectTitle);				
					req.setAttribute("collaborator", req.getParameterValues("collab"));
					req.getRequestDispatcher("/googleCalendarNewCalendar").forward(req, res);
				}
				else if(calendarId=="tokenExpired") {
					log.info("Token has expired, redirecting to OAuth servlet");
					req.getRequestDispatcher("/AuthController/GoogleCalendar").forward(req, res);
				}
				else {
					EventList events = gcResource.getEventList(calendarId);
					if(events!=null) {
						req.setAttribute("nameNull", req.getParameter("nameNull"));
						req.setAttribute("calendarId", calendarId);
						req.setAttribute("events", events);
						req.setAttribute("proyectTitle", proyectTitle);
						req.getRequestDispatcher("googleCalendarEventList2.jsp").forward(req, res);
					}
					else {
						log.info("The events returned are null... probably your token has experied. Redirecting to OAuth servlet.");
		                req.getRequestDispatcher("/AuthController/GoogleCalendar").forward(req, res);
					}
				}
			}
			else {
				log.info("Proyect Title is null. Redirecting to select page.");
				req.getRequestDispatcher("googleCalendarEventList2.jsp").forward(req, res);
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
