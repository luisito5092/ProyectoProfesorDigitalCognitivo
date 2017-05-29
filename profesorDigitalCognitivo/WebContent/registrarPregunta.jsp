
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Registro Evaluación Sumativa</title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" media="screen" href="css/style.css">
    <script src="js/jquery-1.7.min.js"></script>
    <script src="js/jquery.easing.1.3.js"></script>
    <script src="js/FF-cash.js"></script>
</head>

</style>
<body>	
	<header>
		<div="main">
			<div class="wrap">
		       		<h2>Registro de <a class="fuenteLetra3">Nueva Pregunta</a></h2>
	 		</div>
		</div> 
	

	<div class="contenido">
		<form action="/ServletPregunta" method="get">
			<p class="fuenteLetra4">Ingrese todos los datos que se solicitan a continuación.</p>
			<table>
			<tr>
				<td class="fuenteLetra5">Pregunta</td>
				<td><input type="text" placeholder="   pregunta" name="pregunta" /></td>
			</tr>
			<tr>
				<td class="fuenteLetra5">Descripción Ayuda</td>
				<td><input type="text" placeholder=" Descripción Ayuda" name="ayuda" /></td>
			</tr>
			<tr>
				<td class="fuenteLetra5">Respuesta Correcta</td>
				<td><input type="text" placeholder=" Respuesta Correcta" name="respuestaCorrecta" /></td>
			</tr>

			<tr>
				<td class="fuenteLetra5">Tipo de Pregunta</td>
				<td><INPUT TYPE="radio" name="radios" value="Selección Única" CHECKED>Seleccion Unica<BR>
					<INPUT TYPE="radio" name="radios" value="Complete" >Complete<BR>
            		<INPUT TYPE="radio" name="radios" value="Desarrollo">Desarrollo<BR></td>
				<td></td>
			</tr>
			
			<tr>
			<td><input type="hidden" value="<%=session.getAttribute("DescripcionTema").toString() %>" name="DescripcionTema"></td>
			<td><input type="hidden" value="<%=session.getAttribute("DescripcionSubtema").toString() %>" name="DescripcionSubtema"></td>
			<td><input type="submit" value="Registrar" name="guardarPregunta" /></td>
			</tr>
		</table>
	</form>
</div>
</header>
			
</body>
</html>