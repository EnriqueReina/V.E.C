package aiss.model.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.google.appengine.repackaged.org.joda.time.LocalDateTime;

import aiss.model.google.calendar.Calendar;
import aiss.model.google.calendar.EndPost;
import aiss.model.google.calendar.Event;
import aiss.model.google.calendar.EventList;
import aiss.model.google.calendar.EventPost;
import aiss.model.google.calendar.StartPost;
import aiss.model.resource.GoogleCalendarResource;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PruebasGoogleCalendar {

	private final String access_token = "ya29.GlsVB6OGiyQ32CrsEUaZD90Q-aUH-pzidkPSUxnmkQyLuTXu9LPQf1T06XIlc4S16O9l6CrHYy_SwGkBdIU4FZ-UfxihOUzQoEfbZQW8s0bk2xbCTujexHRQz9ui";
	GoogleCalendarResource gcResource = new GoogleCalendarResource(access_token);

	@Test
	// Crea calendario
	public void testA() {
		Calendar cal = new Calendar();
		cal.setSummary("TestDePrueba");

		String res = gcResource.addCalendar(cal);

		cal.setId(res);

		if (res.isEmpty()) {
			res = null;
		}
		if (res != null) {
			System.out.println("Google Calendar con nombre " + cal.getSummary() + " creado Correctamente");
		}

		assertNotNull("El calendario no puede ser null", res);
	}

	@Test
	// Crea Evento
	public void testB() {

		String calendarId = gcResource.getProyectCalendar("TestDePrueba");
		EventPost event = new EventPost();
		LocalDateTime startTime = LocalDateTime.now();
		LocalDateTime endTime = LocalDateTime.now().plusDays(1);

		StartPost start = new StartPost();
		EndPost end = new EndPost();

		start.setDateTime(startTime.toString() + "Z");
		end.setDateTime(endTime.toString() + "Z");
		event.setStart(start);
		event.setEnd(end);
		event.setSummary("Evento creado con las pruebas jUnit");

		String res = gcResource.addEvent(calendarId, event);
		event.setId(res);

		if (res.isEmpty()) {
			res = null;
		}
		if (res != null) {
			System.out.println("Evento con nombre" + event.getSummary()
					+ " creado correctamente en el calendario con id: " + calendarId);
		}

		assertNotNull("El evento no puede ser null", res);
	}

	@Test
	// Actualiza Evento
	public void testC() {
		String calendarId = gcResource.getProyectCalendar("TestDePrueba");
		EventList eventL = gcResource.getEventList(calendarId);
		Event eventoCreado = eventL.getItems().get(0);
		String eventId = eventoCreado.getId();

		EventPost newEvent = new EventPost();
		
		LocalDateTime startTime = LocalDateTime.now();
		LocalDateTime endTime = LocalDateTime.now().plusDays(1);

		StartPost start = new StartPost();
		EndPost end = new EndPost();
		
		start.setDateTime(startTime.toString() + "Z");
		end.setDateTime(endTime.toString() + "Z");
		
		newEvent.setSummary("Evento actualizado");
		newEvent.setStart(start);
		newEvent.setEnd(end);
		
		Boolean res = gcResource.updateEvent(calendarId, eventId, newEvent);
		
		if (!res) {
			res = null;
		}
		if (res) {
			System.out.println("Evento con nombre" + eventoCreado.getSummary()+ " actualizado correctamente en el calendario con id: " + calendarId);
			System.out.println("Nuevos datos: " + newEvent.getSummary() +", con incio: " + newEvent.getStart().getDateTime() + "; con fin: " + newEvent.getEnd().getDateTime());
		}

		assertNotNull("El evento no puede ser null", res);
	}


	@Test
	// Borra Evento
	public void testD() {

		String calendarId = gcResource.getProyectCalendar("TestDePrueba");
		EventList eventL = gcResource.getEventList(calendarId);
		Event eventoCreado = eventL.getItems().get(0);
		String eventId = eventoCreado.getId();

		Boolean res = gcResource.deleteEvent(calendarId, eventId);

		if (res) {
			System.out.println("Borrado correctamente el evento: " + eventoCreado.getSummary() + ", con id: " + eventId);
		}

		assertTrue("El evento no se borro correctamente", res);
	}

	@Test
	// Borra Calendario
	public void testE() {

		String calendarId = gcResource.getProyectCalendar("TestDePrueba");
		Boolean res = gcResource.deleteCalendar(calendarId);

		if (res) {
			System.out.println("Borrado correctamente el calendario con nombre: TestDePrueba, con id: " + calendarId);
		}

		assertTrue("El calendario no se borro correctamente", res);
	}

}
