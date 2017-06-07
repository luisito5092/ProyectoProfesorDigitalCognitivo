<%
String getCodigo = request.getParameter("codigo");
String getDescripcion = request.getParameter("descripcion"); %>	

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Registro Curso</title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" media="screen" href="css/style.css">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 
    <script>
	</script>
</head>

</style>
<body>	
	<header>
		<div="main">
			<div class="wrap">
		       		<h2 class=fuenteletra8>Modificar <a class="fuenteLetra3"> Curso <%= session.getAttribute("IdentificadorCurso")%></a></h2>
	 		</div>
		</div> 

	<div class="contenido">
		<table>
			<td> 
				<form action="/ServletEstudiantesCurso" method="get">
						<button type="submit" class="btn btn-primary" name="ListaEstudiantes">Lista de Estudiantes</button>
						<input type="hidden" value="<%=getCodigo%>"  name="codigoCurso">
				</form>
			</td>
			<td>
				<form action="/ServletEvaluacion" method="get">
					<button type="submit" class="btn btn-primary" name="evaluaciones">Evaluaciones</button>
					<input type="hidden" value="<%=getCodigo%>"  name="codigoCurso">
				</form>
			</td>
			<td>
				<form action="/ServletTema" method="get">
					<button type="submit" class="btn btn-primary" name="listaDeTemas">Lista de Temas</button>
					<input type="hidden" value="<%=getCodigo%>"  name="codigoCurso">
				</form> 
			</td>
		</table> 

		<form action="/ServletCurso" method="get">
			<table>
			<tr>
				<td class="fuenteLetra5">Código:</td>
				<td class="fuenteLetra5"><%=getCodigo%></td>
			</tr>
			<tr>
				<td class="fuenteLetra5" >Descripción</td>
				<td><input type="hidden" value="<%=getCodigo %>" name="codigoOriginal"></td>
				<td><input type="text" value="<%=getDescripcion%>" name="descripcion" /></td>
			</tr>
			<tr>
				<td><input type="submit" name="actualizar" value="Guardar Cambios"></td>
			</tr>
			</table>
		</form>
			   	    
	</div>

	</header>
</body>