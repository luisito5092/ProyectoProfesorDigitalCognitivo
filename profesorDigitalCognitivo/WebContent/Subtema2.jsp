<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
	<%@ page import = "logicaDeNegocios.dao.DaoSubtema"%>
	<%@ page import = "logicaDeNegocios.dto.DtoSubtema"%>
<html>
<head>
    <title>Subtemas</title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" media="screen" href="css/style.css">
     <link rel="stylesheet" type="text/css" media="screen" href="css/style.css">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>   
    
</head>
</style>
<body>	
	<% DaoSubtema listaSubtema=new DaoSubtema();
	 DtoSubtema subtema=new DtoSubtema(); %><header>
		<div main="">
			<div class="wrap">
		       		<h2 class=fuenteletra8>Subtemas del<a class="fuenteLetra3"> Curso <%= session.getAttribute("IdentificadorCurso")%></h2>
	 		</div>
		</div> 
     <div class="contenido" style="margin:45px auto"><hr>
     <form action="/ServletSubtema2" method="get">
     <a href="registrarSubtema.jsp"><button type="button" class="btn btn-primary" name="botonAgregarSubtemas">Agregar Subtemas</button></a><hr>
     </form>
     
	<div class="row">
      <div class="panel panel-default">
        <div class="panel-heading">
          <h4>
              Lista de Subtemas
          </h4>
        </div>
        <table class="table table-fixed">
        	<thead>
            <tr>
              <th class="col-xs-2">Descripción</th>
              <th class="col-xs-2">Opciones</th>
            </tr>
          </thead>
          <tbody>
          	<tr>
				<% for(int i=0; i<listaSubtema.listarSubtemasTema(session.getAttribute("DescripcionTema").toString()).size();i++){
					subtema=listaSubtema.listarSubtemasTema(session.getAttribute("DescripcionTema").toString()).get(i);
				%>
									<td><%=subtema.getDescripcion() %> </td>
									<td> 
										<form method="get" action="/ServletSubtema2">
											<button type="submit" name="editarSubtema">  <span class="glyphicon glyphicon-pencil"></span></button>
											<button type="submit" name="eliminarSubtema"> <span class="glyphicon glyphicon-trash"></span></button>
											<input type="hidden" name="descripcionSubtema" value=<%=subtema.getDescripcion() %>>
											<input type="hidden" name="CodigoCursoActual" value="<%=session.getAttribute("IdentificadorCurso").toString()%>">
											<input type="hidden" value="<%=session.getAttribute("logueado").toString()%>" name="correoProfesor">
											<button type="submit" name="agregarPregunta"> <span class="glyphicon glyphicon-plus"></span></button>
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
	</header>
	</body>
</html>