
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Registro Estudiante</title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" media="screen" href="css/style.css">
    <script src="js/jquery-1.7.min.js"></script>
    <script src="js/jquery.easing.1.3.js"></script>
    <script src="js/FF-cash.js"></script>
</head>

</style>
<body>	
	<header>
		<div="main">
			<div class="wrap">
		       		<h2>Registro de <a class="fuenteLetra3">Nuevo Estudiante</a></h2>
	 		</div>
		</div> 
	

	<div class="contenido">
		<form action="/ServletEstudiante2" method="post">
			<p class="fuenteLetra4">Ingrese todos los datos que se solicitan a continuaci�n.</p>
			<table>
			<tr>
				<td class="fuenteLetra5">Nombre</td>
				<td><input type="text" placeholder="   nombre" name="nombre" /></td>
			</tr> 
			<tr>
				<td class="fuenteLetra5">Primer Apellido</td>
				<td><input type="text" placeholder="   primer apellido" name="primerApellido" /></td>
			</tr>
			<tr>
				<td class="fuenteLetra5">Segundo Apellido</td>
				<td><input type="text" placeholder="   segundo apellido" name="segundoApellido" /></td>
			</tr>
			<tr>
				<td class="fuenteLetra5">Identificaci�n</td>
				<td><input type="text" placeholder="   identificaci�n" name="identificacion" /></td>
			</tr>
			<tr>
				<td class="fuenteLetra5">Carn�</td>
				<td><input type="text" placeholder="   carn�" name="carne" /></td>
			</tr>
			<tr>
				<td class="fuenteLetra5">N�mero telef�nico</td>
				<td><input type="text" placeholder="   n�mero telef�nico" name="telefono" /></td>
			</tr>
			<tr>
				<td class="fuenteLetra5">Correo Electr�nico</td>
				<td><input type="email" placeholder="   correo electr�nico" name="correo" /></td>
			</tr>
			<tr>
				<td class="fuenteLetra5">Fecha de Nacimiento</td>
				<td><input type="date" name="fecha" /></td>
			</tr>
			
			<tr>
				<td><input type="submit" value="Registrar" id="registrar" name="registrarNuevoEstudiante" onClick="alert('Se ha registrado el estudiante con �xito');"/></td>
			</tr>
		</table>
		</form>
	</div>
</header>

	<script>
function goBack() {
    window.history.back();
}
</script>
</body>
</html>