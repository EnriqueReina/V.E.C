package aiss.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.google.calendar.Calendar;
import aiss.model.google.calendar.CalendarAclRule;
import aiss.model.google.calendar.Scope;
import aiss.model.resource.GoogleCalendarResource;

@SuppressWarnings("serial")
public class GoogleCalendarNewCalendarController extends HttpServlet {
	
	private static final Logger log = Logger.getLogger(GoogleCalendarNewCalendarController.class.getName());

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String accessToken = (String) req.getSession().getAttribute("GoogleCalendar-token");
		if (accessToken != null && !"".equals(accessToken)) {
			String proyectTitle = req.getParameter("proyectTitle");
			if(proyectTitle!=null && !"".equals(proyectTitle)) {
				log.warning("Nuevo calendario con titulo: " + proyectTitle);
				GoogleCalendarResource gcResource = new GoogleCalendarResource(accessToken);
				Calendar newCalendar = new Calendar();
				newCalendar.setSummary(proyectTitle);
				String calendarId = gcResource.addCalendar(newCalendar);
				if(calendarId!=null && !"".equals(calendarId)) {
					String[] collaborators = req.getParameterValues("collab");
					if(collaborators!=null && !(collaborators.length==0)) {
						for(String email : collaborators) {
							CalendarAclRule rule = new CalendarAclRule();
							rule.setRole("writer");
							Scope scope = new Scope();
							scope.setType("user");
							scope.setValue(email);
							rule.setScope(scope);
							gcResource.addRuleToCalendar(calendarId, rule);
						}
					}
										
					res.sendRedirect(req.getContextPath()+"/googleCalendarEventList?proyectTitle="+proyectTitle+"&calendarId="+calendarId);;
				}
				else {
					log.info("Calendar id is null probably token has expired, redirecting to OAuth servlet");
					req.getRequestDispatcher("/AuthController/GoogleCalendar").forward(req, res);
				}
			}
			else {
				log.info("Proyect title is null, redirecting to select page");
				req.getRequestDispatcher("/googleDriveFileList2").forward(req, res);
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
