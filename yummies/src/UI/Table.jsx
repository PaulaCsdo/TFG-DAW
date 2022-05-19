import React, { useEffect, useState } from 'react'
import { Table, Menu, Icon } from 'semantic-ui-react'
import Input from './InputField'

const ROWSPERPAGE = 5

export default function CustomTable ({columns, data, search = false, children}) {

  const [currentData, setCurrentData] = useState([])
  const [filterData, setFilterData] = useState([])
  const [currrentPage, setCurrrentPage] = useState(0)
  const [searchText, setSearchText] = useState('')

  useEffect(_ => {
    let auxList = data
    if(searchText !== ""){
      auxList = data.filter(x => x[columns[0].key].toLowerCase().indexOf(searchText.toLowerCase()) >=0)
    }
    setFilterData(auxList)
    setCurrentData(auxList.slice(currrentPage*ROWSPERPAGE, (currrentPage*ROWSPERPAGE)+ROWSPERPAGE))
  }, [searchText])
  

  const createRow = (row, index )=> {
    return (
      <Table.Row key={`${index}_row`}>
        {columns.map(col =>{
          return(
            <Table.Cell key={`${index}_${col.key}`} >{row[col.key]}</Table.Cell>
          )
        })}
      </Table.Row>
    )
  }

  const pagination = _ => {
    const pages = []
    const numberOfPages = Math.ceil(filterData.length/ROWSPERPAGE)
    for(let i = 0; i<numberOfPages; i++){
      pages.push(
        <Menu.Item 
          key={`${i}_pagination`} 
          active={currrentPage === i}
          onClick={_=>{pageChangeHandler(i)}}>
            {i+1}
        </Menu.Item>
        )
    }
    return(
      <Table.Footer>
        <Table.Row>
          <Table.HeaderCell colSpan={columns.length}>
            <Menu floated='right' pagination>
              <Menu.Item
                disabled = {currrentPage === 0}
                onClick={_=>{pageChangeHandler(currrentPage-1)}} 
                icon>
                <Icon name='chevron left' />
              </Menu.Item>
              {pages}
              <Menu.Item
                disabled = {currrentPage === numberOfPages-1}
                onClick={_=>{pageChangeHandler(currrentPage+1)}} 
                icon>
                <Icon name='chevron right' />
              </Menu.Item>
            </Menu>
          </Table.HeaderCell>
        </Table.Row>
      </Table.Footer>
    )
  }

  const pageChangeHandler = page => {
    setCurrentData(filterData.slice(page*ROWSPERPAGE, (page*ROWSPERPAGE)+ROWSPERPAGE))
    setCurrrentPage(page)
  }

  const searchHandler = e => {
    console.log(e.target.value)
    setSearchText(e.target.value)
    setCurrrentPage(0)
  }

  const searchInput = _ => {
    return (
      <div className='search-input-container'>
        <Input className='search-input' value={searchText} onChange={searchHandler} icon='search' placeholder='Buscar...'/>
        {children}
      </div>
    )
  }

  return (
  <>
    {search && searchInput()}
    <Table basic='very'>
    <Table.Header>
      <Table.Row>
        {columns.map(col =>{
          return(
            <Table.HeaderCell key={col.key} >{col.name}</Table.HeaderCell>
          )
        })}
      </Table.Row>
    </Table.Header>
    <Table.Body>
      {currentData.map((row, index )=> createRow(row, index))}
    </Table.Body>
    {filterData.length > ROWSPERPAGE && pagination() }
    </Table>
  </>
  )
}
