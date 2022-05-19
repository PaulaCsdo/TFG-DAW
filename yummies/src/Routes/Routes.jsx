import React from 'react'
import { Route, Routes } from 'react-router-dom'
import Home from '../Landing/Home'
import Receta from '../Landing/Receta'
import AddReceta from '../Receta/AddReceta'
import RecetasGuardadas from '../Landing/RecetasGuardadas'
import MisRecetas from '../Landing/MisRecetas'

export default function AriaRoutes () {
  return (
    <Routes>
      <Route path='/recetas/:id' element={<Receta />} />
      <Route path='/recetas/*' element={<Home />} />
      <Route path='/*' element={<Home />} />
      <Route path='/addReceta' element={<AddReceta />} />
      <Route path='/recetasGuardadas' element={<RecetasGuardadas />} />
      <Route path='/misRecetas' element={<MisRecetas />} />
    </Routes>
  )
}
