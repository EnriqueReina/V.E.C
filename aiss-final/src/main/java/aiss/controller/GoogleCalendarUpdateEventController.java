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
public class GoogleCalendarUpdateEventController extends HttpServlet {
	
	private static final Logger log = Logger.getLogger(GoogleCalendarUpdateEventController.class.getName());
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String accessToken = (String) req.getSession().getAttribute("GoogleCalendar-token");
		if (accessToken != null && !"".equals(accessToken)) {
			String calendarId = req.getParameter("calendarId");
			String proyectTitle = req.getParameter("proyectTitle");
			String eventId = req.getParameter("eventId");
			String newEventName = req.getParameter("newEventName");
			
			GoogleCalendarResource gcResource = new GoogleCalendarResource(accessToken);
			String oldEventName = req.getParameter("oldEventName");
			String oldEventDate = req.getParameter("oldEventDate");
			String newEventDate = req.getParameter("newEventDate");
			String oldEventTime = req.getParameter("oldEventTime");
			String newEventTime = req.getParameter("newEventTime");
			
			log.severe(calendarId +", "+proyectTitle +", "+eventId +", "+newEventName +", "+oldEventTime +", "+oldEventName +", "+oldEventDate +", "+newEventDate +", "+oldEventTime +", "+newEventTime);
			
			EventPost updEvent = new EventPost();
			StartPost start = new StartPost();
			EndPost end = new EndPost();
			if(!oldEventName.equals(newEventTime)) {
				updEvent.setSummary(newEventName);
			}
			if(newEventTime!=null && !"".equals(newEventTime)) {
				if(newEventTime.length()==4) {
					newEventTime="0"+newEventTime;
				}
				if(oldEventTime.length()==4) {
					oldEventTime="0"+oldEventTime;
				}
				if(!oldEventDate.equals(newEventDate)) {
					if(!oldEventTime.equals(newEventTime)) {
						start.setDateTime(newEventDate+"T"+newEventTime+":00+02:00");
						end.setDateTime(newEventDate+"T"+newEventTime+":00+02:00");
					}
					else {
						start.setDateTime(newEventDate+"T"+oldEventTime+":00+02:00");
						end.setDateTime(newEventDate+"T"+oldEventTime+":00+02:00");
					}
				}
				else {
					if(!oldEventTime.equals(newEventTime)) {
						start.setDateTime(oldEventDate+"T"+newEventTime+":00+02:00");
						end.setDateTime(oldEventDate+"T"+newEventTime+":00+02:00");
					}
					else {
						start.setDateTime(oldEventDate+"T"+oldEventTime+":00+02:00");
						end.setDateTime(oldEventDate+"T"+oldEventTime+":00+02:00");
					}
				}
			}
			else {
				if(!oldEventDate.equals(newEventDate)) {
					start.setDate(newEventDate);
					end.setDate(newEventDate);
				}
				else {
					start.setDate(oldEventDate);
					end.setDate(oldEventDate);
				}
			}
			updEvent.setStart(start);
			updEvent.setEnd(end);
			Boolean success = gcResource.updateEvent(calendarId, eventId, updEvent);
			if(!success) {
				log.info("Update failed probably token has expired, redirecting to OAuth servlet");
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
