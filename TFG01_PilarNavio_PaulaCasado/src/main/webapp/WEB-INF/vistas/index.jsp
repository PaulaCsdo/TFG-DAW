<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Usuario - Home</title>
</head>

<body>
<a href="/logout">Salir</a>
	
	<h1>¿Qué quieres hacer?</h1>
	<h3>Pulsa sobre la opción</h3>
	
	<a href="/usuario/verTodas">Ver todas las recetas</a>
	<a href="/usuario/guardadas">Ver las recetas que me gustan</a>
	<a href="/usuario/misRecetas">Ver mis propias recetas</a>
	<a href="/usuario/altaReceta">Crear receta</a>
	<a href="#">Preferencias</a>
	<a href="#">Ver mi perfil</a>
	

	
	<p>${mensaje}
</body>
</html>