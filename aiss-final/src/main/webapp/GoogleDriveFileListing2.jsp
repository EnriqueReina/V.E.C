<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
   <script>

    function myFunc(clicked_id)
    {
    	 var child = document.getElementById(clicked_id).parentElement.parentElement.parentElement.getElementsByTagName("ul")[0];
         var flecha = document.getElementById(clicked_id).parentElement.childNodes[1];
      
        if(child.style.display == "none")
        {
        	 flecha.className = "fas fa-angle-down mr-2";
             child.style.display = "block";
        }
        else
        {
        	 flecha.className = "fas fa-angle-right mr-2";
             child.style.display = "none";
        }
    }

    function myFunc2(clicked_id)
    {
        var child = document.getElementById(clicked_id).parentElement.parentElement.getElementsByTagName("ul")[0];
        var flecha = document.getElementById(clicked_id).parentElement.childNodes[1];
        
        if(child.style.display == "none")
        {
            flecha.className = "fas fa-angle-down mr-2";
            child.style.display = "block";
        }
        else
        {
        	flecha.className = "fas fa-angle-right mr-2";
            child.style.display = "none";
        }
    }
    
  
    </script>

   
            <div class="col GoogleDriveCont p-0" style="background-color: rgba(255,255,255,0.2)">

                <div class="col p-0 m-0" style="background-color: #636e72;">
                    <div class="row">
                        <div class="w-100">
							<input class="w-11 bg-white" id="titleRaw" type="text" name="nombre" placeholder="Escribe aqui el nombre del proyecto" required="required">
                            <span onclick="myFuncCollab(this)" class="w-4 font-1 float-r px-0 py-2 btn btn-primary bw-0 bg-danger text-center text-white">Nuevo Proyecto</span>
                        
                        	<!-- Modal 4 -->
							<div class="modal fade" id="myModal4">
								<div class="row modal-dialog" role="document">
									<div
										class=" col-l-4 col-m-8 bg-dark text-white mx-auto  modal-content">
										<div class="modal-header row">
											<div class="col">
												<h3 class="modal-title">
													Añadir colaboradores
													<span class="close float-r cross">
														<i class="fas fa-times"></i>
													</span>
												</h3>					
												
											</div>
										</div>
										<div class="modal-body">
											<div class="mx-auto" id="participants-cont">
												<p id="projectTitle"></p>
												<div class="my-2 text-center">
													<input class="collabos" type="email" name="collab" placeholder="email@gmail.com" style="width: 80%">
													<span class="mx-2" onclick="addCollab()">
														<i class="fas fa-plus pt-3"></i>
													</span>
												</div>
											</div>
											<div class="mx-auto text-center  mt-5">
												<!--<input class="btn btn-danger bg-success text-white" type="submit" value="Crear Proyecto"> -->
												<button class="button-style" style="background-color: #636e72; border-color: #2f3542;" id="creaProyectoDrive">Crea Proyecto</button>
											</div>
										</div>
									</div>
								</div>
							</div>
                        	
                        	<script>
                        		$("#creaProyectoDrive").click(function(){
                        			$("#Spinner").show();
                        			var titlE = $("#titleRaw").val();
                        			var stringCollab ="";
                        			
                        			$('.collabos').each(function() {
                        			    
                        				var email = encodeURIComponent($(this).val());
                        				stringCollab += "&collab=" + email;
                        				
                        			});
                        			                        			
                        			$("#reload_GoogleDriveFiles").load("/googleDriveProyectNew?nombre="+titlE+stringCollab, function(){
                        				$("#reload_Calendar").load("/googleCalendarEventList?proyectTitle="+titlE, function(){
                        					$("#reload_DriveChat").load("/googleDriveChatDisplay?proyectTitle="+titlE+"&picked=true", function(){
                        						$("#reload_GoogleDriveFiles").load("/googleDriveFileList2",function(){
	                        						$("#Spinner").hide();                        							
                        						});
                        					});
                        				});
                        			});                        		
                        			
                        		});
                        	</script>
                        
                        </div>
                    </div>
                </div>

                <ul>
                    <c:forEach items="${requestScope.files.items}" var="f">
                        <c:choose>
                            <c:when test="${f.mimeType == 'application/vnd.google-apps.folder' && f.title.contains('#AISSVEC')}">
                                <div class="projCont">
	                               	                                    
	                                     <button class="butt">
	                                    
	                                    	<i class="fas fa-angle-right mr-2"></i>
	                                    	
	                                    	<span class="hover_link" id="${f.id}" onclick="myFunc2(this.id)">
	                                    		<c:out value="${f.title}"/>
	                                    	</span>
	                                    		                                    	
	                                    	<!-- Editar 
	                                    	<span title="${f.title}" id="${f.id}" onclick="myFunc4(this)">
	                                    		<i class="fas fa-pencil-alt ml-2 action edit-but"></i>
	                                    	</span>-->
	                                    	
	                                    	<!-- Borrar -->
	                                    	<span id="${f.title}" title="${f.id}" onclick="borrarProyectoDrive(this)">
	                                    		<i class="fas fa-eraser ml-2 action erase-but"></i>
	                                    	</span>
	                                    	
	                                    	<script>
	                                    	function borrarProyectoDrive(thisElement)
	                                    	{
	                                    		$("#Spinner").show();
		                                    	var fileId = $(thisElement).prop('title');
		                                		var title = $(thisElement).prop('id');
		                                	
		                                		var res = title.split("-");
			                                	title = res[1];
			                                		
			                                	console.log("Aski");
			                                		
			                                	$("#reload_GoogleDriveFiles").load("/googleDriveDelete?id="+fileId+"&proyectTitle="+title+"&esTitulo=si",function(){
				                                	$("#reload_Calendar").load("/googleCalendarDeleteCalendar?proyectTitle="+title, function(){
				                                		$("#reload_GoogleDocPrev").load("/googleDocList_empty.jsp", function(){
					                                		$("#reload_DriveChat").load("/googleDriveChat2.jsp",function(){
					                                			$("#Spinner").hide();
					                                		});
				                                		});
				                                	});
			                                	});		                                		
	                                    	}
	                                    	</script>
	                                    	
	                                    	<!-- Añadir colab -->	                                    	
	                                    	<span id="${f.id}" onclick="myFunc5(this)">
	                                    		<i class="fas fa-user-friends ml-2 action addUser-but"></i>
	                                    	</span>
	                                    	
	                                    	<!-- Añadir -->
	                                    	<span title="${f.id}" id="myBtn" onclick="func3(this)">
	                                    		<i class="fas fa-plus ml-2 action plus-but"></i>
	                                    	</span>	
	                                    		                                    	
	                                    	<!-- Seleccionar -->
	                                    	<span id="seleccionarProyectoGoogleDrive" title="${f.title}" onclick="pickProject(this)" id="myBtn">
	                                    		<i class="far fa-check-square ml-2 action plus-but"></i>
	                                    	</span> 
	                                    	
	                                    	<script>
	                                        
	                                    	function pickProject(thisElement)
	                                    	{
	                                    		$("#Spinner").show();
		                                			
		                                    	var projectTitle = $(thisElement).prop('title');
		                                		var res = projectTitle.split("-");
		                                		projectTitle = res[1];
		                                		$("#reload_Calendar").load("/googleCalendarEventList?proyectTitle="+projectTitle, function(){
		                                			$("#reload_DriveChat").load("/googleDriveChatDisplay?proyectTitle="+projectTitle+"&picked=true",function(){
		                                				$("#Spinner").hide();
		                                			});
		                                		});
	                                    	}
	                                    	</script>
	                                    </button>
	                                   
	                                    <!-- Modal 3 -->
										<div class="modal fade ${f.id}" id="myModal3">
											<div class="row modal-dialog" role="document">
												<div class=" col-l-4 col-m-8 bg-dark text-white mx-auto  modal-content">
													<div class="modal-header row py-0">
														<div class="col">
															<h3 class="modal-title">
																Colaboradores
																<span class="close float-r cross">
																	<i class="fas fa-times"></i>
																</span>
															</h3>	
																<c:forEach items="${f.owners}" var="owner">
																	<p class="text-center mt-2"><b>Owner: </b><c:out value="${owner.emailAddress}"></c:out></p>
																</c:forEach>
														</div>
													</div>
													<div class="modal-body">									
														<div class="d-grid">
															<div class="mx-auto">
																<input type="hidden" name="fileId" value="${f.id}">
																
																<div class="overflow-y-s max-h-4">
																	<table class="mx-auto my-2">
																	<c:forEach items="${requestScope.perm}" var="collab">								
																																																				
																		<c:if test="${collab.key == f.id}">																		
																			<c:forEach items="${collab.value}" var="per">
																				
																					<c:choose>
																						<c:when test="${per.role =='owner'}">
																							
																						</c:when>
																						<c:otherwise>
																							
																							<tr>
																								<td><c:out value="${per.emailAddress}"/></td>
																								<td><input class="collaboratorDeleteGoogleDrive w-1"type="checkbox" name="checkEmail" value="${per.id}" class="w-2"></td>
																							</tr>
																			
																						</c:otherwise>
																					</c:choose>
																			</c:forEach>
																		</c:if>
																		
																	</c:forEach>
																	</table>
																</div>
																<p>Añadir colaborador:</p>
																
																<div class="col" id="anyadeNuevosCollabsActualizar">
																	<div class="my-2 text-center">
																		<input class="nuevoCollab" type="email" name="collab" placeholder="email@gmail.com">
																		<span class="mx-2" onclick="addCollab2()">
																			<i class="fas fa-plus"></i>
																		</span>
																	</div>
																</div>																														
															</div>
															<div class="mx-auto mt-5">
																<button title="${f.id}" id="${f.title}" class="btn btn-danger bg-danger w-4 text-white" onclick="anyadirCollabGDF(this)">Actualizar</button>
																<button title="${f.id}" class="btn btn-success bg-success w-4 text-white" onclick="borrarCollab(this)">Borrar</button>
															</div>
														</div>
													</div>
													
													<script>
														
													function borrarCollab(thisElement)
													{
														$("#Spinner").show();
														var allUsers = $(".collaboratorDeleteGoogleDrive");
														var fileId = $(thisElement).prop('title');
														var uri = "";
														$.each(allUsers, function(){
															
															if($(this).prop('checked'))
																uri += "&checkEmail="+ $(this).val();
														});
														
														if(uri == "")
														{
															$("#Spinner").hide();
															alert("Debes seleccionar al menos a un usuario para borrar");
														}
														else
														{
															$("#reload_GoogleDriveFiles").load("/googleDriveUpdateUser?fileId="+fileId+uri, function(){
																$("#Spinner").hide();
															});
														}
													}
													
													function anyadirCollabGDF(thisElement)
													{
														//name="actualizar" type="submit" value="Actualizar">
														$("#Spinner").show();
														
														var nuevosCollabs = $(".nuevoCollab");
														var fileId = $(thisElement).prop('title');
														var uri = "";
														var title= $(thisElement).prop('id');
														var res = title.split("-");
														title = res[1].trim();
														
														$.each(nuevosCollabs, function(){
															uri += "&collab="+ $(this).val();
														});
														
														uri = uri.trim();											
														
														if(uri == "&collab=")
														{
															$("#Spinner").hide();
															alert("Debes seleccionar al menos a un usuario para borrar");
														}
														else
														{
															$("#reload_GoogleDriveFiles").load("/googleDriveUpdateUser?proyectTitle="+ title +"&actualizar=Actualizar&fileId="+fileId+uri,function(){
																$("#reload_GoogleDriveFiles").load("/googleDriveFileList2", function(){
																	$("#Spinner").hide();
																});
															});
														}
													}
													
													</script>
													
												</div>
											</div>
										</div>
	                                    
                                    <jsp:include page="childs.jsp">
                                        <jsp:param name="file" value="${f.id}" />
                                    </jsp:include>
                                    
                                </div>
                            </c:when>
                        </c:choose>
                    </c:forEach>
				</ul>
            </div>
    

	
