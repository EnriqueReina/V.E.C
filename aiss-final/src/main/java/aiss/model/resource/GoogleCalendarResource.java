package aiss.model.resource;

import java.util.logging.Logger;

import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import aiss.model.google.calendar.Calendar;
import aiss.model.google.calendar.CalendarAcl;
import aiss.model.google.calendar.CalendarAclRule;
import aiss.model.google.calendar.CalendarList;
import aiss.model.google.calendar.EventList;
import aiss.model.google.calendar.EventPost;

public class GoogleCalendarResource {
	private static final Logger log = Logger.getLogger(GoogleCalendarResource.class.getName());
	
	private final String access_token;
	private final String uri = "https://www.googleapis.com/calendar/v3";
	
	public GoogleCalendarResource(String access_token) {
		this.access_token = access_token;
	}
	
	//CALENDARS
	
	public CalendarList getCalendarList() {
		String calendarList = uri + "/users/me/calendarList";
		ClientResource cr = null;
		CalendarList result = null;
		try {
            cr = new ClientResource(calendarList + "?access_token=" + access_token);
            result = cr.get(CalendarList.class);
        } 
		catch (ResourceException re) {
            log.warning("Error when retrieving all calendars: " + cr.getResponse().getStatus());
        }
        return result;
	}
	
	public String getProyectCalendar(String proyectTitle) {
		String calendarId = null;
		CalendarList list = getCalendarList();
		if(list!=null) {
			for(Calendar c : list.getItems()) {
				if(c.getSummary().equals(proyectTitle)) {
					calendarId = c.getId();
				}
			}
		}
		else {
			calendarId = "tokenExpired";
		}
		return calendarId;
	}
	
	public String addCalendar(Calendar calendar) {
		ClientResource cr = null;
		String newId = null;
		try {
			cr = new ClientResource(uri + "/calendars?access_token=" + access_token);
			Calendar newCalendar = cr.post(calendar,Calendar.class);
			newId = newCalendar.getId();
		}
		catch (ResourceException re) {
			log.warning("Error when inserting the calendar: " + cr.getResponse().getStatus());
		}
		return newId;
	}
	
	public CalendarAcl getCalendarAcl(String calendarId) {
		ClientResource cr = null;
		CalendarAcl result = null;
		try {
            cr = new ClientResource(uri + "/calendars/" + calendarId + "/acl?access_token=" + access_token);
            result = cr.get(CalendarAcl.class);
        } 
		catch (ResourceException re) {
            log.warning("Error when retrieving calendar acl: " + cr.getResponse().getStatus());
        }
        return result;
	}
	
	public String addRuleToCalendar(String calendarId, CalendarAclRule rule) {
		ClientResource cr = null;
		String newId = null;
		try {
			cr = new ClientResource(uri + "/calendars/" + calendarId + "/acl?access_token=" + access_token);
			CalendarAclRule newRule = cr.post(rule,CalendarAclRule.class);
			newId = newRule.getId();
		}
		catch (ResourceException re) {
			log.warning("Error when inserting the calendar acl rule: " + cr.getResponse().getStatus());
		}
		return newId;
	}
	
	public boolean deleteAclRule(String calendarId, String ruleId) {
        ClientResource cr = null;
        boolean result = true;
        try {
            cr = new ClientResource(uri + "/calendars/" + calendarId + "/acl/" + ruleId +"?access_token=" + access_token);
            cr.delete();
        } catch (ResourceException re) {
            log.warning("Error when deleting calendar acl rule: " + cr.getResponse().getStatus());
            result = false;
        }
        return result;
    }
	
	public boolean updateCalendar(String calendarId, Calendar calendar) {
        ClientResource cr = null;
        boolean result = true;
        try {
            cr = new ClientResource(uri + "/calendars/" + calendarId + "?access_token=" + access_token);
            cr.put(calendar);
        } catch (ResourceException re) {
            log.warning("Error when updating calendar: " + cr.getResponse().getStatus());
            result = false;
        }
        return result;
    }
	
	public boolean deleteCalendar(String calendarId) {
        ClientResource cr = null;
        boolean result = true;
        try {
            cr = new ClientResource(uri + "/calendars/" + calendarId + "?access_token=" + access_token);
            cr.delete();
        } catch (ResourceException re) {
            log.warning("Error when deleting calendar: " + cr.getResponse().getStatus());
            result = false;
        }
        return result;
    }
	
	//EVENTOS
	
	public EventList getEventList(String calendarId) {
		String calendarList = uri + "/calendars/" + calendarId + "/events?orderBy=startTime&singleEvents=true&timeZone=Europe%2FMadrid";
		ClientResource cr = null;
		EventList result = null;
		try {
            cr = new ClientResource(calendarList + "&access_token=" + access_token);
            result = cr.get(EventList.class);
        } 
		catch (ResourceException re) {
            log.warning("Error when retrieving all events: " + cr.getResponse().getStatus());
        }
		
		return result;
	}
	
	public String addEvent(String calendarId, EventPost event) {
		ClientResource cr = new ClientResource(uri + "/calendars/" + calendarId + "/events?access_token=" + access_token);
		ChallengeResponse chr = new ChallengeResponse(ChallengeScheme.HTTP_OAUTH_BEARER);
		
    	chr.setRawValue(access_token);
    	cr.setChallengeResponse(chr);
    	String newId = null;
		try {
			EventPost newEvent = cr.post(event,EventPost.class);
			newId = newEvent.getId();
		}
		catch (ResourceException re) {
			log.warning("Error when inserting the event: " + cr.getResponse().getStatus());
		}
		return newId;
	}
	
	public boolean updateEvent(String calendarId, String eventId, EventPost event) {
        ClientResource cr = null;
        boolean result = true;
        try {
            cr = new ClientResource(uri + "/calendars/" + calendarId + "/events/" + eventId + "?access_token=" + access_token);
            cr.put(event);
        } catch (ResourceException re) {
            log.warning("Error when updating event: " + cr.getResponse().getStatus());
            result = false;
        }
        return result;
    }

	public boolean deleteEvent(String calendarId, String eventId) {
        ClientResource cr = null;
        boolean result = true;
        try {
            cr = new ClientResource(uri + "/calendars/" + calendarId + "/events/" + eventId + "?access_token=" + access_token);
            cr.delete();
        } catch (ResourceException re) {
            log.warning("Error when deleting event: " + cr.getResponse().getStatus());
            result = false;
        }
        return result;
    }

}
