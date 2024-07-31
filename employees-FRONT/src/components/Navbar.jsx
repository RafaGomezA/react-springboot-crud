import React from 'react'
import { Link, NavLink } from 'react-router-dom'

export const Navbar = () => {
    return (
        <nav className="bg-gray-600">
            <div className="max-w-7xl mx-auto flex justify-between items-center h-16 px-4">
                    <NavLink to='/' className="text-white font-bold text-xl">Aplicación de RRHH</NavLink>
                <div className="flex items-center space-x-4">
                    <NavLink exact to='/' className="text-gray-300 hover:text-white px-3 text-lg">Lista</NavLink>
                    <NavLink to='/add' className="text-gray-300 hover:text-white px-3 text-lg">Agregar Empleado</NavLink>
                    <NavLink to='/contact' className="text-gray-300 hover:text-white px-3 text-lg">Contacto</NavLink>
                </div>
            </div>
        </nav>
      )
  }
