package com.rgomez.empleados.service.impl;

import com.rgomez.empleados.errors.exceptions.EntityNotFound;
import com.rgomez.empleados.repository.DepartmentRepository;
import com.rgomez.empleados.repository.EmployeeRepository;
import com.rgomez.empleados.repository.entity.DepartmentEntity;
import com.rgomez.empleados.repository.entity.EmployeeEntity;
import com.rgomez.empleados.service.EmployeeService;
import com.rgomez.empleados.service.GenericService;
import jakarta.persistence.GeneratedValue;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService { //CTRL + I

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public List<EmployeeEntity> getAll() {
        return this.employeeRepository.findAll();
    }

    @Override
    public EmployeeEntity getByID(UUID id) {
        return this.employeeRepository.findById(id)
                .orElseThrow( ()-> new EntityNotFound(EmployeeEntity.class.getName(), id.toString()));
    }

    @Override
    public EmployeeEntity getByDni(String dni) {
        return this.employeeRepository.findByDni(dni)
                .orElseThrow( ()-> new EntityNotFound(EmployeeEntity.class.getName(), dni)); //no hace falta pasarlo a String porque ya lo es
    }

    @Override
    public EmployeeEntity save(EmployeeEntity entity) {
        if(StringUtils.isNotBlank(entity.getDepartment().getName())){
            final DepartmentEntity department = this.departmentRepository
                    .findByName(entity.getDepartment().getName())
                    .orElse(null);

            entity.setDepartment(department);  //busco en la bbdd el departamento por nombre (entrando en EmployeeEntity, que tiene DepartmentEntity que tiene un nombre). Cuando obtengo el departamento por nombre (obtengo el objeto completo, no solo el nombre) entonces lo seteo
        }
        return this.employeeRepository.save(entity); //la entidad del empleado, que ahora tiene el departamento asociado, se guarda en la base de datos utilizando el repositorio de empleados (employeeRepository.save(entity)).
    }

    @Override
    public EmployeeEntity update(UUID id,EmployeeEntity entity) {
        if(this.employeeRepository.existsById(id)){  // se verifica si el empleado con ese id existe en la bbdd
            entity.setId(id);
            return this.save(entity);
        }
        return this.save(entity);

    }

    @Override
    public void delete(UUID id) {
        this.employeeRepository.deleteById(id);
    }

}

/* Aclaración del .save()
El save() para inserción post:
Cuando llamas al método save() con una entidad que no tiene un ID asignado (es decir, es nulo), el método interpreta
esto como una solicitud de creación de un nuevo registro en la base de datos. El método save() creará un nuevo registro
en la base de datos con los datos de la entidad que estás pasando y generará un nuevo ID para este nuevo registro

El save() para actualización put:
Cuando llamas al método save() con una entidad que tiene un ID asignado (es decir, no es nulo), el método interpreta
esto como una solicitud de actualización del registro existente en la base de datos.El método save() intentará actualizar
el registro con el ID proporcionado con los datos de la entidad que estás pasando.
*/
