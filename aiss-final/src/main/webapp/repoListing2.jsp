<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div  class="mx-auto p-3">
<h2 class="text-white">REPOSITORIOS <button class="float-r w-3 h5 button-style " id="botonCreaRepo" onclick="popUpModal(this)" style="background-color: #636e72; border-color: #2f3542; margin-top: -5px;"><i class="fas fa-folder-plus"></i></button></h2>

<table class="text-white ml-4 mt-3" id="repos">
     <c:forEach items="${requestScope.repos.values}" var="repos">
            <tr>
                <td class="text-bold" id="repoNameListing"><i class="fas fa-chevron-right"></i>  <c:out value="${repos.name}"/></td>
            	<td>
            		<div class="ml-5">
            			<input type="hidden" id="reponameBB" name="reponame" value=<c:out value="${repos.name}"/>>
            			<span onclick="contenidoRepoBB()"><i class="fab fa-bitbucket"></i></span>
            		</div>
            		
            		<script>
            			function contenidoRepoBB()
            			{
            				$("#Spinner").show();
	            			var reponame = $("#reponameBB").val();
	            			$("#reload").load("/FileListController?reponame="+reponame, function(){
	            				$("#Spinner").hide();
	            			});
            			}
            		</script>
            		
            	</td>
            	<td>
            		<div class="ml-3">
            			<span id="${repos.name}" onclick="deleteRepo(this)"><i class="far fa-trash-alt"></i></span>
            		</div>
            		
            		<script>
            			function deleteRepo(thisElement)
            			{
            				$("#Spinner").show();
            				var reponameDel = $(thisElement).prop('id');
            				
            				$("#reload").load("/RepoDeleteController?reponame="+reponameDel, function(){
            					$("#Spinner").hide();
            				});
            			}
            		</script>
            	</td>
            	
    		</tr>
    		<tr class="spacer" style="height: 10px"></tr>
        </c:forEach>			
    </table>
    	           
        <div class="contBB" id="contenedorModalRepo">
        	<div class="contElementBB bg-dark">
	        	<div class="cont-title text-center p-2">
	        		<p class="h4 text-white">Crea tu repo</p>
	        	</div>
	        	
	        	<div class="cont-body ">
					<div method="GET" action="/NewRepoController">
						<input class="d-block mx-auto mb-2 text-center" id="reponameCreateRepo" type="text" name="reponame" placeholder="Nombre del repo">
						<br>
						<span class="d-block mx-auto button-style w-2 text-center pt-2" onclick="crearRepo()" style="height: 30px; background-color: #636e72; border-color: #2f3542;">Crear</span>
						<br>
						<span class="d-block mx-auto button-style w-2 text-center pt-2" id="cerrarPopUpCreaRepo" style="height: 30px;" onclick="cerrarPopUp()">Close</span>
					</div>  
					
					<script>
						function crearRepo()
						{
							$("#Spinner").show();
							var nuevoRepoName = $("#reponameCreateRepo").val();
							
							$("#reload").load("/NewRepoController?reponame="+nuevoRepoName,function(){
								$("#Spinner").hide();
							});
						}
					</script>    	
	        	</div>
        	</div>
        </div>

       <script>
       
       		function popUpModal(Button)
       		{
       			var modal = document.getElementById("contenedorModalRepo");
       			
       			modal.style.display = "block";
       			
       		}
       	
       		function cerrarPopUp()
       		{
				var modal = document.getElementById("contenedorModalRepo");
       			
       			modal.style.display = "none";	
       		}
       		function popUpModal2(Button)
       		{
       			var modal = document.getElementById("contenedorModalUpload");
       			var reponame = document.getElementById("repoNameListing").innerHTML;
       			var myInput = document.getElementById("miInputRepoName");
       			myInput.value = reponame;
       			
       			console.log(reponame);
       			
       			modal.style.display = "block";
       			
       		}
       	
       		function cerrarPopUp2()
       		{
				var modal = document.getElementById("contenedorModalUpload");
       			
       			modal.style.display = "none";	
       		}
       </script>
       
       
</div> 

  