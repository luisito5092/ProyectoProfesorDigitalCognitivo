
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
	<%@ page import = "logicaDeNegocios.dao.DaoTema"%>
	<%@ page import = "logicaDeNegocios.dto.DtoTema"%>
<html>
<head>
    <title>Temas</title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" media="screen" href="css/style.css">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>   

</head>

<body>
	<% DaoTema listaT=new DaoTema();
	 DtoTema tema=new DtoTema();
	 %>
	<header>
		<div="main">
			<div class="wrap">
		       		<h2 class=fuenteletra8>Temas del <a class="fuenteLetra3">Curso <%= session.getAttribute("IdentificadorCurso")%></a></h2>
	 		</div>
		</div> 
     <div class="panel-heading">
          <h4>
            Lista de Temas
          </h4>
        </div><div class="contenido" style="margin:45px auto">
     <form action="/ServletTema" method="get">
     	     <button type="submit" class="btn btn-primary" name="AgregarTema">Agregar Tema</button></hr>
     </form>

	<div class="row">
      <div class="panel panel-default">
        
        <table class="table table-fixed" >
        	<thead>
            <tr>

              <th class="col-xs-2">Descripcion</th>
              <th class="col-xs-2">Opciones</th>
            </tr>
          </thead>
          <tbody>
          	<tr>
				<% for(int i=0; i<listaT.listarTemas(session.getAttribute("IdentificadorCurso").toString()).size();i++){
					tema=listaT.listarTemas(session.getAttribute("IdentificadorCurso").toString()).get(i);
				%>
								<td><%=tema.getDescripcion() %></td>
								<td> 
									<form method="get" action="/ServletTema">
										<button type="submit" name="editarTema"> <span class="glyphicon glyphicon-pencil"></span></button>
										<button type="submit" name="botonEliminarTema"> <span class="glyphicon glyphicon-trash"></span></button>
										<input type="hidden" value=<%=tema.getDescripcion() %> name="Descripcion">
										<input type="hidden" value=<%=session.getAttribute("IdentificadorCurso")%> name="codigoCurso">
										<button type="submit" name="agregarSubtema"> <span class="glyphicon glyphicon-plus"></span></button>
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
	</body>
</html>