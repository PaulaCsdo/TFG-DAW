import React, { useState, useEffect } from 'react'
import Table from '../UI/Table'
import { Icon, Button } from 'semantic-ui-react'
import {recetasColumns} from './AdminConfiguration/AdminColumns'
import { useNavigate } from 'react-router-dom'
import axios from 'axios'
import toast from 'react-hot-toast'


export default function Recetas () {
  const navigate = useNavigate()
  const [recetaResponse, setRecetas] = useState([])
  const getRecetas = _ =>{
    axios.get(`http://localhost:8088/administrador/verRecetas`)
    .then(({data}) => {
      setRecetas(data)
    })
    .catch(e =>{
      console.log(e)
      toast.error('Ususario o contraseña incorrectos.')
    })
  }

  useEffect( _ => {
    getRecetas()
  }, [])
  const createRecetaHandler = _ => {
    return (     
      <Button onClick={_=>navigate('/addReceta')} color='violet' animated='vertical'>
        <Button.Content visible>Añadir receta</Button.Content>
        <Button.Content hidden>
          <Icon name='add' />
        </Button.Content>
      </Button>
    )
  }

  return (
    <>
      {recetaResponse.length > 0 && <Table search data={recetaResponse} columns={recetasColumns()}> {createRecetaHandler()} </Table>}
    </>
  )
}
