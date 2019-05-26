<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<title>Main</title>
	<link rel="stylesheet" type="text/css" href="css/universal-style.css">
	<link rel="stylesheet" type="text/css" href="css/main-style-2.css">
	<link rel="stylesheet" type="text/css" href="css/style-2.css">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
</head>
<body class="bg-dark">
	
	<div class="SpinnerCont bg-dark" id="Spinner">
	</div>
	<section class="sec-menu" id="GoogleChatSection">
		<div id="reload_DriveChat">
			<%@include file="googleDriveChat2.jsp"%>
		</div>
	</section>
	
	<div class="cont ml-5">
		<div class="row p-0 bg-dark" style="border-radius: 20px 20px 0px 0px;">
		
			<section class="sec" id="GoogleDriveSection">
				<div class="h-100" id="reload_GoogleDriveFiles">		
					<div class="w-100 h-100" style="background-color:rgba(255,255,255,0.2)">
						<div class="w-8 t-5 pt-5 mx-auto">
							<button class="mx-auto mt-4 button-style h5 text-uppercase " id="reloadGDF" style="display: block; background-color: #636e72; border-color: #2f3542; ">
								<i class="fab fa-google"></i>
							</button>
						</div>
					</div>		
				</div>
			</section>
			
			<section class="sec" id="GoogleCalendarSection">
				<div id="reload_Calendar" style="height: 100%;">
					<%@include file="googleCalendarEventList2.jsp"%>
				</div>
			</section>
		</div>
			
		<div class="row p-0 bg-dark" style="border-radius: 0px 0px 20px 20px;">
			<section class="sec" id="BitBucketSection">
				<div class="h-100" id="reload" style="height: 100%; background-color:rgba(255,255,255,0.2)">
					<div class="w-100 h-100">
						<div class="w-8 t-5 pt-5 mx-auto">
							<button class="mx-auto mt-4 button-style h5 text-uppercase " id="connectBit" style="display: block; background-color: #636e72; border-color: #2f3542; ">
								<i class="fab fa-bitbucket"></i>
							</button>
						</div>
					</div>							
				</div>
				
				
			</section>
			
			<section class="sec" id="GoogleTextPreviewSection">
				<div id="reload_GoogleDocPrev" style="height: 100%; background-color:rgba(255,255,255,0.2)">
					<p class="h4 text-justify px-5 pt-5 t-5" style="color: rgba(255,255,255,0.4);">
						Ningún documento seleccionado. Para poder visualizar el contenido de un documento, debes ir a donde tus proyectos de Google Drive y pulsar en un documento de texto <i class="fas fa-pen-square"></i>
					</p>
				</div>
			</section>
		</div>
	</div>
	
	<script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
	
	<script type="text/javascript">
		
		$(document).ready(function(){
			$("#Spinner").load("/includes/spinner.jsp");
			
		});
		
		$(window).on('load', function(){
			$("#Spinner").hide();
		})
	
		$("#reloadGDF").click(function()
		{
			$("#Spinner").show();
			$("#reload_GoogleDriveFiles").load("/googleDriveFileList2",function(){
				$("#Spinner").hide();
			});
		});
		
		$("#connectBit").click(function()
		{
			$("#Spinner").show();
			$("#reload").load("/repoLists",function(){
				$("#Spinner").hide();
			});
		});
		
		$("#reload_RepoList").click(function()
		{
			$("#reload").load("/repoLists");
		});
		
		$("#loadCalendar").click(function()
		{
			var projectTitle = $("#projectTitleInput").val();
			$("#reload_Calendar").load("/googleCalendarEventList?proyectTitle=" + projectTitle);
		});
	</script>
</body>
</html>