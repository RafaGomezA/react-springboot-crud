package com.rgomez.empleados.service;

import com.rgomez.empleados.repository.entity.EmployeeEntity;

import java.util.UUID;

public interface EmployeeService extends GenericService <EmployeeEntity, UUID>  {

    EmployeeEntity getByDni (String DNI);
}
