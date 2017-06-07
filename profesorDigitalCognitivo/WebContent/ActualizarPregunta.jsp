<%
	 String subtema = session.getAttribute("DescripcionTema").toString();
	 String tema = session.getAttribute("DescripcionSubtema").toString();
	 
	 String pregunta = session.getAttribute("pregunta").toString();
	 String descripcion = session.getAttribute("descripcion").toString();
	 String ayuda = session.getAttribute("ayuda").toString();
	 String respuesta = session.getAttribute("respuesta").toString();
%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>	

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<html>
<head>
    <title>Actualizar pregunta</title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" media="screen" href="css/style.css">
    <script src="js/jquery-1.7.min.js"></script>
    <script src="js/jquery.easing.1.3.js"></script>
    <script src="js/FF-cash.js"></script>
</head>

<body>
	<header>
		<div class="main">
			<div class="wrap">
		       		<h2>Actualizaci�n de <a class="fuenteLetra3"> Pregunta</a></h2>
	 		</div>
		</div> 
	
	<div class="contenido">
		
			<p class="fuenteLetra4">Modifique los datos que se desee a continuaci�n.</p>
			<table>
				<form action="/ServletPregunta" method="post" onsubmit="newsearch(); return false">
					<tr>
						<td class="fuenteLetra5">Pregunta</td>
						<td><input type="text" value="<%=pregunta %>" name="pregunta" /></td>
					</tr>
					<tr>
						<td class="fuenteLetra5">Descripci�n Ayuda</td>
						<td><input type="text" value="<%=ayuda %>" name="ayuda" /></td>
					</tr> 
					<tr>
						<td class="fuenteLetra5">Respuesta Correcta</td>
						<td><input type="text" value="<%=respuesta %>" name="respuesta" /></td>
					</tr>   
							   
					<tr>
						<td><input type="hidden" value="<%=pregunta %>" name="preguntaOriginal" /></td>
						<td><input type="hidden" value="<%=tema %>" name="tema"></td>
						<td><input type="hidden" value="<%=subtema %>" name="subtema"></td>
						<td><input type="hidden" value="<%=descripcion %>"  name="descripcion"></td>
						<td><input type="submit" value="Guardar" name="actualizacionPregunta" /></td>
					</tr>
				</form>
				
			</table>
		
</div>
</header>
			
</body>
</html>