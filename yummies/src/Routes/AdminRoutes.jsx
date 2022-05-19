import React from 'react'
import { Navigate, Route, Routes } from 'react-router-dom'
import Users from '../Admin/Users'
import Recetas from '../Admin/Recetas'
import Ingredientes from '../Admin/Ingredientes'
import AddReceta from '../Receta/AddReceta'

export default function AriaRoutes () {
  return (
    <Routes>
      <Route path='/*' element={<Users />} />
      <Route path='/admin/*' element={<Users />} />
      <Route path='/admin/usuarios' element={<Users />} />
      <Route path='/admin/recetas' element={<Recetas />} />
      <Route path='/admin/ingredientes' element={<Ingredientes />} />
      <Route path='/addReceta' element={<AddReceta />} />
    </Routes>
  )
}
