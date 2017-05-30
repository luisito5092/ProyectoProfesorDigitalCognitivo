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
		       		<h2 class=fuenteletra8 >Evaluaciones <a class="fuenteLetra3">Habilitadas</a></h2>
	 		</div>
      	</div>
      
      <div class="contenido" ></hr>
	<div class="row">
      <div class="panel panel-default">
        <div class="panel-heading">
          <h4>
            Evaluaciones 
          </h4>
        </div>
        <form action="/ServletEvaluacion" method="get">
        <table class="table table-fixed" >
        	<thead>
            <tr>
              <th class="col-xs-1">Nombre</th>
              <th class="col-xs-1">Puntaje Total</th>
              <th class="col-xs-2">Fecha y Hora</th>
              <th class="col-xs-2">Minutos Disponibles</th>
              <th class="col-xs-2">Porcentaje Curso</th>
              <th class="col-xs-2">Detalle Actual</th>
            </tr>
          </thead>
          <tbody>   
          	<tr>
				<% for(int i=0; i<listaE.listarEvaluacionesHabilitadasCurso(session.getAttribute("IdentificadorCurso").toString()).size();i++){
					evaluacion = listaE.listarEvaluacionesHabilitadasCurso(session.getAttribute("IdentificadorCurso").toString()).get(i);
				%>
							<td><%=evaluacion.getNombreEvaluacion() %> </td>
							<td><%=evaluacion.getPuntajeTotal() %> </td>
							<td><%=evaluacion.getHoraFecha() %> </td>
							<td><%=evaluacion.getMinutosDisponibles() %> </td>
							<td><%=evaluacion.getPorcentajeCurso() %> </td>
							<td><button type="submit" name="estadoEvaluacion" value="<%=evaluacion.getNombreEvaluacion()%>">  <span class="glyphicon glyphicon-stats"></span></button></td>
        				</tr>
        				<%
        					}
        				 %>
        			</tbody>
		        </table>
		        
		        </form>
		        </div>
		      </div>
		  </div>
      </div>
	</header>
</body>
</html>