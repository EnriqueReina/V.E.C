<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="messageList">
<!-- Borrar al añadir a proyecto final -->
	<c:if test="${chat==null}">
		<p class="h4 text-justify px-5 pt-5 t-5" style="color: rgba(255,255,255,0.4);">Ningún chat seleccionado. Para poder seleccionar un calendario, debes ir a donde tus proyectos de Googel Drive y pulsa en el icono: <i class="far fa-check-square ml-2 action plus-but"></i></p>
	</c:if>
<!-- Hasta aquí llega el borrado -->
	<c:if test="${chat!=null}">
		<div class="messages">
			<c:if test="${chat.items.isEmpty()}">
				<div class="text-white emptyChat">No hay mensajes</div>
			</c:if>
			<c:if test="${!chat.items.isEmpty()}">
				<div class="overflowChat">
					<c:forEach items="${chat.items}" var="reply">
						<table class="chat">
							<tr class="profileRow"><td class="profileImgTd"><img class="profileImg" src="${reply.author.picture.url}"></td><td class="profileNameTd">${reply.author.displayName}</td></tr>
						</table>
						<table class="chat2">	
							<tr class="messageRow"><td class="messageTd">${reply.content}</td></tr>
						</table>
					</c:forEach>
				</div>
			</c:if>
		</div>
		<div class="sendMessage bg-white w-100 h-100">
			<div action="/googleDriveChatNewMessage" method="Get">
				<input id="driveChatProjectTitle" type="hidden" name="proyectTitle" value="${proyectTitle}">
				<input id="driveChatChatId" type="hidden" name="chatId" value="${chatId}">
				<input id="driveChatFileId" type="hidden" name="fileId" value="${fileId}">
				<input id="driveChatInputMessage" class="inputMessage" type="text" name="chatMessage" placeholder="Escribe aquí tu mensaje">
				<button id="sendMessageDriveChat" class="sendButton"></button> 
			</div>	
		</div>
		
		<script>
			$("#sendMessageDriveChat").click(function(){
				
				var pT = $("#driveChatProjectTitle").val();
				var cId = $("#driveChatChatId").val();
				var fId = $("#driveChatFileId").val();
				var cNM = $("#driveChatInputMessage").val();
				cNM = encodeURIComponent(cNM);
				
				$("#reload_DriveChat").load("/googleDriveChatNewMessage?proyectTitle="+ pT +"&chatId="+ cId +"&fileId="+ fId +"&chatMessage="+cNM);	
			});
			
		</script>
	</c:if>
</div>

