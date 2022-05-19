import React, { useState, useEffect } from 'react'
import { Button, Divider, Icon, Dropdown, Segment, Message } from 'semantic-ui-react'
import Input from '../UI/InputField'
import axios from 'axios'
import toast from 'react-hot-toast'
import { useNavigate } from 'react-router-dom'

const valuesTemplate = {idIngrediente:'', cantidad:'', unidad:''}

const AddIngredientes = ({idReceta}) => {
  const navigate = useNavigate()
  const [ingredienteList, setIngredienteList] = useState([])
  const [ingredienteOptions, setIngredientesOptions] = useState([])
  const [validated, setValidated] = useState(false)
  const [values, setValue] = useState(valuesTemplate)

  const getIngredientes = _ =>{
    axios.get(`http://localhost:8088/administrador/verIngredientes`)
    .then(({data}) => {
      const auxOptions = data.map(x => ({
        key: x.idIngrediente,
        text: x.descripcion,
        value: x.idIngrediente
      }))
      setIngredientesOptions(auxOptions)
    })
    .catch(e =>{
      console.log(e)
      toast.error('Ususario o contraseña incorrectos.')
    })
  }

  useEffect( _ => {
    getIngredientes()
  }, [])
  
  const addIngredienteForm = _ => {
    const dropError = validated && values.idIngrediente === ''
    return (
      <div className="add-ingrediente-container">
        <div className='add-ingrediente-drop-container'>
          <div className='add-ingrediente-drop-label'>Selecciona un ingrediente</div>
          <Dropdown 
            error={dropError} 
            value={values.idIngrediente}
            onChange={(e, { value }) => setValue( {...values, idIngrediente: value } )}
            className={`add-ingrediente-drop ${values.idIngrediente !== '' && 'success-input'}`} 
            search
            selection
            options={ingredienteOptions} />
          {dropError && <div className='inputField-error'>Seleccione un ingrediente</div>}
        </div>
        {auxInput('cantidad', 'Cantidad', 'Añade una cantidad')}
        {auxInput('unidad', 'Unidad', 'Añade una unidad')}
        <Button onClick={addIngrediente} primary>Añadir ingrediente</Button>
      </div>
    )
  }

  const auxInput = (target, label, error) => {
    return(
      <Input 
        error={(validated && values[target] === '') && error } 
        value={values[target]}
        onChange={e=>setValue( {...values, [target]: e.target.value } )} 
        className={`add-ingrediente-input ${values[target] !== '' && 'success-input'}`} 
        inputlabel={label}/>
    )
  }

  const listaIngredientes = _ =>{
    return (
      <div>
        {ingredienteList.map(x=>{return(
          <div className="ingrediente-container">
            <Segment>
              <div className='ingrediente-segment'>
                <div>{ingredienteOptions.find(ing=> ing.value === x.idIngrediente ).text}</div>
                <div className='cantidad'>{`${x.cantidad} ${x.unidad}`}</div>
              </div>
            </Segment>
          </div>
        )})}
      </div>
    )
  }

  const checkEmpty = _ =>{
    for(const key in values){
      if(values[key] === '') return true
    }
  }

  const addIngrediente = _  => {
    setValidated(true)
    if(!checkEmpty()){
      const auxValues = {...values}
      auxValues.idReceta = idReceta
      console.log(auxValues)
      axios.post(`http://localhost:8088/rest/usuario/anadirIngrediente`, auxValues)
      .then(({data}) => {
          console.log(data)
          const auxList = [...ingredienteList]
          auxList.push(values)
          setIngredienteList(auxList)
          setValue(valuesTemplate)
          setValidated(false)
      })
      .catch(e =>{
        console.log(e)
        toast.error('Ususario o contraseña incorrectos.')
      })
    }
  }

  return (
    <>
      {/* <div className='ingredientes-image' /> */}
      <div className='receta-body'>
        <div className='login-form'>
            <div className='head-text'>
            <h2>Nueva receta</h2>
            <Message color='yellow'>Aqui puedes añadir los ingredientes de la receta. Una vez finalices, puedes ver la receta creada en "Mis Recetas"</Message>
            </div>
            <div className='login-container'>
                {addIngredienteForm()}
                <Divider />
                <div>Lista de ingredientes añadidos</div>
                {listaIngredientes()}
                <div className="actions">
                <Message color='yellow'>¿Has acabado? Pulsa en el botón para ir a "Mis Recetas" y ver la receta que acabas de crear.</Message>
                <div className='align-left'>
                  <Button onClick={_=>navigate('/misRecetas')} primary animated>
                    <Button.Content visible>Ir a mis recetas</Button.Content>
                    <Button.Content hidden>
                      <Icon name='arrow right' />
                    </Button.Content>
                  </Button>
                </div>
                </div>
            </div>
        </div>
      </div>
    </>
  )
}
export default AddIngredientes
