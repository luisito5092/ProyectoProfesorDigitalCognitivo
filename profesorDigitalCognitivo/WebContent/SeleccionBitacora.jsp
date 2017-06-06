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
		       		<h2>Selección de <a class="fuenteLetra3">Bitácoras </a></h2>
	 		</div>
		</div> 
	

	<div class="contenido">
			<p class="fuenteLetra4">Seleccione los parámetros con los que desea desplegar las bitácoras:</p>
			<form action="/ServletCurso" method="get">
			<table>

				<tr>
				<td class="fuenteLetra5">Fecha de Inicio</td>
				<td><input type="date" name="fechaInicio" /></td>
				<td class="fuenteLetra5">Fecha Final</td>
				<td><input type="date" name="fechaFinal" /></td>
				<tr>
					<td class="fuenteLetra5">Tipo de Bitácora</td>
					<td><select class="selectpicker" name="comboboxBitacora" style="width: 153px; ">
					<option value="CSV">CSV</option>
					<option value="XML">XML</option>
					<option value="Posicional">Posicional</option>
					</select></td>
				</tr>
				
				<tr>
				<td>
					<input type="submit" value="Consultar" name="ConsultarBitacoras">
					<input type="hidden" value="<%=session.getAttribute("logueado").toString()%>" name="correoProfesor">
				</td></tr>
			</table>
		</form>
	</div>
	</header>
</body>
</html>