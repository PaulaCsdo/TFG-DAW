<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pruebas</title>
</head>
<body>
<p>Hola</p>

<h1>Inicio de sesión</h1>
<form action="/login" method="post">
	<table>
		<tr>
			<td><input type="text" name="username" placeholder="nombre"></td>
		</tr>
		<tr>
			<td><input type="text" name="password" placeholder="contr"></td>
		</tr>
		<tr>
			<td><input type="submit" value="Login"></td>
		</tr>
	</table>
</form>
<p>${mensaje}</p>

</body>
</html>