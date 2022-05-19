import React from 'react'

export const usersColumns = _ => {
	return [
		{ name: 'Nombre de usuario', key: 'username' },
		{ name: 'Email', key: 'email' },
		{ name: 'Nombre', key: 'nombre' },
		{ name: 'Apellido', key: 'apellido' },
	]
}

export const recetasColumns = _ => {
	return [
		{ name: 'Nombre de receta', key: 'titulo' },
		{ name: 'Momento', key: 'momento' },
		{ name: 'Kcal', key: 'kcal' },
		{ name: 'Novedad', key: 'novedad' },
	]
}

export const ingredientesColumns = _ => {
	return [
		{ name: 'Nombre de ingredeinte', key: 'descripcion' },
		{ name: 'ID', key: 'idIngrediente' },
	]
}
