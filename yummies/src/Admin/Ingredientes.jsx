import React, { useState, useEffect } from 'react'
import Table from '../UI/Table'
import { Button, Icon, Modal } from 'semantic-ui-react'
import {ingredientesColumns} from './AdminConfiguration/AdminColumns'
import InputField from '../UI/InputField'
import axios from 'axios'
import toast from 'react-hot-toast'


export default function Ingredientes () {

  const [showCreate, setShow] = useState(false)
  const [newIngrediente, setNewIngrediente] = useState('')
  const [ingredientesResponse, setIngredientes] = useState([])

  const getIngredientes = _ =>{
    axios.get(`http://localhost:8088/administrador/verIngredientes`)
    .then(({data}) => {
      setIngredientes(data)
    })
    .catch(e =>{
      console.log(e)
      toast.error('Ususario o contraseña incorrectos.')
    })
  }

  useEffect( _ => {
    getIngredientes()
  }, [])

  const AddIngrediente = _ => {
    axios.post(`http://localhost:8088/administrador/altaIngrediente?descripcion=${newIngrediente}`)
    .then(({data}) => {
      const auxList = [...ingredientesResponse]
      auxList.push(data)
      setIngredientes(auxList)
      toast.success('Ingrediente añadido.')
    })
    .catch(e =>{
      console.log(e)
      toast.error('Ususario o contraseña incorrectos.')
    })
    .finally(_=>{
      setNewIngrediente('')
    })

  }

  const createIngredienteHandler = _ => {
    return (     
      <Button onClick={_=>setShow(true)} color='violet' animated='vertical'>
        <Button.Content visible>Añadir ingrediente</Button.Content>
        <Button.Content hidden>
          <Icon name='add' />
        </Button.Content>
      </Button>
    )
  }

  return (
    <>
      {ingredientesResponse.length > 0 && <Table search data={ingredientesResponse} columns={ingredientesColumns()} > {createIngredienteHandler()} </Table>}
      <Modal
        size='tiny'
        open={showCreate}
        onClose={_=>setShow(true)} 
      >
        <Modal.Header>Añade un nuevo ingrediente</Modal.Header>
        <Modal.Content>
          <InputField value={newIngrediente} placeholder='Nombre de ingredeinte...' inputlabel='Nuevo ingrediente:' onChange={e => setNewIngrediente(e.target.value)}/>
        </Modal.Content>
        <Modal.Actions>
          <Button basic  negative onClick={_=>{setShow(false); setNewIngrediente('')}} >
            Cancelar
          </Button>
          <Button positive onClick={_=>{setShow(false); AddIngrediente()}} >
            Guardar
          </Button>
        </Modal.Actions>
      </Modal>
    </>
  )
}
