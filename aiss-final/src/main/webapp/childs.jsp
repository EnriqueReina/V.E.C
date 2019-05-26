<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ul>
	<c:forEach items="${requestScope.files.items}" var="f2">
		
		<c:forEach items="${f2.parents}" var="p">
		
				<c:if test="${param.file == p.id }">			
					
						<c:choose>
							<c:when test="${f2.mimeType == 'application/vnd.google-apps.folder' }">
								<figure id="carpeta">									
									<figcaption>								
										
										  <button class="butt2 mt-2">
	                                    
	                                    	<i class="fas fa-angle-right mr-2"></i>
	                                    	
	                                    	<span class="hover_link" id="${f2.id}" onclick="myFunc(this.id)"><c:out value="${f2.title}"/></span>
	                                    	
											<c:if test="${!f2.title.contains('CONFIG')}">
	                                    	
		                                    	<!-- Editar -->
		                                    	<span title="${f2.title}" id="${f2.id}" onclick="myFunc4(this)">
		                                    		<i class="fas fa-pencil-alt ml-2 action edit-but"></i>
		                                    	</span>	                                    	
	                                    	
		                                    	<!-- Borrar -->
		                                    	<span id="${f2.title}" title="${f2.id}" onclick="borrarProyectoDrive(this)"><i class="fas fa-eraser ml-2 action erase-but"></i></span>
		                                    	
		                                    	<!-- Añadir -->
		                                    	<span title="${f2.id}" id="myBtn" onclick="func3(this)"><i class="fas fa-plus ml-2 action plus-but"></i></span>
	                                    	</c:if>
	                                    	
	                                    </button>
										
									</figcaption>								
									
								<jsp:include page="childs.jsp">
									<jsp:param name="file" value="${f2.id}" />
								</jsp:include>
								
								</figure>	
							</c:when>
							<c:otherwise>
								<li class="my-2" id="archivo">
		
									
									<span class="linkStyle" id="${f2.id}" onclick="previewText(this)">
										<c:out value="${f2.title}"/>
									</span>
							
									
									<script>
										function previewText(thisElement)
										{
											var fileId = $(thisElement).prop('id');
											
											$("#reload_GoogleDocPrev").load("/googleDocList?id="+fileId);
										}
									</script>
									
									<c:if test="${!f2.title.contains('VECchat')}">
									
									<!-- Editar -->
	                                <span title="${f2.title}" id="${f2.id}" onclick="myFunc4(this)">
	                                	<i class="fas fa-pencil-alt ml-2 action edit-but"></i>
	                                </span>

	                                <span id="${f2.title}" title="${f2.id}" onclick="borrarProyectoDrive(this)">
	                               		<i class="fas fa-eraser ml-2 action erase-but"></i>
	                                </span>
	                                
	                                </c:if>
	                             
								</li>
								
							</c:otherwise>
						</c:choose>			
						
				</c:if>			
								
		</c:forEach>
	
	</c:forEach>
</ul>