<%
String getCodigo = request.getParameter("codigoCurso");
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
	<% DaoEvaluacion listaE=new DaoEvaluacion();
	 DtoEvaluacion evaluacion=new DtoEvaluacion(); %>
	<header>
		<div class="main">
			<div class="wrap">
		       		<h2><a class="fuenteLetra3"> Evaluaciones </a></h2>
	 		</div>
      	</div>
      
      <div class="contenido" ></hr>
      <form action="/ServletEvaluacion" method="get">
      	<input type="hidden" name="codigoCurso" value="<%=session.getAttribute("IdentificadorCurso").toString()%>"/>
      	<button type="input" class="btn btn-primary" name="ventanaRegistrar">Agregar Evaluación</button>
      	<a href="EvaluacionesHabilitadas.jsp"><button type="button" class="btn btn-primary" name="evaHabilitada">Evaluaciones Habilitadas</button></a>
      	<a href="EvaluacionesNoHabilitadas.jsp"><button type="button" class="btn btn-primary" name="evaDeshabilitada">Evaluaciones Deshabilitadas</button></a>
      </form>
	<div class="row">
      <div class="panel panel-default">
        <div class="panel-heading">
          <h4>
            Evaluaciones 
          </h4>
        </div>
        <table class="table table-fixed" >
        	<thead>
            <tr>
              <th class="col-xs-2">Nombre</th>
              <th class="col-xs-2">Puntaje Total</th>
              <th class="col-xs-2">Fecha y Hora</th>
              <th class="col-xs-2">Minutos Disponibles</th>
              <th class="col-xs-2">Porcentaje Curso</th>
              <th class="col-xs-3">Descargar Evaluacion</th>
            </tr>
          </thead>
          <tbody>   
          	<tr>
				<% for(int i=0; i<listaE.listarEvaluacionesCurso(getCodigo).size();i++){
					evaluacion = listaE.listarEvaluacionesCurso(getCodigo).get(i);
				%>
							<td><%=evaluacion.getNombreEvaluacion() %> </td>
							<td><%=evaluacion.getPuntajeTotal() %> </td>
							<td><%=evaluacion.getHoraFecha() %> </td>
							<td><%=evaluacion.getMinutosDisponibles() %> </td>
							<td><%=evaluacion.getPorcentajeCurso() %> </td>
							<td>
						<form action="/ServletEvaluacion" method="get" style="height: 43px; ">
							<input type="hidden" value="<%=evaluacion.getNombreEvaluacion() %>" name="NombreEvaluacion">
							<input type="hidden" name="CodigoCursoActual" value="<%=session.getAttribute("IdentificadorCurso").toString()%>">
							<button type="submit" name="GenerarPDF"> <span class="glyphicon glyphicon-save-file"></span></button>
							
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