<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Registrar Tema</title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" media="screen" href="css/style.css">
</head>

</style>
<body>	
	<header>
		<div="main">
			<div class="wrap">
		       		<h2>Registrar un <a class="fuenteLetra3">Subtema </a></h2>
	 		</div>
		</div> 
	

	<div class="contenido">
		<form action="/ServletSubtema2" method="get">
			<p class="fuenteLetra4">Ingrese todos los datos que se solicitan a continuación.</p>
			<table>
				<tr>
					<td class="fuenteLetra5">Descripción</td>
					<td><input type="text" placeholder="   descripcion" name="textDescripcionSubtema" /></td>
				</tr>
				<tr>
				<td><input type="hidden" value="<%=session.getAttribute("DescripcionTema").toString() %>" name="descripcionTema">
				<input type="hidden" name="CursoActualTema" value="<%=session.getAttribute("IdentificadorCurso").toString() %>" >
				<input type="hidden" value="<%=session.getAttribute("logueado").toString()%>" name="correoProfesor"></td>
				<td><input type="submit" class="btn btn-primary" value="Registrar" name="registrarSub" onClick="alert('Se ha registrado el subtema con éxito')"/></td>
				
				</tr>
			</table>
		</form>
	</div>
	</header>
</body>

</html>