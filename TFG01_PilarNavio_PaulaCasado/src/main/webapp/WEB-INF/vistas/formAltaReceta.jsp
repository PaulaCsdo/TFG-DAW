<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Alta de receta</title>
</head>
<body>
<h1>Añadir receta</h1>
	<form action= "/usuario/altaReceta" method="post">
		<label for="idReceta">ID</label>
		<input type="text" name="idReceta"><br>
		
		
		<label for="titulo">Título</label>
		<input type="text" name="titulo"><br>
		
		<label for="imagen">Ruta de la imagen</label>
		<input type="text" name="imagen"><br>
		
		<label for="autor">Autor</label>
		<input type="text" name="autor"><br>
		
		<label for="numPorciones">Número de porciones:</label>
  		<input type="number" id="numPorciones" name="numPorciones" min="1" max="30"><br>
		
		<label for="categoria.idCategoria">Elige una categoria:</label>
		<select name="categoria.idCategoria">
			<c:forEach var="ele" items="${listaCategorias}">
				<option value="${ele.idCategoria}">${ele.descripcion}</option>
			</c:forEach>
		</select><br>
		
<!-- 		<label for="novedad">Novedad</label> -->
<!-- 		<input type="text" name="novedad"> -->


		
		<label for="momento">¿En qué momento se toma?</label><br>
	    
			  <input type="checkbox" id="desayuno" name="desayuno" value="desayuno">
			  <label for="desayuno"> Desayuno</label><br>
			  
			  <input type="checkbox" id="aperitivo" name="aperitivo" value="aperitivo">
			  <label for="aperitivo"> Aperitivo</label><br>
			  
			  <input type="checkbox" id="comida" name="comida" value="comida">
			  <label for="comida"> Comida</label><br>
			
			  <input type="checkbox" id="postre" name="postre" value="postre">
			  <label for="postre"> Postre</label><br>
			  
			  <input type="checkbox" id="cena" name="cena" value="cena">
			  <label for="cena"> Cena</label><br>
			  
			  		
		<input type="submit" value="Registrar">
	</form>
	
	<p>${mensaje}</p>
</body>
</html>