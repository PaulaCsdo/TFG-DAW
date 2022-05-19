import React, {useState, useEffect} from "react"
import Table from '../UI/Table'
import {usersColumns} from './AdminConfiguration/AdminColumns'
import axios from 'axios'
import toast from 'react-hot-toast'

export default function Users () {
  const [usersResponse, setUsers] = useState([])
  const getUsers = _ =>{
    axios.get(`http://localhost:8088/administrador/verUsuarios`)
    .then(({data}) => {
      setUsers(data)
    })
    .catch(e =>{
      console.log(e)
      toast.error('Ususario o contraseña incorrectos.')
    })
  }

  useEffect( _ => {
    getUsers()
  }, [])
  return (
  <>
    {usersResponse.length > 0 && <Table search data={usersResponse} columns={usersColumns()} />}
  </>
  )
}
