import React, { useState, useEffect } from 'react'
import { Button, Segment } from 'semantic-ui-react'
import Input from './UI/InputField'
import { useNavigate } from 'react-router-dom'
import axios from 'axios'
import toast from 'react-hot-toast'

const valuesTemplate = {username:'', password:'', nombre:'', apellido:'', email:''}


const CreateUser = _ => {
  const navigate = useNavigate()
  const [validated, setValidated] = useState(false)
  const [values, setValue] = useState(valuesTemplate)
  const [showPass, setShowPass] = useState(false)

//   useEffect(_ => {
//     setCurrentValue(colourOptions.find(x => x.value === value?.toLowerCase()))
//   }, [value])

const auxInput = (target, label, error, pass = false) => {
  const extraProps = pass ? {
    labelPosition:'right',
    label: <Button onClick={_=>setShowPass(!showPass)}  >Mostrar Contraseña</Button>,
    type: showPass ? 'text' : 'password' 
  } : 
  {}
  return(
    <div className="field">
      <Input 
        error={(validated && values[target] === '') && error } 
        value={values[target]} 
        onChange={e=>setValue( {...values, [target]: e.target.value } )} 
        className={`add-receta-input ${values[target] !== '' && 'success-input'}`} 
        inputlabel={`${label} Requerido`}
        {...extraProps}
        />
    </div>
  )
}

const checkEmpty = _ =>{
  for(const key in values){
    if(values[key] === '') return true
  }
}

const createUser = _  => {
  setValidated(true)
  if(!checkEmpty()){
    axios.post(`http://localhost:8088/alta`, values)
    .then(({data}) => {
      localStorage.setItem('YUMMIES_TOKKEN', JSON.stringify(data));
      navigate('/')
    })
    .catch(e =>{
      console.log(e)
      toast.error('Ususario o contraseña incorrectos.')
    })
    setValue(valuesTemplate)
    setValidated(false)
  }
}
  
  return (
    <div className='authentication-body'>
      <div className='authentication' style={{height: '100vh'}}>
          <div className='registration-image' />
          <div className='login-body'>
            <div className='login-form'>
                <div className='head-text'>
                <h2>Bienvenido!</h2>
                <p>Bienvenido, por favor introduce tus credenciales.</p>
                </div>
                <div className='login-container'>
                    {auxInput('username', 'Nombre de usuario', 'Ingrese un nombre de usuario')}
                    {auxInput('password', 'Contraseña', 'Ingrese una contraseña', true)}
                    {auxInput('nombre', 'Nombre', 'Ingrese su nombre de pila')}
                    {auxInput('apellido', 'Apellido', 'Ingrese su apellido')}
                    {auxInput('email', 'Correo electronico (Email)', 'Ingrese una cuenta de correo')}
                    <div className='df-jc-ac'>
                      <Button onClick={_=>{navigate('/')}} className='big-button' basic secondary>Inicio</Button>
                      <Button className='big-button' primary onClick={createUser}>Crear</Button>
                    </div>

                    <div className="actions">
                    </div>
                </div>
            </div>
          </div>
      </div>
    </div>
  )
}
export default CreateUser
