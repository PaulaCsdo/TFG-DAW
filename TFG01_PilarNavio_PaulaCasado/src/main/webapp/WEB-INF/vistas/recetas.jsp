<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Recetas</title>
</head>
<body>
	<p> ${mensaje} </p>
	
	<h3>Nuestras Recetas</h3>
		<table border="1">
						
			<tr>					
				<th>Titulo</th>
				<th>Autor</th>
				<th>Imagen</th>
				<th colspan="4">Detalles</th>	
			</tr>
			
			<c:forEach var="ele" items="${listaRecetas}">
				<tr>
					<td>${ele.titulo}</td>
					<td>${ele.usuario.username}</td>
					<td>${ele.imagen}</td>
					<td>${ele.kcal} kcal</td>
					<td>${ele.tiempo} minutos</td>
					<td>${ele.puntuacion}</td>
					<td><a href="/usuario/receta/${ele.idReceta}">Ver receta</a></td>
				</tr>			
			</c:forEach>
		
		</table>

</body>
</html>