<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
	<%@ page import = "logicaDeNegocios.dao.DaoRespuestaDada"%>
	<%@ page import = "logicaDeNegocios.dto.DtoRespuestaDada"%>
<html>
<head>
    <title>Estado de la Evaluación</title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" media="screen" href="css/style.css">
     <link rel="stylesheet" type="text/css" media="screen" href="css/style.css">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>   
    
</head>
</head>
<body>
	<% DaoRespuestaDada listaRespuestas=new DaoRespuestaDada();
	 DtoRespuestaDada respuesta=new DtoRespuestaDada(); %>
	<header>
		<div="main">
			<div class="wrap">
		       		<h2 class=fuenteletra8 >Respuestas de la <a class="fuenteLetra3">Evaluación</a></h2>
	 		</div>
		</div> 
     <div class="contenido" style="margin:45px auto"></hr>
     <form action="/ServletEvaluacion" method="get">
	<div class="row">
      <div class="panel panel-default">
      
        <div class="panel-heading">
          <h4>
            Respuestas de la Evaluación Realizada:
          </h4>
        </div>
        
        <table class="table table-fixed" >
        	<thead>
            <tr>

              <th class="col-xs-2">Tipo Pregunta</th>}
              <th class="col-xs-2">Pregunta</th>
              <th class="col-xs-2">Respuesta</th>
              <th class="col-xs-3">Correcto</th>
            
            </tr>
          </thead>
          <tbody>
          	<tr>
				<% 
				String curso=session.getAttribute("IdentificadorCurso").toString();
				String evaluacion=session.getAttribute("NombreEvaluacion").toString();
				String estudiante=session.getAttribute("Estudiante").toString();
				int condicion=listaRespuestas.listarRespuestasDadas(curso, evaluacion, estudiante).size();
					for(int i=0; i<condicion;i++){
					respuesta=listaRespuestas.listarRespuestasDadas(curso, evaluacion, estudiante).get(i);
				%>
									<td><%=respuesta.getTipoPregunta() %> </td>
									<td><%=respuesta.getPregunta() %> </td>
									<td><%=respuesta.getRespuesta() %>
									<td><%=respuesta.isCorrecto() %></td>
        				</tr>
        				<%
        					}
        				 %>
        			</tbody>
		        </table>
		        </div>
		      </div>
		      </form>
		  </div>
	</body>
</html>
