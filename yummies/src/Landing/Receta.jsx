import React, {useState} from "react"
import { Tab, Segment, Button, Icon, Message} from "semantic-ui-react"
import { useLocation } from 'react-router-dom'
import image from '../assets/images/receta.png'
import { useNavigate } from 'react-router-dom'
import axios from 'axios'
import toast from 'react-hot-toast'

export default function Users () {
  const navigate = useNavigate()
  const [searchText, setSearchText] = useState('')
  const { state: receta  } = useLocation()

  const guardarReceta = _ =>{
    
		const currentUser = JSON.parse(localStorage.getItem('YUMMIES_TOKKEN'))
    axios.get(`http://localhost:8088/rest/usuario/guardarReceta/`, {params:{username: currentUser.username, idReceta: receta.idReceta}})
    .then(_=>{
      toast.success('Receta guardada en favoritos.')
    })
    .catch(_=>{
      toast.error('Error al guardar receta.')
    })
  }

  const panes = [
    {
      menuItem: 'Informacion',
      render: () => <Tab.Pane attached={false}>
        <div>
            <div className="ingrediente-container">
              <Segment>
                <div className='ingrediente-segment'>
                    <div>Momento</div>
                    <div className='cantidad'>{receta.momento}</div>
                </div>
              </Segment>
            </div>
            <div className="ingrediente-container">
              <Segment>
                <div className='ingrediente-segment'>
                    <div>Ingredientes</div>
                    <div className='cantidad'>{receta.ingredienteEnRecetas.length}</div>
                </div>
              </Segment>
            </div>
            <div className="ingrediente-container">
              <Segment>
                <div className='ingrediente-segment'>
                    <div>Kcal</div>
                    <div className='cantidad'>{receta.kcal} kcal</div>
                </div>
              </Segment>
            </div>
        </div>
      </Tab.Pane>,
    },
    {
      menuItem: 'Ingredientes',
      render: () => <Tab.Pane attached={false}>
        <div>
          {receta.ingredienteEnRecetas.map(x=>{return(
            <div key={`ingrediente_receta_${x.idIngredientereceta}`} className="ingrediente-container">
                <Segment>
                <div className='ingrediente-segment'>
                    <div>{x?.ingrediente?.descripcion}</div>
                    <div className='cantidad'>{`${x.cantidad} ${x.unidad}`}</div>
                </div>
                </Segment>
            </div>
            )})}
        </div>
      </Tab.Pane>,
    },
    {
      menuItem: 'Pasos',
      render: () => <Tab.Pane attached={false}>{receta.pasos?.split('- ').map(x=><p key={Math.random()}>{x}</p>)}</Tab.Pane>,
    },
  ]
  
  return (
  <div className='ver-receta-container'>
    <Message color='yellow'>Esta es la informacion de la receta que has seleccionado, puedes guardarla en favoritos o volver a la lista de recetas.</Message>
    <div className="receata-header">
      <Button animated basic className='med-button'  primary onClick={_ =>navigate(-1)}>
        <Button.Content visible>Volver a las Recetas</Button.Content>
        <Button.Content hidden>
          <Icon name='arrow left' />
        </Button.Content>
      </Button>
      {/* <Button basic className='med-button'  primary onClick={_ =>navigate('/recetas')}>Volver a las Recetas</Button> */}
      <img alt="" className="" src={image} />
      <Button animated='vertical' className='med-button'  primary onClick={guardarReceta}>
        <Button.Content visible>Guardar como Favorita</Button.Content>
        <Button.Content hidden>
          <Icon name='heart outline' />
        </Button.Content>
      </Button>
    </div>
    <div className="receta-nombre">
        {receta.titulo}
    </div>
    <Tab className='menu' menu={{ secondary: true, pointing: true }} panes={panes} />
  </div>
  )
}
