<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ page import = "logicaDeNegocios.dao.DaoCurso"%>
<%@ page import = "logicaDeNegocios.dto.DtoCurso"%>
<% session.setAttribute("cursoEscogido", new String());%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <title>Mis Cursos</title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" media="screen" href="css/style.css">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 
    <script src="tablas.js"></script>   
</head>

</style>
<body>	
	<% DaoCurso listaC=new DaoCurso();
	 DtoCurso curso=new DtoCurso();
	 String correo= session.getAttribute("logueado").toString();
	 String varCurso= request.getParameter("codigo"); %>
	<header>
		<div class="main">
			<div class="wrap">
		       		<h2 class=fuenteletra8>Mis <a class="fuenteLetra3"> Cursos </a></h2>
	 		</div>
	 	</div>
      
      
      <div class="contenido" ></hr>
      <button type="submit" class="btn btn-primary" name=VentanaBitacoras>Bitácoras</button>
	<div class="row">
      <div class="panel panel-default">
        <div class="panel-heading">
          <h4>
            Lista de mis Cursos
          </h4>
        </div>
	<table class="table table-fixed" id="table">
    	<thead>
            	<tr>

		              <th class="col-xs-2">Código</th>
		              <th class="col-xs-3">Descripción</th>
		              <th class="col-xs-3" colspan="2">Opciones</th>
            	</tr>
      	</thead>
<tbody>
        	<tr>
				<% for(int i=0; i<listaC.listarMisCursos(correo).size();i++){
					curso=listaC.listarMisCursos(correo).get(i);
				%>
				<td><%=curso.getCodigo() %> </td>
				<td><%=curso.getDescripcion() %> </td>
				<td>
						<form action="/ServletCurso" method="post">
							<input type="hidden" value="<%=curso.getCodigo() %>" name="CodigoCurso">
							<button type="submit" name="eliminar"> <span class="glyphicon glyphicon-trash"></span></button>
						</form>
				</td>
				<td>
						<form action="/ServletCurso" method="get" style="height: 43px; ">
							<input type="hidden" value="<%=curso.getCodigo() %>" name="CodigoCurso">
							<button type="submit" name="ventanaActualizar"> <span class="glyphicon glyphicon-pencil"></span></button>
							<input type="hidden" value="<%=curso.getDescripcion() %>" name="Descripcion">
							
						</form>
				</td>	
        	</tr>
        				<%
        					}
        				 %>
		</tbody>
	</table>
		        </div>
		      </div>
		  </div>
      </div>
	</header>
</body>
</html>