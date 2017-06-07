
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Registrar Profesor</title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" media="screen" href="css/style.css">
</head>

</style>
<body>	
	<header>
		<div="main">
			<div class="wrap">
		       		<h2>Registrar a <a class="fuenteLetra3">Nuevo Profesor</a></h2>
	 		</div>
		</div> 
	

	<div class="contenido">
		<form action="/ServletProfesorFixed" method="post">
			<p class="fuenteLetra4">Ingrese todos los datos que se solicitan a continuación.</p>
			<table>
				<tr>
					<td class="fuenteLetra5">Nombre Completo</td>
					<td><input type="text" placeholder="   nombre" name="nombre" /></td>
				</tr>
				<tr>
					<td class="fuenteLetra5">Número telefónico</td>
					<td><input type="text" placeholder="   número telefónico" name="telefono" /></td>
				</tr>
				<tr>
					<td class="fuenteLetra5">Correo Electrónico</td>
					<td><input type="text" placeholder="  correo electrónico" name="correo" /></td>
				</tr>
				<tr>
					<td class="fuenteLetra5">Contraseña</td>
					<td><input type="password" placeholder="   contraseña" name="contrasenia" /></td>
				</tr>
				<tr>
				<td><input type="submit" value="Registrar" id="registrar" onClick="alert('Se ha registrado el profesor con éxito')"/></td></tr>
			</table>
		</form>
	</div>
	</header>
</body>

</html>