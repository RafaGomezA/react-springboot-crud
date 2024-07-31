package com.rgomez.empleados.controller.mapper;


import com.rgomez.empleados.repository.entity.DepartmentEntity;
import com.rgomez.employees.model.DepartmentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    @Mapping(source = "id", target = "id")
    DepartmentDTO mapToDepartmenteDTO (DepartmentEntity departmentEntity);

    List<DepartmentDTO> mapToListDepartmentDTO (List <DepartmentEntity> departmentEntities);

    DepartmentEntity mapToDepartmentEntity (DepartmentDTO departmentDTO);

    List <DepartmentEntity> mapToListDepartmentEntity(List<DepartmentDTO> departmentDTOS);
}
