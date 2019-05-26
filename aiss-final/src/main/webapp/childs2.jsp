<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="mx-auto p-3">
<h2 class="text-white">REPOSITORIOS</h2>
<table class="text-white">
<tr>
         <th class="h4">Name</th>
</tr>
        <c:forEach items="${requestScope.files.values}" var="files">
        
            <tr style="height: 5px;"></tr>
            <tr>
            	<td class="ml-3 pl-5">
            	<c:out value="${files.path}"/></td>
            	<c:choose>
	            	<c:when test="${files.type=='commit_directory'}">
	            	<td>
	            	<div>
		    					<span onclick="getDirectorioFolder(this)">
			    					<i class="far fa-folder-open"></i>
			    					<input id = "dirDirectoryRepo" type ="hidden" name="dir" value=<c:out value="${files.path}"/>>
			    					<input id = "commitIdDirectoryRepo" type ="hidden" name="commitId" value=<c:out value="${files.commit.hash}"/>>
			    					<input id = "reponameDirectoryRepo" type ="hidden" name="reponame" value=<c:out value="${requestScope.reponame}"/>>
			    				</span>
					</div>
					
					<script>
						function getDirectorioFolder(thisElement)
						{
							$("#Spinner").show();
							var dir = $(thisElement).find("#dirDirectoryRepo").val();
							var commitId = $(thisElement).find("#commitIdDirectoryRepo").val();
							var reponame = $(thisElement).find("#reponameDirectoryRepo").val();
						
							$("#reload").load("/DirectoryController?dir="+dir+"&commitId="+commitId+"&reponame="+reponame, function(){
								$("#Spinner").hide();
							});
						}
					</script>
					</td>
            		</c:when>
	            	<c:otherwise>
		            	<td class="pl-3">
		            		
		    					<span onclick="location.href='${files.links.self.href}'"><i class="far fa-eye"></i></span>
							
						</td>
	            	</c:otherwise>
            	</c:choose>
            </tr>
            <tr style="height: 5px;"></tr>
        </c:forEach>			
    </table>
    
    <div class="ml-5 mt-3">
	    <span class="text-white" onclick="vuelveAtras()">
	    	Vuelve a tus repositorios
	    </span>
    </div>
    
    <script>
    	function vuelveAtras()
    	{
    		$("#Spinner").show();
    		$("#reload").load("/repoLists",function(){
    			$("#Spinner").hide();
    		});
    	}
    </script>
 </div>	
            		
       
        	  
            	
            
		            
          
            			
            			
            		
          