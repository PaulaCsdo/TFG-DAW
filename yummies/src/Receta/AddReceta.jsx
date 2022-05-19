import React, { useState, useEffect } from 'react'
import RecetaStep from './RecetaStep'
import IngredientesStep from './IngredientesStep'

const AddReceta = _ => {
  const [showIngredientes, setShowIngredientes] = useState(false)

  
  return (
    <div className='authentication-body'>
        <div className='receta-container'>
            {showIngredientes ? <IngredientesStep idReceta={showIngredientes}/> : <RecetaStep showIngredientes={ id=>setShowIngredientes(id)} />}
        </div>
    </div>
  )
}
export default AddReceta
