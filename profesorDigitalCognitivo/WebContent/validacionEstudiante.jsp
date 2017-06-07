<%@page import="serviciosCognitivos.ServicioReconocimientoFacial"%>
<%@page import="org.omg.PortableInterceptor.SYSTEM_EXCEPTION"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
    <%@ page import = "serviciosCognitivos.ServicioReconocimientoFacial"%>
    <%@page import= "java.util.*" session="true" %>

<html>
<head> <meta charset="UTF-8">
    <title>Validación</title>
    <link rel="stylesheet" type="text/css" media="screen" href="css/style.css">
</head> 
 <body>
<!--  <input type="hidden" id="jpegUrl"/> -->
 
<% String x = request.getParameter("jpegUrl").toString(); 
System.out.println(x);
ServicioReconocimientoFacial test = new ServicioReconocimientoFacial();
test.buscarEstudiante(x, "");

%>
 
 </body>        
<center>
</center>
</html>