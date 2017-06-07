<%@page import="serviciosCognitivos.ServicioReconocimientoFacial"%>
<%@page import="org.omg.PortableInterceptor.SYSTEM_EXCEPTION"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
    <%@ page import = "serviciosCognitivos.ServicioReconocimientoFacial"%>
    <%@page import= "java.util.*" session="true" %>

<html>
<head> <meta charset="ISO-8859-1">
    <title>Validación</title>
    <link rel="stylesheet" type="text/css" media="screen" href="css/style.css">
</head> 
 <body>
 
<% String imagen = request.getParameter("jpegUrl").toString(); 
String codCurso = request.getParameter("codCurso").toString(); 
String nombreEvaluacion = request.getParameter("nombreEvaluacion").toString();

ServicioReconocimientoFacial test = new ServicioReconocimientoFacial();
String logueado = test.buscarEstudiante(imagen);
if (logueado == null) {
	%>
    <form>
    Acceso Denegado
	</form>
	<%
}
else {
	session.setAttribute("user", logueado);
	%>
	    <form action="jejeJoseAndres.jsp" method="post">
	    <h3 class="fuenteLetra3">Bienvenido:   <%=logueado%>  /  <%=codCurso%>   /  <%=nombreEvaluacion%> </h3>
	    <input type="hidden" name="user" value="<%=logueado%>">
	    <input type="hidden" name="user" value="<%=codCurso%>">
	    <input type="hidden" name="user" value="<%=nombreEvaluacion%>">
	    <input type="submit" value="Siguiente">
		</form>
		<%}
%>
 </body>        
<center>
</center>
</html>