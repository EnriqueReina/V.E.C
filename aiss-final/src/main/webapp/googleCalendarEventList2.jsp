<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="eventList h-100">
	<!-- Borrar al añadir a proyecto final -->
	<c:if test="${events==null}">
		<div class="h-100 w-100" style = "background-color: rgba(255,255,255,0.2);">
			<p class="h4 text-center pt-5 t-5" style="color: rgba(255,255,255,0.4);">Ningún calendario seleccionado. Para poder seleccionar un calendario, debes ir a donde tus proyectos de Google Drive y pulsa en el icono: <i class="far fa-check-square ml-2 action plus-but"></i></p>
		</div>
	</c:if>
	<!-- Hasta aquí llega el borrado -->
	<c:if test="${events!=null}">
		<div class="cont">
		<div class="row p-0" style="background-color: #636e72; border-radius: 10px 10px 0px 0px">
			<h3 class="my-2 p-3 mx-auto text-white"><c:out value="${proyectTitle}"/></h3>
		</div>
		<div class="row p-0">
		<div class="events h-7" style="background-color:#4b4b4b">
			<c:if test="${events.items.isEmpty()}">
				<div class="emptyV">No hay eventos</div>
			</c:if>
			<c:if test="${!events.items.isEmpty()}">
				<div class="overflowV h-100">
					<c:forEach items="${events.items}" var="event">
						<div class="calendarEntry row h-2">
							
							<c:if test="${event.start.dateTime==null}">
								<div class="dateCont h-1 t-3 px-3  mx-2">
										<p class="text-bold">
											<c:out value="${event.start.date.getDayOfMonth()}"/>
											<c:out value="${event.start.date.getMonth()}"/>
											<c:out value="${event.start.date.getYear()}"/>
										</p>
										<div class="hour w-2">
											<p>--:--</p>
										</div>
								</div>
								
							</c:if>
							<c:if test="${event.start.dateTime!=null}">
													
								<div class="dateCont h-1 t-3 px-3  mx-2">
										<p class="text-bold">
											${event.start.dateTime.getDayOfMonth()}
											${event.start.dateTime.getMonth()}
											${event.start.dateTime.getYear()}
										</p>
										<div class="hour w-2">
											<p>
												<c:if test="${event.start.dateTime.getHour()<10}">0${event.start.dateTime.getHour()}:</c:if><c:if test="${event.start.dateTime.getHour()>9}">${event.start.dateTime.getHour()}:</c:if><c:if test="${event.start.dateTime.getMinute()<10}">0${event.start.dateTime.getMinute()}</c:if><c:if test="${event.start.dateTime.getMinute()>9}">${event.start.dateTime.getMinute()}</c:if>
											</p>
										</div>
								</div>
							</c:if>	
							<div class="event text-center t-4 w-6 h-1">
								<p>${event.summary}</p>
							</div>
							<div class="eventButtonCont pt-4 w-2">
							<div class="eventButton w-1">
								<div class="updateEvent w-100 h-100 my-0 br-0">
									<div class="updateEvent-content w-8">
										<div class="closeUpdateEvent">&times;</div>
										<div id="${event.id}">
											<input id="actualizarEventoCalendarId" type="hidden" name="calendarId" value="${calendarId}">
											<input id="actualizarEventoProyectTitle" type="hidden" name="proyectTitle" value="${proyectTitle}">
											<input id="actualizarEventoEventId" type="hidden" name="eventId" value="${event.id}">
											<c:if test="${event.start.dateTime==null}">
												<input id="actualizarEventoOldFecha" class="inputDate2" type="hidden" name="oldEventDate" value="${event.start.date.toString()}">
												<input id="actualizarEventoNewFecha" class="inputDate2 bg-white text-center mx-auto" type="date" name="newEventDate" required="required" value="${event.start.date.toString()}">
												<input id="actualizarEventoOldTime" class="inputTime2" type="hidden" name="oldEventTime" value="oldTimes">
												<input id="actualizarEventoNewTime" class="inputTime2 bg-white text-center mx-auto" type="time" name="newEventTime">
											</c:if>
											<c:if test="${event.start.dateTime!=null}">
												<input id="actualizarEventoOldFecha" class="inputDate2" type="hidden" name="oldEventDate" required="required" value="${event.start.dateTime.toLocalDate().toString()}">
												<input id="actualizarEventoNewFecha" class="inputDate2 bg-white text-center mx-auto" type="date" name="newEventDate" required="required" value="${event.start.dateTime.toLocalDate().toString()}">
												<input id="actualizarEventoOldTime" class="inputTime2" type="hidden" name="oldEventTime" value="${event.start.dateTime.toLocalTime().toString()}">
												<input id="actualizarEventoNewTime" class="inputTime2 bg-white text-center mx-auto" type="time" name="newEventTime" value="${event.start.dateTime.toLocalTime().toString()}">
											</c:if>
											<input id="actualizarEventoOldNameEvent" class="inputEvent2" type="hidden" name="oldEventName" required="required" pattern="[a-zA-Z0-9ñÑáéíóúüÁÉÍÓÚ\/-_çÇ&# ]+" value="${event.summary}">
											<input id="actualizarEventoNewNameEvent" class="inputEvent2 bg-white text-center mx-auto" id="inputEvent" type="text" name="newEventName" required="required" pattern="[a-zA-Z0-9ñÑáéíóúüÁÉÍÓÚ\/-_çÇ&# ]+" value="${event.summary}" ><br>
											<button class="buttonV" id="actualizarEventoGoogleCalendar" title="${event.id}" onclick="actualizarEventoGC(this)">Actualizar</button>
										</div>
								  	</div>
								  	
									<script>
								  		
										function actualizarEventoGC(thisElement)
										{					
											var id="#"+$(thisElement).prop('title');
											var parent = $(id);
											
											console.log(parent);
											
											var calId = $(parent).find("#actualizarEventoCalendarId").val();											
											var proTit = $(parent).find("#actualizarEventoProyectTitle").val();
											var eventId = $(parent).find("#actualizarEventoEventId").val();
											
											var oldFecha = $(parent).find("#actualizarEventoOldFecha").val();
											oldFecha = encodeURIComponent(oldFecha);
											var newFecha = $(parent).find("#actualizarEventoNewFecha").val();
											newFecha = encodeURIComponent(newFecha);
											var oldTime = $(parent).find("#actualizarEventoOldTime").val();
											oldTime = encodeURIComponent(oldTime);
											var newTime = $(parent).find("#actualizarEventoNewTime").val();
											newTime = encodeURIComponent(newTime);
											
											var oldName = $(parent).find("#actualizarEventoOldNameEvent").val();
											oldName = encodeURIComponent(oldName);
											var newName = $(parent).find("#actualizarEventoNewNameEvent").val();
											newName = encodeURIComponent(newName);
											
											//console.log("calendarId="+calId+"&proyectTitle="+proTit+"&eventId="+eventId+"&oldEventDate="+oldFecha+"&newEventDate="+newFecha+"&oldEventTime="+oldTime+"&newEventTime="+newTime+"&oldEventName="+oldName+"&newEventName="+newName);	
											
											$("#reload_Calendar").load("/googleCalendarUpdateEvent?calendarId="+calId+"&proyectTitle="+proTit+"&eventId="+eventId+"&oldEventDate="+oldFecha+"&newEventDate="+newFecha+"&oldEventTime="+oldTime+"&newEventTime="+newTime+"&oldEventName="+oldName+"&newEventName="+newName);	
										}					  	
								  	
								  	</script>
								  	
								</div>
								<button class="editEvent"></button>
							</div>
							<div class="eventButton">
								<div id="eventoBorrar${event.id}">
									<input id="delCalId" type="hidden" name="calendarId" value="${calendarId}">
									<input id="delProTit" type="hidden" name="proyectTitle" value="${proyectTitle}">
									<input id="delEventId" type="hidden" name="eventId" value="${event.id}">
									<button class="deleteEvent" title="${event.id}" onclick="deleteEventCalendar(this)"></button>
								</div>
							</div>
							</div>
							<script>
							
								function deleteEventCalendar(thisElement)
								{
									var eventId = $(thisElement).prop('title');
									var id = "#eventoBorrar"+eventId;
									var elBorrar = $(id);
	
									var delCalId = $(elBorrar).find("#delCalId").val();
									var delProTit = $(elBorrar).find("#delProTit").val();
									var delEventId = $(elBorrar).find("#delEventId").val();
								
									$("#reload_Calendar").load("/googleCalendarDeleteEvent?calendarId="+delCalId+"&proyectTitle="+delProTit+"&eventId="+delEventId);
								
								}
							</script>
						</div>
					</c:forEach>
				</div>
			</c:if>
			</div>
		</div>
		
		<div class="addEvent row m-0 p-0 w-100 h-1"  style="background-color: white; border-radius: 0px 0px 10px 10px">
			<div class="mx-auto py-1">
				<input id="calendarIdCalendarDrive" type="hidden" name="calendarId" value="${calendarId}">
				<input id="proyectTitleCalendarDrive" type="hidden" name="proyectTitle" value="${proyectTitle}">
				<input id="eventDateCalendarDrive" class="inputDate w-3 mx-1 bg-white" type="date" name="eventDate" required="required">
				<input id="eventTimeCalendarDrive" class="inputTime w-2 mx-1 bg-white" type="time" name="eventTime">
				<c:if test="${nameNull==null}">
					<input id="eventNameCalendarDrive" class="inputEvent w-3 mx-1 bg-white" type="text" name="eventName" required="required" pattern="[a-zA-Z0-9ñÑáéíóúüÁÉÍÓÚ\/-_çÇ&# ]+" placeholder="Título del evento" oninput="eventNameNull()">
				</c:if>
				<button class="addEventBut" id="googleCalendarNuevoEvento"></button>
			</div>
			
			<script>
				
				$("#googleCalendarNuevoEvento").click(function(){
					
					var calId = $("#calendarIdCalendarDrive").val();
					var ptCD = $("#proyectTitleCalendarDrive").val();
					var eventDateCD = $("#eventDateCalendarDrive").val();
					var eventTimeCD = $("#eventTimeCalendarDrive").val();
					var eventNameCD = $("#eventNameCalendarDrive").val();
					eventNameCD = encodeURIComponent(eventNameCD);
					
					$("#reload_Calendar").load("/googleCalendarNewEvent?calendarId="+calId+"&proyectTitle="+ptCD+"&eventDate="+eventDateCD+"&eventTime="+eventTimeCD+"&eventName="+eventNameCD);
				});
			
			</script>
		</div>
		</div>
		
	</c:if>
</div>
<script type="text/javascript">
	var modals = document.getElementsByClassName('updateEvent');
	var btns = document.getElementsByClassName("editEvent");
	var spans = document.getElementsByClassName("closeUpdateEvent");
	for(let i=0;i<btns.length;i++){
	    btns[i].onclick = function() {
	      	modals[i].style.display = "block";
	    }
	}
	for(let i=0;i<spans.length;i++){
		spans[i].onclick = function() {
	    	modals[i].style.display = "none";
	    }
	}
	function eventNameNull(){
		var inputEvent = document.getElementsByClassName('inputEvent')[0];
		var eventName = String(inputEvent.value).trim();
		var empty = "";
		if(eventName.localeCompare("")==0){
			var error = "Event must have a name";
		}
		else{
			var error = "";
		}
		inputEvent.setCustomValidity(error);
		return error;
	}
	function newEventNameNull(){
		var inputEvent = document.getElementById("inputEvent");
		var eventName = String(inputEvent.value).trim();
		var empty = "";
		if(eventName.localeCompare("")==0){
			var error = "Event must have a name";
		}
		else{
			var error = "";
		}
		inputEvent.setCustomValidity(error);
		return error;
	}
</script>
