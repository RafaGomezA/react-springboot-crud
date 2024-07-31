package com.rgomez.empleados.service;

import com.rgomez.empleados.repository.entity.DepartmentEntity;
import com.rgomez.empleados.repository.entity.EmployeeEntity;
import com.rgomez.employees.model.DepartmentDTO;
import org.springframework.stereotype.Service;

import java.util.UUID;

public interface DepartmentService extends GenericService<DepartmentEntity, UUID> {
    DepartmentEntity getByName (String name);
}
