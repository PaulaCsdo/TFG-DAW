<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Receta</title>
</head>
<body>

<img src="${receta.imagen}" alt="foto receta" width="500" height="600">

<h1>${receta.titulo}</h1>
<p>${receta.puntuacion}</p>

<p>${num_ingredientes} ingredientes</p>

<p>Dificultad: ${receta.nivelCocina.descripcion}</p>

<p>Se hace en ${receta.tiempo} minutos</p>

<h3>Ingredientes</h3>

<table>
	<c:forEach var="ele" items="${receta.ingredienteEnRecetas}">
		<tr>
			<td> ${ele.ingrediente.descripcion}</td>
			<td> ${ele.cantidad}</td>
			<td> ${ele.unidad}</td>
		</tr>	
	</c:forEach>
</table>

<h3>Pasos</h3>
<c:forEach var="ele" items="${pasosSegmentados}" begin="1">
	<ul> 
		<li> ${ele} </ul>
	</ul>
</c:forEach>


<h4>¿Qué puntuación le das a la receta?</h4>
<p>FALTA POR IMPLEMENTARLO. HE VISTO VÍDEOS PARA HACERLO CON HTML5+BOOTSTRAP+JS </p>
<button>Puntuar</button>

</body>
</html>