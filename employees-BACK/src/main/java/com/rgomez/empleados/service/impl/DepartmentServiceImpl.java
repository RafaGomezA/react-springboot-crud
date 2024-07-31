package com.rgomez.empleados.service.impl;

import com.rgomez.empleados.errors.exceptions.EntityNotFound;
import com.rgomez.empleados.repository.DepartmentRepository;
import com.rgomez.empleados.repository.entity.DepartmentEntity;
import com.rgomez.empleados.service.DepartmentService;
import com.rgomez.empleados.service.GenericService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public List<DepartmentEntity> getAll() {
        return this.departmentRepository.findAll();
    }

    @Override
    public DepartmentEntity getByID(UUID id) {
        return this.departmentRepository.findById(id)
                .orElseThrow( ()-> new EntityNotFound(DepartmentRepository.class.getName(), id.toString()));
    }

    @Override
    public DepartmentEntity getByName(String name) {
        return this.departmentRepository.findByName(name)
                .orElseThrow( ()-> new EntityNotFound(DepartmentRepository.class.getName(), name));
    }

    @Override
    public DepartmentEntity save(DepartmentEntity entity) {
        return this.departmentRepository.save(entity);
    }

    @Override
    public DepartmentEntity update(UUID id, DepartmentEntity entity) {
        if(this.departmentRepository.existsById(id)){
            entity.setId(id);
            return this.departmentRepository.save(entity);
        }
        return this.departmentRepository.save(entity);
    }

    @Override
    public void delete(UUID id) {
        this.departmentRepository.deleteById(id);
    }


}
