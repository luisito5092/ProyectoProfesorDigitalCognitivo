<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
	<%@ page import = "logicaDeNegocios.Bitacora"%>
	<%@ page import = "logicaDeNegocios.BitacoraCSV"%>
	<%@ page import = "logicaDeNegocios.dto.DtoBitacora"%>
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
	<% Bitacora csv=new BitacoraCSV(System.getProperty("user.home")+"/Bitacora"+session.getAttribute("logueado").toString()+".csv");
	 DtoBitacora dto=new DtoBitacora();%>
	<header>
		<div="main">
			<div class="wrap">
		       		<h2 class=fuenteletra8 >Bitácoras <a class="fuenteLetra3">CSV <%=session.getAttribute("Fecha11").toString()%> <%=session.getAttribute("Fecha22").toString()%></a></h2>
	 		</div>
		</div> 
     <div class="contenido" style="margin:45px auto"></hr>
    <form action="/ServletCurso" method="get">
    	<input type="hidden" value="<%=session.getAttribute("logueado").toString()%>" name="correoProfesor">
	    <input type="hidden" value="<%=session.getAttribute("Fecha11").toString()%>" name="fecha1">
	    <input type="hidden" value="<%=session.getAttribute("Fecha22").toString()%>" name="fecha2">
      <button type="submit" class="btn btn-primary" name=DescargarCSV >Descargar Archivo</button>
      </form>
	<div class="row">
      <div class="panel panel-default">
      
        <div class="panel-heading">
          <h4>
            Estudiantes de la Evaluación:
          </h4>
        </div>
        
        <table class="table table-fixed" >
        	<thead>
            <tr>

              <th class="col-xs-2">Profesor</th>
              <th class="col-xs-2">Fecha</th>
              <th class="col-xs-2">Hora</th>
              <th class="col-xs-3">Acción Realizada</th>
              <th class="col-xs-2">Curso de la Acción</th>
            </tr>
          </thead>
          <tbody>
          	<tr>
				<% 
					for(int i=0; i<csv.leerRegistro(session.getAttribute("Fecha11").toString(), session.getAttribute("Fecha22").toString()).size();i++){
						dto=csv.leerRegistro(session.getAttribute("Fecha11").toString(), session.getAttribute("Fecha22").toString()).get(i);
				%>
							<td><%=dto.getCorreoProfesor()%></td>
        					<td><%=dto.getFecha()%></td>
        					<td><%=dto.getHora()%></td>
        					<td><%=dto.getDescripcion()%></td>
        					<td><%=dto.getCodigoCurso()%></td>        				
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