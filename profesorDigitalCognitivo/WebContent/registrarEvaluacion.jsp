<% String getCurso = request.getParameter("codigoCurso"); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Registro Evaluaci�n Sumativa</title>
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
		       		<h2>Registro de <a class="fuenteLetra3">Nueva Evaluaci�n</a></h2>
	 		</div>
		</div> 
	</header>

	<div class="contenido">
		<form action="/ServletEvaluacion" method="get">
			<p class="fuenteLetra4">Ingrese todos los datos que se solicitan a continuaci�n.</p>
			<table>
				<tr>
					<td class="fuenteLetra5">Nombre</td>
					<td><input type="text" placeholder="   nombre" name="nombre" /></td>
				</tr> 
				<tr>
					<td class="fuenteLetra5">Puntaje Total</td>
					<td><input type="text" placeholder="   puntaje total" name="puntaje" /></td>
				</tr>
				<tr>
					<td class="fuenteLetra5">Porcentaje correspondiente del curso</td>
					<td><input type="text" placeholder="   porcentaje curso" name="porcentajeCurso" /></td>
				</tr>
				<tr>
					<td class="fuenteLetra5">Minutos disponibles para realizar evaluaci�n</td>
					<td><input type="text" placeholder="   minutos disponibles" name="minutos" /></td>
				</tr>
				<tr>
					<td class="fuenteLetra5">Hora y Fecha del registro</td>
					<td><input type="datetime-local" placeholder="   hora y fecha" name="horaYFecha" /></td>
				</tr>
				<tr>
					<td class="fuenteLetra5">Tipo de Evaluaci�n</td>
					<td><INPUT TYPE="radio" name="tipo" VALUE="sumativa" CHECKED>Evaluaci�n Sumativa<BR>
	            		<INPUT TYPE="radio" name="tipo" VALUE="formativa">Evaluaci�n Formativa<BR></td>
				</tr>
				<tr>
					<td class="fuenteLetra5">Partes de la evaluaci�n</td>
					<th><INPUT TYPE="checkbox" name="seleccionUnica" VALUE="seleccionUnica" CHECKED>Selecci�n �nica<BR>
						<INPUT TYPE="checkbox" name="complete" VALUE="complete" CHECKED>Complete<BR>
						<INPUT TYPE="checkbox" name="desarrollo" VALUE="desarrollo" CHECKED>Desarrollo<BR></th>
	            	<td><INPUT TYPE="text" placeholder="   puntaje selecci�n �nica" name="puntosSeleccionUnica"><BR>
						<INPUT TYPE="text" placeholder="   puntaje complete"  name="puntosComplete"><BR>
						<INPUT TYPE="text" placeholder="   puntaje desarrollo"  name="puntosDesarrollo"></td>	
			</tr>
			</table>
			<input type="hidden" value="<%=getCurso%>" name="codigoCurso"/>
			<input type="hidden" value="<%=session.getAttribute("logueado").toString()%>" name="correoProfesor"/>
			<input type="submit" value="Continuar" name="registrar"/>
		</form>
	</div>
</body>
</html>