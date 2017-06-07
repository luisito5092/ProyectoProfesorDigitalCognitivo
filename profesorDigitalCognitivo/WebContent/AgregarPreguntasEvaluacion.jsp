<%@page import="logicaDeNegocios.dto.DtoPregunta"%>
<%@page import="logicaDeNegocios.dto.DtoParteEvaluacion"%>
<%@page import="logicaDeNegocios.dao.DaoParteEvaluacion"%>
<%
String getCodigo = request.getParameter("codigo");
String getEvaluacion = request.getParameter("nombreEvaluacion");
%>

<%@page import="logicaDeNegocios.dto.DtoEvaluacion"%>
<%@page import="logicaDeNegocios.dao.DaoEvaluacion"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<html>
<head>

    <title>Preguntas</title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" media="screen" href="css/style.css">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>  
    
</head>

</style>
<body>	
	<% DaoParteEvaluacion listaPE=new DaoParteEvaluacion();
	 DtoPregunta pregunta=new DtoPregunta(); %>
	<header>
		<div class="main">
			<div class="wrap">
		       		<h2 class=fuenteletra8 >Asignar preguntas a <a class="fuenteLetra3"> Evaluación  </a></h2>
	 		</div>
      	</div>
 
      
      <div class="contenido" >
           
	<div class="row">
      <div class="panel panel-default">
        <div class="panel-heading">
        	<a href="registrarCurso.jsp"><button type="button" class="btn btn-primary" onClick="alert('Se ha registrado la evaluación con éxito.')">Dejar de asignar preguntas a <%=getEvaluacion%></button></a>
        </div>
        
        <table class="table table-fixed" >
        	<thead>
            <tr>
            	<th class="col-xs-2">Tema</th>
              <th class="col-xs-2">Subtema</th>
              <th class="col-xs-2">Descripcion Pregunta</th>
              <th class="col-xs-3">Pregunta</th>
              <th class="col-xs-2">Agregar</th>
            </tr>
          </thead>
          <tbody>   
          	<tr>
				<% for(int i=0; i<listaPE.listarPreguntasParaEvaluacion(getCodigo, getEvaluacion).size();i++){
					pregunta = listaPE.listarPreguntasParaEvaluacion(getCodigo, getEvaluacion).get(i);
				%>
							<td><%=pregunta.getTema() %> </td>
							<td><%=pregunta.getSubtema() %> </td>
							<td><%=pregunta.getDescripcionPregunta() %> </td>
							<td><%=pregunta.getPregunta() %> </td>	
							<td>
							<form action="/ServletPregunta" method="get	" >
								<input type="hidden" value="<%=getCodigo%>" name="codigoCurso">
								<input type="hidden" value="<%=getEvaluacion%>" name="nombreEvaluacion">
					
								<input type="hidden" value="<%=pregunta.getTema()%>" name="tema">
								<input type="hidden" value="<%=pregunta.getSubtema()%>" name="subtema">
								<input type="hidden" value="<%=pregunta.getDescripcionPregunta()%>" name="descripcion">
								<input type="hidden" value="<%=pregunta.getPregunta()%>" name="pregunta">									
								<button type="submit" name="agregarPreguntaEvaluacion"> <span class="glyphicon glyphicon-pushpin"></span></button>
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