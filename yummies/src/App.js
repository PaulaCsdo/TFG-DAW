import React, {useEffect} from 'react';
import logo from './logo.svg';
import './App.css';
import axios from 'axios';

function App() {
  useEffect(_=>{
    axios.get('http://localhost:8088/rest/usuario/verUna/3')
    .then( response =>{
      console.log(response.data)
    })
    axios.post('http://localhost:8088/login', {username:'paula', password: 'hola'})
    .then( response =>{
      console.log(response.data)
    })

  }, [])
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

export default App;
