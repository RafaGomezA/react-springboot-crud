import { useState, useEffect } from 'react';
import axios from 'axios';

export const useEmployee = () => {
    const urlBaseEmployee = "http://localhost:8080/employee";
    const urlBaseDepartment = "http://localhost:8080/department";

    const [employees, setEmployees] = useState([]);
    const [departments, setDepartments] = useState([]);
    const [loading, setLoading] = useState(true);

    const getAllEmployees = async () => {
        try {
            const response = await axios.get(urlBaseEmployee);
            setEmployees(response.data);
            setLoading(false);
        } catch (error) {
            console.error("Error al obtener la lista de empleados:", error);
        }
    }

    const getAllDepartments = async () => {
        try {
            const response = await axios.get(urlBaseDepartment);
            setDepartments(response.data);
            setLoading(false);
        } catch (error) {
            console.error("Error al obtener la lista de departamentos:", error);
        }
    };

    const getEmployeeById = async (id) => {
        try {
            const response = await axios.get(`${urlBaseEmployee}/${id}`);
            return response.data;
        } catch (error) {
            console.error("Error al obtener los datos del empleado:", error);
        }
    }

    const saveEmployee = async (employeeData) => {
        try {
            await axios.post(urlBaseEmployee, employeeData);
            getAllEmployees();
        } catch (error) {
            console.error("Error al agregar el empleado:", error);
        }
    };

    const updateEmployee = async (id, employeeData) => {
        try {
            await axios.put(`${urlBaseEmployee}/${id}`, employeeData);
            getAllEmployees();
        } catch (error) {
            console.error("Error al actualizar el empleado:", error);
        }
    };

    const deleteEmployee = async (id) => {
        try {
            await axios.delete(`${urlBaseEmployee}/${id}`);
            getAllEmployees();
        } catch (error) {
            console.error("Error al borrar el empleado:", error);
        }
    };

    return { employees, 
            departments, 
            loading, 
            getAllEmployees, 
            getAllDepartments, 
            getEmployeeById,
            saveEmployee,  
            updateEmployee, 
            deleteEmployee 
            };
}






