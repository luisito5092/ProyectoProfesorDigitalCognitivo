<%String codigoCurso= request.getParameter("codigo"); %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import = "logicaDeNegocios.dao.DaoEstudiante"%>
	<%@ page import = "logicaDeNegocios.dto.DtoEstudiante"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
    <title>Registrar Tema</title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" media="screen" href="css/style.css">
</head>

</style>
<body>
<% DaoEstudiante listaT=new DaoEstudiante();
	 DtoEstudiante estudiante=new DtoEstudiante();
	  %>	
	<header>
		<div="main">
			<div class="wrap">
		       		<h2>Registrar un <a class="fuenteLetra3">Estudiante </a></h2>
	 		</div>
		</div> 
	

	<div class="contenido">
		<form action="/ServletEstudiantesCurso" method="get">
			<p class="fuenteLetra4">Ingrese todos los datos que se solicitan a continuación.<%=session.getAttribute("IdentificadorCurso").toString() %></p>
			<table>
				
				<tr>
				<td class="fuenteLetra5">Seleccione el estudiante</td>
				
				<td><select class="selectpicker" name="comboboxEstudiante">
				<% for(int i=0; i<listaT.crearListado().size();i++){
					estudiante=listaT.crearListado().get(i);
				%>
				  <option value=<%=estudiante.getIdEstudiante()%>><%=estudiante.getNombre() + " " + estudiante.getPrimerApellido() + " " + estudiante.getSegundoApellido() %></option> 
				
				  <% 
					}	
				%>
				</select></td>
			
				</tr>
				<tr>
				<td>
					<input type="hidden" name="CursoActual" value=<%=session.getAttribute("IdentificadorCurso").toString() %>>
					<input type="hidden" value="<%=session.getAttribute("logueado").toString()%>" name="correoProfesor">
					<input type="submit" value="Agregar Estudiante" name="agregarEstudianteCurso" onClick="alert('Se ha registrado el estudiante con éxito')"/>
				</td>
			</table>
		</form>
	</div>
	</header>
</body>
</html>