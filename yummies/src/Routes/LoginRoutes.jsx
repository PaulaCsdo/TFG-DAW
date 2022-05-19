import React from 'react'
import { Route, Routes } from 'react-router-dom'
import Login from '../Login'
import CreateUser from '../CreateUser'
import Users from '../Admin/Users'
import Home from '../Landing/'
import Recetas from '../Admin/Recetas'
import Ingredientes from '../Admin/Ingredientes'
import AddReceta from '../Receta/AddReceta'


export default function AriaRoutes () {
  return (
    <Routes>
      <Route path='/login' element={<Login />} />
      <Route path='/registration' element={<CreateUser />} />
      <Route path='/*' element={<Home />} />
    </Routes>
  )
}
