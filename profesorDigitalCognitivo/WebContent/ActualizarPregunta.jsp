
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ page import = "logicaDeNegocios.dao.DaoPregunta"%>
<%@ page import = "logicaDeNegocios.dto.DtoPregunta"%>
<html>
<head>
    <title>Actualizar pregunta</title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" media="screen" href="css/style.css">
    <script src="js/jquery-1.7.min.js"></script>
    <script src="js/jquery.easing.1.3.js"></script>
    <script src="js/FF-cash.js"></script>
</head>

</style>
<body>
	<%
	 String subtema=session.getAttribute("subtema").toString();
	 String tema=session.getAttribute("tema").toString();
	 String pregunta = request.getParameter("pregunta");
	 String descripcion = request.getParameter("descripcion");
	 String ayuda = request.getParameter("ayuda");
	 String respuesta = request.getParameter("respuesta");
	 DaoPregunta daoPregunta=new DaoPregunta();
	 DtoPregunta dtoPregunta=new DtoPregunta();
	 %>	
	<header>
		<div="main">
			<div class="wrap">
		       		<h2>Actualización de <a class="fuenteLetra3"> Pregunta</a></h2>
	 		</div>
		</div> 
	
	<div class="contenido">
		<form action="/ServletPregunta" method="post">
			<p class="fuenteLetra4">Modifique los datos que se desee a continuación.</p>
			<table>
			<tr>
				<td class="fuenteLetra5">Pregunta</td>
				<td><input type="text" value=<%=pregunta %> name="pregunta" /></td>
			</tr>
			<tr>
				<td class="fuenteLetra5">Descripción Ayuda</td>
				<td><input type="text" value=<%=ayuda %> name="ayuda" /></td>
			</tr>
			<tr>
				<td class="fuenteLetra5">Respuesta Correcta</td>
				<td><input type="text" value=<%=respuesta %> name="respuesta" /></td>
			</tr>
			
			<tr>
				<td><input type="hidden" value="<%=tema %>" name="tema"></td>
				<td><input type="hidden" value="<%=subtema %>" name="subtema"></td>
				<td><input type="hidden" value="<%=descripcion %>" name="descripcion"></td>
				<td><input type="submit" value="Guardar" name="actualizacionPregunta" /></td>
			</tr>
		</table>
	</form>
</div>
</header>
			
</body>
</html>