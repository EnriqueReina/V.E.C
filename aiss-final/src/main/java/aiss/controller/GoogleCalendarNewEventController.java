package aiss.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.google.calendar.EndPost;
import aiss.model.google.calendar.EventPost;
import aiss.model.google.calendar.StartPost;
import aiss.model.resource.GoogleCalendarResource;

@SuppressWarnings("serial")
public class GoogleCalendarNewEventController extends HttpServlet {
	
	private static final Logger log = Logger.getLogger(GoogleCalendarNewEventController.class.getName());

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String accessToken = (String) req.getSession().getAttribute("GoogleCalendar-token");
		if (accessToken != null && !"".equals(accessToken)) {
			String calendarId = req.getParameter("calendarId");
			String proyectTitle = req.getParameter("proyectTitle");
			String eventName = req.getParameter("eventName").trim();
			GoogleCalendarResource gcResource = new GoogleCalendarResource(accessToken);
			EventPost newEvent = new EventPost();
			StartPost start = new StartPost();
			EndPost end = new EndPost();
			String eventDate = req.getParameter("eventDate");
			String eventTime = req.getParameter("eventTime");
			if(eventTime!=null && !"".equals(eventTime)) {
				if(eventTime.length()==4) {
					eventTime="0"+eventTime;
				}
				start.setDateTime(eventDate+"T"+eventTime+":00+02:00");
				end.setDateTime(eventDate+"T"+eventTime+":00+02:00");
			}
			else {
				start.setDate(eventDate);
				end.setDate(eventDate);
			}
			newEvent.setStart(start);
			newEvent.setEnd(end);
			newEvent.setSummary(eventName);
			String eventId = gcResource.addEvent(calendarId, newEvent);
			if(eventId!=null && !"".equals(eventId)) {
				req.setAttribute("proyectTitle", proyectTitle);
				req.getRequestDispatcher("/googleCalendarEventList").forward(req, res);
			}
			else {
				log.info("Event id is null probably token has expired, redirecting to OAuth servlet");
				req.getRequestDispatcher("/AuthController/GoogleCalendar").forward(req, res);
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
