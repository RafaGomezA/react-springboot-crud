import { Link } from 'react-router-dom';
import { useEmployee } from '../hooks/useEmployee';
import React from 'react'
import { useEffect } from 'react';

export const ListEmployee = () => {
    const { employees, departments, loading, deleteEmployee, getAllEmployees, getAllDepartments } = useEmployee();

    useEffect( () => {
        getAllEmployees();
        getAllDepartments();
    }, []);

    const getDepartmentName = (departmentId) => {
        if (loading) return "Cargando...";
        const department = departments.find(dep => dep.id === departmentId);
        return department ? department.name : "Desconocido";
    };

    //boton borrar:
    const handleDeleteEmployee = (id) => {
        const confirmDelete = window.confirm("¿Estás seguro de que quieres borrar este empleado?");
        if (confirmDelete) {
            deleteEmployee(id);
        }
    };

    return (
        <>
            <div className="max-w-7xl mx-auto text-center mt-5">
                <h3 className="text-3xl font-semibold text-center text-gray-800 mb-4">Lista de empleados</h3>
            </div>

            <table className="table-auto w-full">
                <thead>
                    <tr className="bg-gray-300">
                        <th className="px-3 py-2 py-2">ID</th>
                        <th className="px-3 py-2">Nombre</th>
                        <th className="px-3 py-2">Primer apellido</th>
                        <th className="px-3 py-2">Segundo apellido</th>
                        <th className="px-3 py-2">DNI</th>
                        <th className="px-3 py-2">Fecha de nacimiento</th>
                        <th className="px-3 py-2">Teléfono</th>
                        <th className="px-3 py-2">email</th>
                        <th className="px-3 py-2">Departamento</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    {employees.map((employee, index) => (
                        <tr key={employee.id} className={index % 2 === 0 ? 'bg-gray-100' : 'bg-white'}>
                            <td className="px-3">{employee.id}</td>
                            <td className="px-3">{employee.name}</td>
                            <td className="px-3">{employee.middleName}</td>
                            <td className="px-3">{employee.lastName}</td>
                            <td className="px-3">{employee.dni}</td>
                            <td className="px-3">{employee.birthdate}</td>
                            <td className="px-3">{employee.phone}</td>
                            <td className="px-3">{employee.email}</td>
                            <td className="px-3">{employee.department && getDepartmentName(employee.department.id)}</td>
                            <td>
                                <div>
                                    <Link to={`/edit/${employee.id}`} className='bg-yellow-400 hover:bg-yellow-500 text-black font-semibold py-2 px-3 rounded mr-2'>Editar</Link>
                                    <button onClick={() => handleDeleteEmployee(employee.id)} className='bg-red-500 hover:bg-red-700 text-white font-semibold py-2 px-3 rounded'>Borrar</button>
                                </div>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </>
    );
}
/*
import axios from 'axios';
import React from 'react'
import { useEffect } from 'react';
import { useState } from 'react'
import { Link } from 'react-router-dom';

export const ListEmployee = () => {

    const urlBaseEmployee = "http://localhost:8080/employee";
    const urlBaseDepartment = "http://localhost:8080/department";

    const [employees, setEmployees] = useState([]);
    const [departments, setDepartments] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect( () => {
        getAllEmployees();
        getAllDepartments();
    }, []);

    //Obtener lista de empleados
    const getAllEmployees = async () => {
        try {
            const response = await axios.get(urlBaseEmployee);
            setEmployees(response.data);
            setLoading(false);
            console.log(response.data); 
        } catch (error) {
            console.error("Error al obtener la lista de empleados:", error);
        }
    }

    //Obtener lista de departamentos
    const getAllDepartments = async () => {
        try {
            const response = await axios.get(urlBaseDepartment);
            setDepartments(response.data);
            console.log(response.data); 
        } catch (error) {
            console.error("Error al obtener la lista de empleados:", error);
        }
    };

    const getDepartmentName = (departmetId) => {
        if(loading) return "Cargando..."
        const department = departments.find( (department) => department.id === departmetId )
        return department ? department.name : "Desconocido";
    };


    const deleteEmployee = async (id) => {

        const confirmDelete = window.confirm("¿Estás seguro de que quieres borrar este empleado?");

        if (confirmDelete) {
            try {
                await axios.delete(`${urlBaseEmployee}/${id}`);
                getAllEmployees();
            } catch (error) {
                console.error("Error al borrar el empleado:", error);
            }
        } else {
            // Si se cancela, no hacer nada
            console.log("Borrado cancelado por el usuario.");
        }
    };

  return (
    <>
        <div className="container text-center mt-5">
        <h3>Lista de empleados</h3>
        </div>
        <hr />
        <table className="table">
            <thead>
                <tr> 
                    <th scope="col">ID</th>
                    <th scope="col">Nombre</th>
                    <th scope="col">Primer apellido</th>
                    <th scope="col">Segundo apellido</th>
                    <th scope="col">DNI</th>
                    <th scope="col">Fecha de nacimiento</th>
                    <th scope="col">Teléfono</th>
                    <th scope="col">email</th>
                    <th scope="col">Departamento</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                  {employees.map( (employee) => (
                    <tr key={employee.id}>
                        <td>{employee.id}</td>
                        <td>{employee.name}</td>
                        <td>{employee.middleName}</td>
                        <td>{employee.lastName}</td>
                        <td>{employee.dni}</td>
                        <td>{employee.birthdate}</td>
                        <td>{employee.phone}</td>
                        <td>{employee.email}</td>
                        <td>{employee.department && getDepartmentName(employee.department.id)}</td>

                        <td>
                             <div>
                                <Link to={`/edit/${employee.id}`} className='btn btn-warning me-2'>Editar</Link>
                                <button onClick={()=> deleteEmployee(employee.id)} className='btn btn-danger'>Borrar</button>
                            </div>
                        </td>
                    </tr>
                ))} 
            </tbody>
        </table>
    </>
  )
}
*/