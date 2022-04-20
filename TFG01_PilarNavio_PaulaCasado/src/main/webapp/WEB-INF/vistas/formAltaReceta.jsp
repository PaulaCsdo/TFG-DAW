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
<!-- 		<label for="idReceta">ID</label> -->
<!-- 		<input type="text" name="idReceta"><br> -->
		
		
		<label for="titulo">Título</label>
		<input type="text" name="titulo"><br>
		
		<label for="imagen">Inserta una imagen</label>
		<input type="file" id="imagen" name="imagen" accept="image/png, image/jpeg">
		
<!-- 		<label for="autor">Autor</label> -->
<!-- 		<input type="text" name="autor"><br> -->
		
		<label for="numPorciones">Número de porciones:</label>
  		<input type="number" id="numPorciones" name="numPorciones" min="1" max="30"><br>
  		
  		<label for="ingredientes">Ingredientes:</label>
  		<form action="" method="post">
		  <fieldset>
		    <legend>Título</legend>
		    <input type="checkbox" name="ingr1" id="ingr1"> <label for="ingr1">Ingrediente 1</label>
		    <input type="submit" value="Añadir estos ingredientes">
		  </fieldset>
		</form>
  		<!-- DUDA: Tenemos diferentes opciones para sacar los ingredientes:
  					a) Sacar todos los ingredientes con img como si fuese el panel para pesar la fruta del supermercado.
  					b) Sacar un input text y mediante llamadas ajax sacar los ingredientes que contengan ese nombre.
  			De cualquier forma tenemos que trabajar el DOM para sacar campos de ingredientes pq no sabemos a priori cuantos ingredientes va a necesitar.
  		-->
  		
		
		<label for="pasos">Pasos:</label>
		<textarea id="story" name="story" rows="5" cols="33"></textarea> 
		<button class="addPaso" type="button">Añadir paso</button> <!-- Sacaremos con JS otro textarea si hace clic en el. Aparecerá entre el ultimo textarea y el boton -->
		
		<label for="dificultad">Dificultad:</label>
		   <input type="radio" id="baja"   name="dificultad" value="baja">
		   <label for="baja">Baja</label>
		   
		   <input type="radio" id="media"   name="dificultad" value="media">
		   <label for="media">Media</label>

			<input type="radio" id="alta"   name="dificultad" value="alta">
		   	<label for="alta">Alta</label>
		
		
		<label for="categoria.idCategoria">Elige una categoria:</label>
		<select name="categoria.idCategoria">
			<c:forEach var="ele" items="${listaCategorias}">
				<option value="${ele.idCategoria}">${ele.descripcion}</option>
			</c:forEach>
		</select><br>
		
<!-- 		<label for="novedad">Novedad</label> -->
<!-- 		<input type="text" name="novedad"> -->


		<label for="tiempo">¿Cuánto tiempo se tarda en hacerla?</label><br>
		<input type="number" id="tiempo" name="tiempo" min="0" step="5"><br>
		
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