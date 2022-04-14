<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Recetas</title>
</head>
<body>
	<p> ${mensaje} </p>
	
	<h3>Nuestras novedades</h3>
		<table border="1">
						
			<tr>					
				<th>Titulo</th>
				<th>Autor</th>
				<th>Imagen</th>
				<th colspan="3">Detalles</th>	
			</tr>
			
			<c:forEach var="ele" items="${listaNovedades}">
				<tr>
					<td>${ele.titulo}</td>
					<td>${ele.autor}</td>
					<td>${ele.imagen}</td>
					<td>${ele.kcal}</td>
					<td>${ele.tiempo}</td>
					<td>${ele.puntuacion}</td>
				</tr>			
			</c:forEach>
		
		</table>

</body>
</html>