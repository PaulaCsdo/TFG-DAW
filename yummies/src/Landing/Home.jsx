import React, {useState, useEffect} from "react"
import Input from '../UI/InputField'
import { Card, Image, Icon, Accordion, Message, Checkbox} from "semantic-ui-react"
import { useNavigate } from 'react-router-dom'
import axios from 'axios'
import toast from 'react-hot-toast'
import imgReceta from '../assets/images/receta.png'

export default function Users () {
  const navigate = useNavigate()
  const [recetasResponse, setRecetas] = useState([])
  const [categoriasResponse, setCategorias] = useState([])
  const [nivelResponse, setNivel] = useState([])
  const [searchText, setSearchText] = useState('')
  const [activeFilter, setActiveFilter] = useState('')
  const [currentFilter, setCurrentFilter] = useState('')
  const [recetasFiltradas, setRecetasFiltradas] = useState([])


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

  useEffect(_=>{
    setRecetasFiltradas(recetasResponse)
  }, [recetasResponse])

  useEffect( _ => {
    getMethod('verRecetas', setRecetas)
    getMethod('verNiveles', setNivel)
    getMethod('verCategorias', setCategorias)
  }, [])

  useEffect(_ => {
    setCurrentFilter('')
    setActiveFilter('')
    let auxList = recetasResponse
    if(searchText !== ""){
      auxList = recetasResponse.filter(x => x.titulo.toLowerCase().indexOf(searchText.toLowerCase()) >=0)
    }
    setRecetasFiltradas(auxList)
  }, [searchText])

  useEffect(_ => {
    let auxList = recetasResponse
    if(currentFilter !== ""){
      auxList = recetasResponse.filter(x =>
        x[activeFilter][activeFilter === 'categoria' ? 'idCategoria' : 'idNivel'] === currentFilter
      )
    }
    setRecetasFiltradas(auxList)
  }, [currentFilter])

  useEffect(_ => {
    setCurrentFilter('')
  }, [activeFilter])
  

  const showRadio = (id, lista, target  )=> {
    return (
      <>
      <div className='radio-container'>
        {lista.length > 0 && lista.map(x => {
          return (
            <Checkbox
              key={`${target}_check_${x.descripcion}`}
              radio
              label={x.descripcion}
              name='checkboxRadioGroup'
              value={x[id]}
              checked={currentFilter === x[id]}
              onChange={(e, data) => setCurrentFilter(data.value)} 
            />
          )
        } )}        
      </div>
      </>
    )
  }

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
    <div className='main-filter-container'>
      <div className='receta-filter-header'>
        <Input inputlabel={`Escribe aqui el nombre de la receta a buscar`} className='search-input' value={searchText} onChange={e=>setSearchText(e.target.value)} icon='search' placeholder='Buscar...'/>
        <Message color='yellow'>Estás en la pagina principal. Aquí puedes buscar una receta por su nombre o filtrarlas por categoria o nivel de dificultad. También puedes hacer click una receta para ver su contenido.</Message>
      </div>
        <Accordion fluid styled>
          <Accordion.Title
            active={activeFilter === 'categoria'}
            index={'categoria'}
            onClick={_=>{if(activeFilter === 'categoria')setActiveFilter(''); else setActiveFilter('categoria')}}
          >
            <Icon name='dropdown' />
            Pulsa aqui para ver las categorias.
          </Accordion.Title>
          <Accordion.Content active={activeFilter === 'categoria'}>
            {showRadio('idCategoria' ,categoriasResponse, 'categoria')}
          </Accordion.Content>
          <Accordion.Title
            active={activeFilter === 'nivelCocina'}
            index={'nivelCocina'}
            onClick={_=>{if(activeFilter === 'nivelCocina')setActiveFilter(''); else setActiveFilter('nivelCocina')}}
          >
            <Icon name='dropdown' />
            Pulsa aqui para ver los distintos niveles de difilcutad.
          </Accordion.Title>
          <Accordion.Content active={activeFilter === 'nivelCocina'}>
            {showRadio('idNivel' ,nivelResponse, 'nivel')}
          </Accordion.Content>
        </Accordion>
    </div>

    
    <Card.Group className={'recetas-container'} itemsPerRow={4}>
      {recetasFiltradas.length > 0 && recetasFiltradas.map(receta => {
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
