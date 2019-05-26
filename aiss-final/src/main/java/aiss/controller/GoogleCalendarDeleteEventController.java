package aiss.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.resource.GoogleCalendarResource;


@SuppressWarnings("serial")
public class GoogleCalendarDeleteEventController extends HttpServlet {
	
	private static final Logger log = Logger.getLogger(GoogleCalendarDeleteEventController.class.getName());

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String accessToken = (String) req.getSession().getAttribute("GoogleCalendar-token");
		if (accessToken != null && !"".equals(accessToken)) {
			String calendarId = req.getParameter("calendarId");
			String eventId = req.getParameter("eventId");
			String proyectTitle = req.getParameter("proyectTitle");
			GoogleCalendarResource gcResource = new GoogleCalendarResource(accessToken);
			Boolean success = gcResource.deleteEvent(calendarId, eventId);
			if(!success) {
				log.info("Delete failed probably token has expired, redirecting to OAuth servlet");
				req.getRequestDispatcher("/AuthController/GoogleCalendar").forward(req, res);
			}
			else {
				req.setAttribute("proyectTitle", proyectTitle);
				req.getRequestDispatcher("/googleCalendarEventList").forward(req, res);
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
