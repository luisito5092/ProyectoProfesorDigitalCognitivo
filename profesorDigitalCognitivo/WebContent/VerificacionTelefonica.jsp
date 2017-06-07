<%
String codigoVerificacion = session.getAttribute("codigoVerificacion").toString();
%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
    <title>Verificaci�n SMS</title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" media="screen" href="css/style.css">
</head>

</style>
<body>
	<header>
		<div="main">
			<div class="wrap"> 
		       		<h2>Verificaci�n por<a class="fuenteLetra3"> SMS <%=session.getAttribute("codigoVerificacion")%></a></h2>
	 		</div>
		</div> 
	<div class="contenido">
		<p class="fuenteLetra4">Se ha enviado el c�digo de verificaci�n al n�mero telef�nico <%=session.getAttribute("telefono")%></p>
		
		<form action="/ServletLogin" method="post">
			<center><table>
				<tr>
					<td><input type="text" name="codigoVerificacion"  placeholder="    C�digo de Verificaci�n"></td>
				</tr>
				<tr>
					<td>						
						<input type="submit" value="   Iniciar   " name="verificacionSMS">
					</td>
				</tr>
				<tr>					
					<td><p>*Oportunidades restantes: <%=session.getAttribute("contador")%></p></td>
				</tr>
					
				
			</table></center>
		</form>
	</div>
	</header>
</body>
</html>