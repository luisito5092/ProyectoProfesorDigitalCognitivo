<%
String getCodigo = request.getParameter("codigoCurso");
String getDescripcion = request.getParameter("Descripcion");
%>

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
		       		<h2>Actualizar <a class="fuenteLetra3">Tema %></a></h2>
	 		</div>
		</div> 
	
	<div class="contenido">
			<p class="fuenteLetra4">Actualice los datos que desee a continuación.</p>
		<form action="/ServletTema" method="post">
			<table>
				<tr>
					<td><input type="text" value="<%= getDescripcion %>" name="nuevoTema"></td>
				<tr>
					<td>
						<input type="hidden" name="tema" value="<%=getCodigo %>" >
						<input type="submit" value="Actualizar" name="realizarActualizacion">
					</td>
				</tr>
			</table>
		</form>
	</div>
	</header>
</body>
</html>