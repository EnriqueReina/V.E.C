<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html> 
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Proyecto VEC</title>
    <link rel="stylesheet" type="text/css" href="css/universal-style.css">
    
	<body class="" style="background-color:#636e72;">
	
    <div class="mt-5">
    	<img class="d-block mx-auto" alt="" src="/includes/logo.png">
    </div>
    <div class="mx-auto text-white p-4 br-2 mt-3 " style="width:500px; background-color: rgba(255,255,255,0.2)">
    	<p class="text-justify">
    		Para usar nuestra aplicación requerimos del uso externo de otras aplicaciones. Para hacer uso de los servicios mínimos, debes registrarte en cada una de ellas (Cuenta de Google y cuenta de Bit-Bucket)para poder usar nuestros servicios.
    	</p>
    </div>
    <div class="w-5 my-5 mx-auto">
	    <button class="d-block mx-auto button-style" onclick="location.href = '/loginVEC'" style="width:100%;">
	    	LOG IN
	    </button>
	    <a class="text-white" href="https://aiss-vec.appspot.com/docs/index.html">Acceda a nuestra API REST</a> 
    </div>
  </body>
</html>