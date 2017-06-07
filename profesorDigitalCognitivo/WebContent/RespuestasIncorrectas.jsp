<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 
    <link rel="stylesheet" type="text/css" media="screen" href="css/style.css">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    
    <title>Registrar Tema</title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" media="screen" href="css/style.css">
</head>

</style>
<body>	
	<header>
		<div="main">
			<div class="wrap">
		       		<h2>Agregar <a class="fuenteLetra3">Respuestas Incorrectas </a></h2>
	 		</div>
		</div> 
	

	<div class="contenido">
		<form action="/ServletPregunta" method="get">
			<p class="fuenteLetra4">Ingrese todos los datos que se solicitan a continuación.</p>
			<table style="width: 600px; ">
				<tr>
					<td class="fuenteLetra5">Respuesta Incorrecta</td>
					<td><input type="text" placeholder="   descripcion" name="respuestaIncorrecta" /></td>
					<td><button type="submit" name="agregarRIncorrecta" style="width: 36px; "> <span class="glyphicon glyphicon-plus"></span></button></td>
				</tr>
				<tr>
				<td><input type="hidden" name="DescripcionTema" value=<%=session.getAttribute("DescripcionTema").toString() %>>
				<input type="hidden" name="CodigoCursoActual" value="<%=session.getAttribute("IdentificadorCurso").toString() %>" >
				<input type="hidden" value="<%=session.getAttribute("logueado").toString()%>" name="correoProfesor"></td>
				<td><input type="hidden" name="DescripcionSubtema" value=<%=session.getAttribute("DescripcionSubtema").toString() %>>
				<input type="submit" class="btn btn-primary" value="Finalizar" name="finalizarRIncorrectas" onClick="alert('Se ha registrado la respuesta con éxito')" style="height: 39px; "></td>
				<td><input type="hidden" name="Pregunta" value=<%=session.getAttribute("Pregunta").toString() %>></td>
				<td></td>
				</tr>
			</table>
		</form>
	</div>
	</header>
</body>

</html>