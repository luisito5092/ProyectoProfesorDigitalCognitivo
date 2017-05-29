<%String codigoCurso= request.getParameter("codigo"); %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import = "logicaDeNegocios.dao.DaoTema"%>
	<%@ page import = "logicaDeNegocios.dto.DtoTema"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
    <title>Registrar Tema</title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" media="screen" href="css/style.css">
</head>

</style>
<body>
<% DaoTema listaT=new DaoTema();
	 DtoTema tema=new DtoTema();
	  %>	
	<header>
		<div="main">
			<div class="wrap">
		       		<h2>Registrar un <a class="fuenteLetra3">Tema </a></h2>
	 		</div>
		</div> 
	

	<div class="contenido">
			<p class="fuenteLetra4">Ingrese todos los datos que se solicitan a continuación.</p>
			<form action="/ServletTema" method="get">
			<table>

				<tr>
				<td class="fuenteLetra5">Temas Registrados</td>
				<td><select class="selectpicker" name="comboboxTema">
				<% for(int i=0; i<listaT.listarTodosTemas().size();i++){
					tema=listaT.listarTodosTemas().get(i);
				%>
				  <option value=<%=tema.getDescripcion()%>><%=tema.getDescripcion()%></option>
				  <% 
					}	
				%>
				<option value="Otro">Otro</option>
				</select></td>
				<tr>
					<td class="fuenteLetra5">Otro</td>
					<td><input type="text" placeholder="   descripcion" name="TextOtro"></td>
				</tr>
				
				<tr>
				<td>
					<input type="hidden" name="CursoActualTema" value="<%=session.getAttribute("IdentificadorCurso").toString() %>" >
					<input type="submit" value="Registrar" name="registrarTemaCurso">
				</td></tr>
			</table>
		</form>
	</div>
	</header>
</body>
</html>