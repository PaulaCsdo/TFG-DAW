import React, { useState, useEffect } from 'react'
import { Button } from 'semantic-ui-react'
import Input from './UI/InputField'
import { useNavigate } from 'react-router-dom'
import axios from 'axios'
import toast from 'react-hot-toast'

const Login = _ => {
  const [username, setUsername] = useState('')
  const [password, setPassword] = useState('')
  const [showPass, setShowPass] = useState(false)
  const navigate = useNavigate()

  const loginHandler = _ => {
    axios.post('http://localhost:8088/login', {password, username})
    .then(({data}) => {
      console.log(data)
      localStorage.setItem('YUMMIES_TOKKEN', JSON.stringify(data));
      navigate('/')
    })
    .catch(e =>{
      console.log(e)
      toast.error('Usuario o contraseña incorrectos.')
    })
  }
  return (
    <div className='authentication-body'>
    <div className='authentication' style={{height: '100vh'}}>
        <div className='login-image' />
        <div className='login-body'>
          <div className='login-form'>
              <div className='login-title'>
              Yummies
              </div>
              <div className='head-text'>
              <h2>¡Bienvenido!</h2>
              <p>Introduce tus credenciales.</p>
              </div>
              <div className='login-container'>
                  <div className="field">
                    <Input className={`${username !== '' && 'success-input'}`} value={username} onChange={e=>setUsername(e.target.value)} inputlabel='Nombre de usuario'/>
                  </div>

                  <div className="field">
                    <Input 
                      labelPosition ='right'
                      label={<Button onClick={_=>setShowPass(!showPass)}  >Mostrar Contraseña</Button>}
                      type={showPass ? 'text' : 'password' }
                      className={`${password !== '' && 'success-input'}`}
                      value={password}
                      onChange={e=>setPassword(e.target.value)}
                      inputlabel='Contraseña'/>
                  </div>

                  <div className="actions">
                      <Button className='big-button' primary onClick={loginHandler}>Entrar</Button>
                  </div>
                  <div className="actions">
                      <p>¿No tienes una cuenta? Clicka aquí para ir al formulario de registro.</p>
                      <Button basic className='big-button'  primary onClick={_ =>navigate('/registration')}>Registrate aqui</Button>
                  </div>
              </div>
          </div>
        </div>
    </div>
    </div>
  )
}
export default Login
