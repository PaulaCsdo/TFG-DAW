import React, { useState, useEffect } from 'react'
import { Menu, Segment } from 'semantic-ui-react'
import Routes from '../Routes/Routes'
import AdminRoutes from '../Routes/AdminRoutes'
import { useNavigate } from 'react-router-dom'
import AppContainer from '../UI/AppContainer'

export default function App () {
  const navigate = useNavigate()
  const [activeItem, setActiveItem] = useState('Usuarios')
  const [showAdmin, setShowAdmin] = useState(false)
  const [firstLoad, setFirstLoad] = useState(true)

  const admin = _ => {
		const currentUser = JSON.parse(localStorage.getItem('YUMMIES_TOKKEN'))
		return currentUser.perfiles[0].idPerfil === 1
	}

	const userLogged = _ => {
		return localStorage.getItem('YUMMIES_TOKKEN') !== undefined && localStorage.getItem('YUMMIES_TOKKEN') !== ''
	}

  useEffect(_ => {
    if(!userLogged()){
      navigate('/login')
    }
    else{
      if(firstLoad){
        setShowAdmin(admin())
        admin() ? setActiveItem('Usuarios') : setActiveItem('Recetas')
        setFirstLoad(false)
      }
    }
  },[])

  const userMenu = _ => {
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
              name='Recetas'
              active={activeItem === 'Recetas'}
              onClick={ (e, { name }) => {setActiveItem(name); navigate('/recetas')}}
            />
            <Menu.Item
              name='Crear receta'
              active={activeItem === 'Crear receta'}
              onClick={ (e, { name }) => {setActiveItem(name); navigate('/addReceta')}}
            />
            <Menu.Item
              name='Recetas guardadas'
              active={activeItem === 'Recetas guardadas'}
              onClick={ (e, { name }) => {setActiveItem(name); navigate('/recetasGuardadas')}}
            />
            <Menu.Item
              name='Mis recetas'
              active={activeItem === 'Mis recetas'}
              onClick={ (e, { name }) => {setActiveItem(name); navigate('/misRecetas')}}
            />
            <Menu.Menu position='right'>
              <Menu.Item
                name='Cerrar sesion'
                onClick={ (e, { name }) => {localStorage.setItem('YUMMIES_TOKKEN', ''); setActiveItem(name); navigate('/login')}}
              />
            </Menu.Menu>
          </Menu>
        </Segment>
        <AppContainer>
          <Routes />
        </AppContainer>
      </>
    )
  }

  const adminMenu = _ => {
    return(

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
        <Menu.Menu position='right'>
          <Menu.Item
            name='Cerrar sesion'
            onClick={ (e, { name }) => {localStorage.setItem('YUMMIES_TOKKEN', ''); setActiveItem(name); navigate('/login')}}
          />
        </Menu.Menu>
      </Menu>
    </Segment>
    <AppContainer>
      <AdminRoutes />
    </AppContainer>
  </>
    )
  }
  if(!userLogged()) return null
  return (
    <div>
    {
      showAdmin ? 
          adminMenu():
          userMenu()
     }
    </div>
  )
}
