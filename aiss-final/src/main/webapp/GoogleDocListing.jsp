<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

	<div class="cont text-white" style="height:300px">
		<div class="row h-1 pb-0 ">	
			<div>
				<h2>
					El titulo del documento es: <c:out value="${doc.title}"/>
					<span class="mx-5" onclick="displayEditor()"><i class="fas fa-pen-square"></i></span>
				</h2>
				
			</div>
		</div>
		<div class="row h-8 overflow-y-s text-justify">
			<div class="w-100 h-100" id="DocCont">
				<c:forEach items="${doc.body.content}" var="c">
					<c:forEach items="${c.paragraph.elements}" var="e">
						<c:out value="${e.textRun.content}"/>
						
						<br>
					</c:forEach>
				</c:forEach>
			</div>
			
			<div class="w-100 h-5" id="DocCont2">
				<div class="mx-autow-8">
					<textarea id="driveFileContent" class="text-justify w-90-p h-6 mx-auto d-block">
						<c:forEach items="${doc.body.content}" var="c">
							<c:forEach items="${c.paragraph.elements}" var="e">
								<c:out value="${e.textRun.content}"/>
							</c:forEach>
						</c:forEach>
					</textarea>
				</div>
				
				<div class="mx-5 mt-3">
					<button class="button-style" style="background-color: #636e72; border-color: #2f3542;" id="${doc.documentId}" onclick="actualizarContenido(this)">Guardar</button>
					<button class="button-style" title="${doc.documentId}" onclick="cancelarEdicion(this)">Cancelar</button>
				</div>
			</div>	
					
		</div>
		
		<script>
			
			$(document).ready(function(){
				$("#DocCont2").hide();
				
			});
			
			function displayEditor()
			{
				$("#DocCont").hide();
				$("#DocCont2").show();
			
			}
			
			function actualizarContenido(thisElement)
			{
				var id = $(thisElement).prop('id');
				var content = $("#driveFileContent").val();
				
				content = encodeURIComponent(content);
		
				$("#reload_GoogleDocPrev").load("/GoogleDriveUpdateFileContent?id="+id+"&content="+content);
			}
			
			function cancelarEdicion(thisElement)
			{
				var id = $(thisElement).prop('title');
				$("#reload_GoogleDocPrev").load("/googleDocList?id="+id);
			}
		</script>
	</div>