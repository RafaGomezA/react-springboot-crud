package com.rgomez.empleados.controller;

import com.rgomez.empleados.controller.mapper.DepartmentMapper;
import com.rgomez.empleados.service.DepartmentService;
import com.rgomez.employees.controller.DepartmentsApi;
import com.rgomez.employees.model.DepartmentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:5173")
public class DepartmentController extends DefaultController implements DepartmentsApi {

    private final DepartmentService departmentService;
    private final DepartmentMapper departmentMapper;
    @Override
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        final var result = this.departmentService.getAll();
        return makeResponse(this.departmentMapper.mapToListDepartmentDTO(result), HttpStatus.OK, DepartmentDTO.class);
    }

    @Override
    public ResponseEntity<DepartmentDTO> getDepartmentByID(UUID id) {
        final var result = this.departmentService.getByID(id);
        return makeResponse(this.departmentMapper.mapToDepartmenteDTO(result),HttpStatus.OK, DepartmentDTO.class);
    }

    @Override
    public ResponseEntity<DepartmentDTO> getDepartmentByName(String name) {
        final var result = this.departmentService.getByName(name);
        return makeResponse(this.departmentMapper.mapToDepartmenteDTO(result),HttpStatus.OK, DepartmentDTO.class);
    }

    @Override
    public ResponseEntity<DepartmentDTO> saveDepartment(DepartmentDTO departmentDTO) throws Exception {
        final var result = this.departmentService.save(this.departmentMapper.mapToDepartmentEntity(departmentDTO));
        return makeResponse(this.departmentMapper.mapToDepartmenteDTO(result),HttpStatus.CREATED,DepartmentDTO.class);
    }

    @Override
    public ResponseEntity<DepartmentDTO> updateDepartmentByID(UUID id, DepartmentDTO departmentDTO) throws Exception {
        final var result = this.departmentService.update(id, this.departmentMapper.mapToDepartmentEntity(departmentDTO));
        return makeResponse(this.departmentMapper.mapToDepartmenteDTO(result),HttpStatus.CREATED,DepartmentDTO.class);
    }

    @Override
    public ResponseEntity<Void> deleteDepartmentById(UUID id) throws Exception {
        this.departmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
