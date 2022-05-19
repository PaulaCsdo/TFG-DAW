import React, { useState, useEffect } from 'react'
import { Menu, Segment } from 'semantic-ui-react'
import Routes from '../Routes/AdminRoutes'
import { useNavigate } from 'react-router-dom'
import AppContainer from '../UI/AppContainer'

export default function App () {
  const navigate = useNavigate()
  const [activeItem, setActiveItem] = useState('Usuarios')

  useEffect(_ => {
    navigate('/admin/usuarios')
    setActiveItem('Usuarios')
  }, [])

  const SelectItem = (e, { name })  =>{ setActiveItem(name) }

  return (
    <>
      <Segment className='menuApp' >
        <Menu secondary>
          <Menu.Item>
            <div className='logo'>
              YUMMIES
            </div>
          </Menu.Item>
          <Menu.Item
            name='Usuarios'
            active={activeItem === 'Usuarios'}
            onClick={ (e, { name }) => {setActiveItem(name); navigate('/admin/usuarios')}}
          />
          <Menu.Item
            name='Recetas'
            active={activeItem === 'Recetas'}
            onClick={ (e, { name }) => {setActiveItem(name); navigate('/admin/recetas')}}
          />
          <Menu.Item
            name='Ingredientes'
            active={activeItem === 'Ingredientes'}
            onClick={ (e, { name }) => {setActiveItem(name); navigate('/admin/ingredientes')}}
          />
        </Menu>
      </Segment>
      <AppContainer>
        <Routes />
      </AppContainer>
    </>
  )
}
