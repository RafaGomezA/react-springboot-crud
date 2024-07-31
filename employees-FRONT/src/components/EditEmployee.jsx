import {useForm} from '../hooks/useForm'
import { Link, useNavigate, useParams } from 'react-router-dom';
import { useEffect } from 'react';
import { useEmployee } from '../hooks/useEmployee';

export const EditEmployee = () => {

    const initialForm = {
        name: '',
        middleName: "",
        lastName: "",
        dni: "",
        birthdate: "",
        phone: "",
        email: "",
        department: ""
    };

    const { formState, onInputChange, setFormState } = useForm(initialForm);
    const {name, middleName, lastName, dni, birthdate, phone, email, department} = formState;
    const { employees, departments, loading, getAllEmployees, getAllDepartments, saveEmployee, getEmployeeById, updateEmployee, deleteEmployee } = useEmployee();
    let navigation = useNavigate();
    const { id } = useParams();


    useEffect(() => {
        getAllDepartments();
        getEmployeeByIdData();
    }, []);


    const getEmployeeByIdData = async () => {
        try {
            const employee = await getEmployeeById(id);
            // Actualizar el estado del formulario con los datos del libro
            setFormState({
                name: employee.name,
                middleName: employee.middleName,
                lastName: employee.lastName,
                dni: employee.dni,
                birthdate: employee.birthdate,
                phone: employee.phone,
                email: employee.email,
                department: employee.department.name
            });

        } catch (error) {
            console.error("Error al obtener los datos del libro:", error);
        }
    }


    const handleSubmit = async (event) => {
        event.preventDefault()
        try {
            await updateEmployee(id, formState);
            navigation('/');
        } catch (error) {
            console.error("Error al actualizar el empleado:", error);
        }
    }

  return (
    <form onSubmit={handleSubmit} className="container mx-auto max-w-lg">
        <h2 className="text-2xl font-semibold my-4">Editar Empleado </h2>
        <div>
            <label htmlFor="name" className="block mt-2 font-bold">Nombre:</label>
            <input 
                type="text" 
                className="w-full px-3 py-2 border rounded-md shadow-sm" 
                name="name" 
                placeholder="Nombre"
                value={name}
                required={true}
                onChange={onInputChange}> 
            </input>
        </div>
        <div>
            <label htmlFor="middleName" className="block mt-2 font-bold">Primer apellido:</label>
            <input 
                type="text" 
                className="w-full px-3 py-2 border rounded-md shadow-sm" 
                name="middleName" 
                placeholder="Primer apellido"
                value={middleName}
                required={true}
                onChange={onInputChange}>
            </input>
        </div>
        <div>
            <label htmlFor="lastName" className="block mt-2 font-bold">Segundo apellido:</label>
            <input 
                type="text" 
                className="w-full px-3 py-2 border rounded-md shadow-sm" 
                name="lastName" 
                placeholder="Segundo apellido"
                value={lastName}
                required={true}
                onChange={onInputChange}>
            </input>
        </div>
        <div>
            <label htmlFor="dni" className="block mt-2 font-bold">DNI:</label>
            <input 
                type="text" 
                className="w-full px-3 py-2 border rounded-md shadow-sm" 
                name="dni" 
                placeholder="12345678A"
                pattern="[0-9]{8}[A-Za-z]"
                title="Por favor, ingrese un DNI válido (8 números seguidos de una letra)"
                value={dni}
                required={true}
                onChange={onInputChange}>
            </input>
        </div>
        <div>
            <label htmlFor="birthdate" className="block mt-2 font-bold">Fecha de Nacimiento (Año-Mes-Día) :</label>
            <input 
                type="text" 
                className="w-full px-3 py-2 border rounded-md shadow-sm" 
                name="birthdate" 
                placeholder="AAAA-MM-DD"
                pattern="\d{4}-\d{2}-\d{2}"
                title="Por favor, ingrese la fecha en el formato AAAA-MM-DD"
                value={birthdate}
                required={true}
                onChange={onInputChange}>
            </input>
        </div>
        <div>
            <label htmlFor="phone" className="block mt-2 font-bold">Teléfono:</label>
            <input 
                type="text" 
                className="w-full px-3 py-2 border rounded-md shadow-sm" 
                name="phone" 
                placeholder="Teléfono"
                value={phone}
                required={true}
                onChange={onInputChange}>
            </input>
        </div>
        <div>
            <label htmlFor="email" className="block mt-2 font-bold">Correo electrónico:</label>
            <input 
                type="email" 
                className="w-full px-3 py-2 border rounded-md shadow-sm" 
                name="email" 
                placeholder="ejemplo@mail.com"
                value={email}
                required={true}
                onChange={onInputChange}>
            </input>
        </div>
        <div>
            <label htmlFor="department" className="block mt-2 font-bold">Departamento:</label>
            <select
                className="w-full px-3 py-2 border rounded-md shadow-sm"
                name="department"
                value={department}
                required={true}
                onChange={onInputChange}>
                    
                <option value="">- Seleccionar departamento -</option>
                {loading ? (<option disabled>Cargando departamentos...</option>) 
                         : (departments.map(dep => (
                                <option key={dep.id} value={dep.name}>{dep.name}</option>
                            ))
                )}
            </select>
        </div>

        <div className='mt-3'>
            <button type="submit" className="bg-yellow-400 hover:bg-yellow-500 text-black font-semibold py-2 px-4 rounded-md mr-3">Editar</button>
            <Link to="/" className="bg-blue-500 hover:bg-blue-600 text-white font-semibold py-2 px-4 rounded-md inline-block">Volver</Link>
        </div>
    </form>
    )
}


