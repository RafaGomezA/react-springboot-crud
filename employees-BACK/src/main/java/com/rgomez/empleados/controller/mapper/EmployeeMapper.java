package com.rgomez.empleados.controller.mapper;

import com.rgomez.empleados.repository.entity.DepartmentEntity;
import com.rgomez.empleados.repository.entity.EmployeeEntity;
import com.rgomez.employees.model.CreateEmployeeDTO;
import com.rgomez.employees.model.EmployeeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DepartmentMapper.class}) //el uses importa en este mapper el DepartmentMapper
public interface EmployeeMapper {


    EmployeeDTO mapToEmployeeDTO (EmployeeEntity employeeEntity);
    /*
    * En EmployeeEntity tengo "private DepartmentEntity department;", y quiero convertirlo a
    * "private String name;" que esta en DepartmentEntity
    * */

    List<EmployeeDTO> mapToListEmployeeDTO (List <EmployeeEntity> employeeEntities);

    @Mapping(target= "department", ignore = true)
    EmployeeEntity mapToEmployeeEntity (EmployeeDTO employeeDTO);

    List <EmployeeEntity> mapToListEmployeeEntity(List<EmployeeDTO> employeeDTOS);


    EmployeeEntity mapToCreateEmployeeEntity (CreateEmployeeDTO createEmployeeDTO);
    default DepartmentEntity map(String value){
        return DepartmentEntity.builder().name(value).build();
    }
    //crea un departamento (de tipo DepartmentEntity) solo con el nombre, solo me interesa el nombre, y en servicio lo busco y lo seteo
    /*
    es un método predeterminado para mapear el nombre de un departamento a una entidad de departamento (DepartmentEntity).
    un método predeterminado para mapear el nombre de un departamento a una entidad de departamento (DepartmentEntity).
    Esta implementación predeterminada utiliza Lombok's @Builder para construir una nueva instancia de DepartmentEntity con el nombre proporcionado.

    EN el servicio ya puedo buscar por nombre los departamentos usando findByNameOrderByCreatedAt(entity.getDepartment().getName())

    */

 }
