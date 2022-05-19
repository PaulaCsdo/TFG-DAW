import React, { useState, useEffect } from 'react'
import { Button, Checkbox, Icon } from 'semantic-ui-react'
import Input from '../UI/InputField'
import { useNavigate } from 'react-router-dom'
import axios from 'axios'
import toast from 'react-hot-toast'

const valuesTemplate = {titulo:'', momento:'', numPorciones:'', kcal:'', tiempo:'', categoria:'', nivelCocina:'', pasos: ''}

const CreateReceta = ({showIngredientes}) => {
  const navigate = useNavigate()
  const [categoriasResponse, setCategorias] = useState([])
  const [nivelResponse, setNivel] = useState([])
  const [validated, setValidated] = useState(false)
  const [values, setValue] = useState(valuesTemplate)
  
  const getMethod = (path, setValue) =>{
    axios.get(`http://localhost:8088/rest/usuario/${path}`)
    .then(({data}) => {
      setValue(data)
    })
    .catch(e =>{
      console.log(e)
      toast.error('Ususario o contraseña incorrectos.')
    })
  }

  useEffect( _ => {
    getMethod('verNiveles', setNivel)
    getMethod('verCategorias', setCategorias)
  }, [])
  
  const showRadio = (lista, target, error, targetId  )=> {
    const dropError = validated && values[target] === ''
    return (
      <>
      {dropError && <div className='inputField-error'>{error}</div>}
      <div className='radio-container'>
        {lista.length > 0 &&  lista.map(x => {
          return (
            <Checkbox
              key={`${target}_check_${x[targetId]}`}
              radio
              label={x.descripcion}
              name='checkboxRadioGroup'
              value={x[targetId]}
              checked={values[target][targetId] === x[targetId]}
              onChange={(e, data) => setValue( {...values, [target]: {[targetId] : data.value} } )} 
            />
          )
        } )}        
      </div>
      </>
    )
  }

  const auxInput = (target, label, error, textarea = false) => {
    return(
      <div className="field">
        <Input 
          error={(validated && values[target] === '') && error } 
          value={values[target]} 
          onChange={e=>setValue( {...values, [target]: e.target.value } )} 
          className={`add-receta-input ${values[target] !== '' && 'success-input'} ${textarea && (validated && values[target] === '') && 'errorArea'}`} 
          inputlabel={`${label} Requerido`}
          textarea = {textarea}
          />
      </div>
    )
  }

  const checkEmpty = _ =>{
    for(const key in values){
      if(values[key] === '') return true
    }
  }

  const createReceta = _  => {
    setValidated(true)
    if(!checkEmpty()){
      const currentUser = JSON.parse(localStorage.getItem('YUMMIES_TOKKEN'))
      const auxValues = {...values}
      auxValues.username = currentUser.username
      console.log(auxValues)
      axios.post(`http://localhost:8088/rest/usuario/altaReceta`, auxValues)
      .then(({data}) => {
          console.log(data)
          setValue(valuesTemplate)
          setValidated(false)
          showIngredientes(data?.idReceta)
      })
      .catch(e =>{
        console.log(e)
        toast.error('Ususario o contraseña incorrectos.')
      })
    }
  }

  return (
    <>
      <div className='login-body'>
        <div className='login-form'>
            <div className='head-text'>
            <h2>Nueva receta</h2>
            <p>instrucciones.</p>
            </div>
            <div className='login-container'>
                {auxInput('titulo', 'Ponle un nombre', 'Ingrese un nombre para la receta')}
                {auxInput('momento', '¿En qué momento del día se puede comer?', 'Ingrese un momento para comerla')}
                {auxInput('numPorciones', '¿Cuántos comensales?', 'Ingrese para cuantos comensales es la receta')}
                {auxInput('kcal', '¿Kcal?', 'Ingrese cuantas kcal tiene la receta')}
                {auxInput('tiempo', '¿Cuánto tiempo tarda en hacerse?', 'Ingrese cuanto tiempo tarda en hacerse la receta')}
                <div className="field-radio">
                  <div className='radio-tittle'>¿Cúal es la categoría de esta receta? Selecciona una </div>
                  {showRadio(categoriasResponse, 'categoria', 'Seleccione una categoria', 'idCategoria')}
                </div>
                <div className="field-radio">
                  <div className='radio-tittle'>¿Cúal es el nivel de dificultad? Selecciona uno </div>
                  {showRadio(nivelResponse, 'nivelCocina', 'Seleccione un nivel de dificultad', 'idNivel')}
                </div>
                {auxInput('pasos', 'Pasos a realizar:', 'Ingrese los pasos a realizar en la receta', true)}
                <div className="actions">
                <div className='align-left'>
                  <Button onClick={_=>{navigate(-1)}} basic secondary>Cancelar creacion</Button>
                  <Button onClick={createReceta} primary animated>
                    <Button.Content visible>Continua añadiendo los ingredientes</Button.Content>
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
export default CreateReceta
