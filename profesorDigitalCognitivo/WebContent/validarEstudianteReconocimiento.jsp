<%
String codCurso = request.getParameter("codCurso").toString(); 
String nombreEvaluacion = request.getParameter("nombreEvaluacion").toString();
%>

<%@page import="org.omg.PortableInterceptor.SYSTEM_EXCEPTION"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
    <%@page import= "java.util.*" session="true" %>

<html>
<head> <meta charset="ISO-8859-1">
    <title>Validar Usuario</title>
    <link rel="stylesheet" type="text/css" media="screen" href="css/style.css">
      <h3 class="fuenteLetra3">Validar estudiante</h3>
</head>
</head> 
 <body>
<video id="video" width="427" height="320" autoplay></video>
<button id="snap">1- Tomar Foto</button>
<canvas id="canvas" width="427" height="320"></canvas>

<input type="hidden" name="jpegUrl" id="jpegUrl" value="">

<script type="text/javascript"> 
//Grab elements, create settings, etc.

var canvas = document.getElementById('canvas');
var context = canvas.getContext('2d');
var video = document.getElementById('video'); 
var imagen = document.getElementById("jpegUrl");

// Trigger photo take
document.getElementById("snap").addEventListener("click", function() {
	context.drawImage(video, 0, 0, 427, 320);
});

// Get access to the camera!
if(navigator.mediaDevices && navigator.mediaDevices.getUserMedia) {
    // Not adding `{ audio: true }` since we only want video now
    navigator.mediaDevices.getUserMedia({ video: true }).then(function(stream) {
        video.src = window.URL.createObjectURL(stream);
        video.play();
    });
}

else if(navigator.getUserMedia) { // Standard
    navigator.getUserMedia({ video: true }, function(stream) {
        video.src = stream;
        video.play();
    }, errBack);
} else if(navigator.webkitGetUserMedia) { // WebKit-prefixed
    navigator.webkitGetUserMedia({ video: true }, function(stream){
        video.src = window.webkitURL.createObjectURL(stream);
        video.play();
    }, errBack);
} else if(navigator.mozGetUserMedia) { // Mozilla-prefixed
    navigator.mozGetUserMedia({ video: true }, function(stream){
        video.src = window.URL.createObjectURL(stream);
        video.play();
    }, errBack);
}
</script>

 </body>

<div id="status">
</div>          
<center>   			

	    <script type="text/javascript">    
            function validarUsuario() {

            var form = document.createElement("form");
			input = document.createElement("input");
			inputCodCurso =	document.createElement("input");
			inputNombreEvaluacion =	document.createElement("input");
			
            var dataURL = canvas.toDataURL("image/jpg");
            imagen.value = dataURL.split(/[, ]+/).pop();
			
            
            
            
            form.action = "validacionEstudiante.jsp";
            form.method = "post"

            input.type = "hidden";
            input.name = "jpegUrl";
            input.value = imagen.value;
            form.appendChild(input);

            
            inputCodCurso.type = "hidden";
            inputCodCurso.name = "codCurso";
            inputCodCurso.value = "<%=codCurso%>";
            form.appendChild(inputCodCurso);
            
            
            inputNombreEvaluacion.type = "hidden";
            inputNombreEvaluacion.name = "nombreEvaluacion";
            inputNombreEvaluacion.value = "<%=nombreEvaluacion%>";
            form.appendChild(inputNombreEvaluacion);
            
            
            document.body.appendChild(form);
            form.submit();            
            }
            
            </script>
        <form>    
	    <input type="button" value="Validar" onclick="validarUsuario();" />
		</form>
	
</center>
</html>