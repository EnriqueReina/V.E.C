package aiss.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
public class GoogleCalendarUpdateCalendarController extends HttpServlet {

	private static final Logger log = Logger.getLogger(GoogleCalendarUpdateCalendarController.class.getName());
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String accessToken = (String) req.getSession().getAttribute("GoogleCalendar-token");
		if (accessToken != null && !"".equals(accessToken)) {
			String proyectTitle = req.getParameter("proyectTitle").trim();
			if(proyectTitle!=null && !"".equals(proyectTitle)) {
				GoogleCalendarResource gcResource = new GoogleCalendarResource(accessToken);
				String calendarId = gcResource.getProyectCalendar(proyectTitle);
				log.warning("Id calendar: " + calendarId);
				if(calendarId!=null && !"".equals(calendarId)) {
					Calendar updCalendar = new Calendar();
					String newProyectTitle = proyectTitle;
					updCalendar.setSummary(newProyectTitle);
					gcResource.updateCalendar(calendarId, updCalendar);
					
					List<CalendarAclRule> aclList = gcResource.getCalendarAcl(calendarId).getItems();
					List<String> oldColList = new ArrayList<>();
					for(CalendarAclRule col : aclList) {
						oldColList.add(col.getScope().getValue());
					}
					List<String> newColList = new ArrayList<>();
					String[] newColArray = req.getParameterValues("collaborator");
					for(String col : newColArray) {
						newColList.add(col);
					}
					List<String> retain = new ArrayList<>(newColList);
					retain.retainAll(oldColList);
					newColList.removeAll(retain);
					oldColList.removeAll(retain);
					for(String email : newColList) {
						CalendarAclRule rule = new CalendarAclRule();
						rule.setRole("writer");
						Scope scope = new Scope();
						scope.setType("user");
						scope.setValue(email);
						rule.setScope(scope);
						gcResource.addRuleToCalendar(calendarId, rule);
					}
					for(CalendarAclRule r : aclList) {
						if(oldColList.contains(r.getScope().getValue())&&!r.getRole().equals("owner")) {
							gcResource.deleteAclRule(calendarId, r.getId());
						}
					}
					req.getRequestDispatcher("/googleCalendarEventList").forward(req, res);
				}
				else {
					log.info("Calendar id is null probably token has expired, redirecting to OAuth servlet");
					req.getRequestDispatcher("/AuthController/GoogleCalendar").forward(req, res);
				}
			}
			else {
				log.info("Proyect Title is null. Redirecting to select page.");
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