<!-- Modal 1 -->
	<div class="modal fade" id="myModal">
		<div class="row modal-dialog" role="document">
			<div
				class=" col-l-4 col-m-8 bg-dark text-white mx-auto  modal-content">
				<div class="modal-header row">
					<div class="col">
						<h3 class="modal-title">
							Añadir Nuevo Archivo
							<span class="close float-r cross" id="cerrrar">
								<i class="fas fa-times"></i>
							</span>
						</h3>					
						
					</div>
				</div>
				<div class="modal-body">

					<div class="d-grid" method="GET" action="/googleDriveInsertFile">
						<div class="mx-auto">
							<input type="hidden" id="parentId" name="parentId">
							
							<input id="inputFileNameDrive" class="myInp text-center" type="text" name="folderName" placeholder="Nombre del archivo" required="required">
							<select id="selectAnyadirNuevoArchivoDrive" name="folderType" class="mySelect">
								<option value="application/vnd.google-apps.folder">Carpeta</option>
								<option value="application/vnd.google-apps.document">Documento</option>
								<option value="text/plain">Plain Text</option>
							</select>
						</div>
						<div class="mx-auto mt-5 mb-2">
							<span class="btn btn-danger bg-danger w-3 text-white" id="anyadirProyectoDrive" onclick="nombreCrearProyecto()" >Crear</span>
						</div>
					</div>
					
					<script>
						function nombreCrearProyecto(){
							$("#Spinner").show();
							
							var parentId = $("#anyadirProyectoDrive").prop('title');
							
							var fileName = $("#inputFileNameDrive").val();
							var trozos = fileName.split(" ");
							var tam = trozos.length;
							
							var type = $("#selectAnyadirNuevoArchivoDrive").children("option:selected").val();
							
							if(tam == 1 && fileName.trim() != "")
							{								
								$("#reload_GoogleDriveFiles").load("/googleDriveInsertFile?parentId="+parentId+"&folderName="+fileName+"&folderType="+type, function(){
									$("#Spinner").hide();
								});
							}
							else
							{
								alert("El nombre no debe contener espacios o no puede estar vacio");	
								$("#Spinner").hide();
							}
							
							
						}
					</script>
				</div>
			</div>
		</div>
	</div>
	
	<!-- Modal 2 -->
	<div class="modal fade" id="myModal2">
		<div class="row modal-dialog" role="document">
			<div
				class=" col-l-4 col-m-8 bg-dark text-white mx-auto  modal-content">
				<div class="modal-header row">
					<div class="col">
						<h3 class="modal-title">
							Actualizar
							<span class="close float-r cross">
								<i class="fas fa-times"></i>
							</span>
						</h3>					
						
					</div>
				</div>
				<div class="modal-body">

					<div class="d-grid">
						<div class="mx-auto">
							<input id="folderIdAct" class="myInp2" type="hidden" name="folderId">
							<input id="esProyectoAct" class="esProyecto" type="hidden" name="esProyecto">
							<input id="folderNameAct" class="myInp text-center" type="text" name="folderName" placeholder="Nombre del archivo" required="required">
							
						</div>
						<div class="mx-auto mt-5">
							<button class="btn btn-danger bg-danger w-3 text-white" id="actualizarButton">Actualizar</button>
						</div>
					</div>
					
					<script>
						
						
						$("#actualizarButton").click(function(){
							$("#Spinner").show();
							
	                    	var folderId = $("#folderIdAct").val();
	                    	var esProyecto = $("#esProyectoAct").val();
	                    	var folderName = $("#folderNameAct").val();
	                    	
	                    	
	                        $("#reload_GoogleDriveFiles").load("/googleDriveUpdateFile?folderId="+folderId+"&esProyecto="+esProyecto+"&folderName="+folderName, function(){
	                        	$("#Spinner").hide();
	                        });
						});
						
					</script>
				</div>
			</div>
		</div>
	</div>
	
		<script>
			
			function func3(thisElement)
			{
				var modal = document.getElementById('myModal');
				var btn = thisElement.id;
				var btn2 = document.getElementById("closeBtn");
				var span = document.getElementById("cerrrar");
				console.log(span);
				var parentId = thisElement.title;
				var inp = document.getElementById("parentId");
				var btn3 = document.getElementById("anyadirProyectoDrive");
				
				
				thisElement.onclick = function() {
					modal.style.display = "block";
					inp.value = parentId;
					btn3.title = parentId;
				}

				// When the user clicks on <span> (x), close the modal
				span.onclick = function() {
					modal.style.display = "none";
				}

				// When the user clicks anywhere outside of the modal, close it
				window.onclick = function(event) {
					if (event.target == modal) {
						modal.style.display = "none";
					}
				}
			}	
			
			
			function myFunc4(thisElement)
			{
						
				var modal = document.getElementById('myModal2');
				var span = modal.getElementsByClassName("close")[0];
				var inp = modal.getElementsByClassName("myInp")[0];
				var inp2 = modal.getElementsByClassName("myInp2")[0]
				var inp3 = modal.getElementsByClassName("esProyecto")[0]
				
				thisElement.onclick = function() {
				modal.style.display = "block";
				var title;
					
					if(thisElement.title.includes("#AISSVEC"))
					{
						title = thisElement.title.split("-")[1];
						inp3.value = "yes";
					}
					else
					{
						title = thisElement.title;
						inp3.value = "no";
					}
					
					inp.value = title;
					inp2.value = thisElement.id;
				}
				
				span.onclick = function() {
					modal.style.display = "none";
				}

				// When the user clicks anywhere outside of the modal, close it
				window.onclick = function(event) {
					if (event.target == modal) {
						modal.style.display = "none";
					}
				}
			}
				
			
			function myFunc5(thisElement)
			{
				console.log(thisElement);
				
				var thisElementId = thisElement.id;
				
				console.log(thisElementId);
				
				var modal = document.getElementsByClassName(thisElementId)[0];
		
				console.log(modal);
				
				var span = modal.getElementsByClassName("close")[0];
								
				thisElement.onclick = function() {
					
					console.log("aki");
					modal.style.display = "block";
	
				}
				
				span.onclick = function() {
					modal.style.display = "none";
				}

				// When the user clicks anywhere outside of the modal, close it
				window.onclick = function(event) {
					if (event.target == modal) {
						modal.style.display = "none";
					}
				}
			}
			
				
			function myFuncCollab(thisElement)
			{
				var modal = document.getElementById('myModal4');
				var titleInput = document.getElementById("titleRaw").value +'';
				
				var trozos = titleInput.split(" ");
				console.log(trozos);
				var tam = trozos.length;
				console.log(tam);
				
				var span = document.getElementsByClassName("close")[0];
				
				console.log(modal);
				console.log(span);
				
				if(titleInput.value !== "" && tam == 1)
				{
					modal.style.display = "block";
				}else
				{
					alert("El titulo no debe estar vacio o no puede tener espacios");	
				}
	
				
				
				span.onclick = function() {
					modal.style.display = "none";
				}

				// When the user clicks anywhere outside of the modal, close it
				window.onclick = function(event) {
					if (event.target == modal) {
						modal.style.display = "none";
					}
				}
			}
			
			function addCollab()
			{
				var collabCont = document.getElementById("participants-cont");
				var htmlText = document.createElement('div');
				htmlText.classList.add('my-2');
				htmlText.classList.add('text-center');
				var textNode = "";
				textNode += '<input class="collabos" type="email" name="collab" placeholder="email@gmail.com" style="width: 80%"><span class="mx-2" onclick="addCollab()"><i class="fas fa-plus pt-3"></i>';
				
				htmlText.innerHTML = textNode;
				collabCont.appendChild(htmlText);
				
			}
			
			function addCollab2()
			{
				var collabCont = document.getElementById("anyadeNuevosCollabsActualizar");
				var htmlText = document.createElement('div');
				htmlText.classList.add('my-2');
				htmlText.classList.add('text-center');
				var textNode = "";
				textNode += '<input class="nuevoCollab" type="email" name="collab" placeholder="email@gmail.com"><span class="mx-2" onclick="addCollab2()"><i class="fas fa-plus"></i><span>';
				
				htmlText.innerHTML = textNode;
				collabCont.appendChild(htmlText);
				
			}
			
			
			</script>
