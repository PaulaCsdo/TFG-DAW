import React, {useState, useEffect} from "react"
import { Card, Image, Icon, Message} from "semantic-ui-react"
import { useNavigate } from 'react-router-dom'
import axios from 'axios'
import toast from 'react-hot-toast'
import imgReceta from '../assets/images/receta.png'

export default function RecetasGuardadas () {
  const navigate = useNavigate()
  const [recetasResponse, setRecetas] = useState([])

  const getRecetas = _ =>{
    const currentUser = JSON.parse(localStorage.getItem('YUMMIES_TOKKEN'))
    axios.get(`http://localhost:8088/rest/usuario/verMisRecetas`, {params:{username: currentUser.username}})
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

  const getImg = id => {
    try{
      const auxImg = require(`../assets/images/${id}.jpg`)
      return auxImg
    }
    catch (e){
      return imgReceta
    }
  }

  return (
  <>
    <Message color='yellow'>En esta seccion podra ver sus recetas creadas</Message>
    <Card.Group className={'recetas-container'} itemsPerRow={4}>
      {recetasResponse.length > 0 && recetasResponse.map(receta => {
        return(
          <Card key={`receta_${receta.idReceta}`} onClick={_=>{navigate(`/recetas/${receta.idReceta}`, { state: { ...receta } })}} >
            <Image src={getImg(receta.idReceta)} wrapped ui={false} />
            <Card.Content>
              <Card.Header>{receta.titulo}</Card.Header>
              <Card.Description>
              {receta.pasos}
              </Card.Description>
            </Card.Content>
            <Card.Content extra>
              <Icon name='clock outline' />
              {receta.momento}
            </Card.Content>
          </Card>
        )
      })}
    </Card.Group>
  </>
  )
}