/*
import {useForm} from '../hooks/useForm'
import { Link, useNavigate, useParams } from 'react-router-dom';
import axios from "axios"
import { useState } from 'react';
import { useEffect } from 'react';

export const EditEmployee = () => {

    const urlBaseEmployee = "http://localhost:8080/employee";
    const urlBaseDepartment = "http://localhost:8080/department";
    let navigation = useNavigate();
    const { id } = useParams();

    const initialForm = {
        name: '',
        middleName: "",
        lastName: "",
        dni: "",
        birthdate: "",
        phone: "",
        email: "",
        department: ""
    };

    const { formState, onInputChange, setFormState } = useForm(initialForm);

    const {name, middleName, lastName, dni, birthdate, phone, email, department} = formState

    const [departments, setDepartments] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        getAllDepartments();
        getEmployeeById();
    }, []);


    const getEmployeeById = async () => {
        try {
            const response = await axios.get(`${urlBaseEmployee}/${id}`);
            const employee = response.data;

            // Actualizar el estado del formulario con los datos del libro
            setFormState({
                name: employee.name,
                middleName: employee.middleName,
                lastName: employee.lastName,
                dni: employee.dni,
                birthdate: employee.birthdate,
                phone: employee.phone,
                email: employee.email,
                department: employee.department.name
            });

        } catch (error) {
            console.error("Error al obtener los datos del libro:", error);
        }
    }

    const getAllDepartments = async () => {
        try {
            const result = await axios.get(urlBaseDepartment);
            setDepartments(result.data);
            setLoading(false);
        } catch (error) {
            console.error("Error al obtener la lista de departamentos:", error);
        }
    }

    const handleSubmit = async (event) => {
        event.preventDefault()
        try {
            await axios.put(`${urlBaseEmployee}/${id}`,formState);
            navigation('/'); 

        } catch (error) {
            console.error("Error al actualizar el libro:", error);
        } 
    }

  return (
    <form onSubmit={handleSubmit} className="container">
    <h2>Editar Empleado </h2>
    <div className="form-group">
        <label htmlFor="name">Nombre</label>
        <input 
            type="text" 
            className="form-control" 
            name="name" 
            placeholder="Nombre"
            value={name}
            required={true}
            onChange={onInputChange}> 
        </input>
    </div>
    <div className="form-group">
        <label htmlFor="middleName">Primer apellido</label>
        <input 
            type="text" 
            className="form-control" 
            name="middleName" 
            placeholder="Primer apellido"
            value={middleName}
            required={true}
            onChange={onInputChange}>
        </input>
    </div>
    <div className="form-group">
        <label htmlFor="lastName">Segundo apellido</label>
        <input 
            type="text" 
            className="form-control" 
            name="lastName" 
            placeholder="Segundo apellido"
            value={lastName}
            required={true}
            onChange={onInputChange}>
        </input>
    </div>
    <div className="form-group">
        <label htmlFor="dni">DNI</label>
        <input 
            type="text" 
            className="form-control" 
            name="dni" 
            placeholder="12345678A"
            pattern="[0-9]{8}[A-Za-z]"
            title="Por favor, ingrese un DNI válido (8 números seguidos de una letra)"
            value={dni}
            required={true}
            onChange={onInputChange}>
        </input>
    </div>
    <div className="form-group">
        <label htmlFor="birthdate">Fecha de Nacimiento (AAAA-MM-DD) </label>
        <input 
            type="text" 
            className="form-control" 
            name="birthdate" 
            placeholder="AAAA-MM-DD"
            pattern="\d{4}-\d{2}-\d{2}"
            title="Por favor, ingrese la fecha en el formato AAAA-MM-DD"
            value={birthdate}
            required={true}
            onChange={onInputChange}>
        </input>
    </div>
    <div className="form-group">
        <label htmlFor="phone">Teléfono</label>
        <input 
            type="text" 
            className="form-control" 
            name="phone" 
            placeholder="Teléfono"
            value={phone}
            required={true}
            onChange={onInputChange}>
        </input>
    </div>
    <div className="form-group">
        <label htmlFor="email">Correo electrónico</label>
        <input 
            type="email" 
            className="form-control" 
            name="email" 
            placeholder="ejemplo@mail.com"
            value={email}
            required={true}
            onChange={onInputChange}>
        </input>
    </div>
    <div className="form-group">
            <label htmlFor="department">Departamento</label>
            <select
                className="form-control"
                name="department"
                value={department}
                required={true}
                onChange={onInputChange}>
                <option value="">- Seleccionar departamento -</option>
                {loading ? (
                            <option disabled>Cargando departamentos...</option>
                        ) : (
                            departments.map(dep => (
                                <option key={dep.id} value={dep.name}>{dep.name}</option>
                            ))
                )}
            </select>
        </div>

    <div className='mt-3'>
        <button type="submit" className="btn btn-warning me-3">Editar</button>
        <Link to="/" className="btn btn-primary">Volver</Link>
    </div>
</form>
)
}
*/