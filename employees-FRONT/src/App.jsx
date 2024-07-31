import React from 'react'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import { AddEmployee } from './components/AddEmployee'
import { EditEmployee } from './components/EditEmployee'
import { ListEmployee } from './components/ListEmployee'
import { Navbar } from './components/Navbar'

export default function App() {
  return (
    <>
      <BrowserRouter>
      
          <Navbar />

          <Routes>
            <Route path='/' element={<ListEmployee />} ></Route>
            <Route path='/add' element={<AddEmployee />} ></Route>
            <Route path='/edit/:id' element={<EditEmployee />} ></Route>
          </Routes>
      
      </BrowserRouter>
    </>
  )
}

