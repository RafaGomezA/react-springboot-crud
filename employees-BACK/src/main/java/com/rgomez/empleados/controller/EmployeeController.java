package com.rgomez.empleados.controller;

import com.rgomez.empleados.controller.mapper.EmployeeMapper;
import com.rgomez.empleados.service.EmployeeService;
import com.rgomez.employees.controller.EmployeesApi;
import com.rgomez.employees.model.CreateEmployeeDTO;
import com.rgomez.employees.model.EmployeeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor // con esto no hace falta el @Autowired
@CrossOrigin(value = "http://localhost:5173")
public class EmployeeController extends DefaultController implements EmployeesApi {

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    @Override
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){
        final var result = this.employeeService.getAll();   //recibo lista de employeeEntity  y abajo la mapeo a ListEmployeeDTO
        return makeResponse(this.employeeMapper.mapToListEmployeeDTO(result), HttpStatus.OK, EmployeeDTO.class);// este clas es el tipo que devuelve
    }

    @Override
    public ResponseEntity<EmployeeDTO> getEmployeeByID(UUID id) {

        final var result =this.employeeService.getByID(id); //recibo un employeEntity y mapeo a EmployeeDTO
        return makeResponse(this.employeeMapper.mapToEmployeeDTO(result), HttpStatus.OK, EmployeeDTO.class);
    }

    @Override
    public ResponseEntity<EmployeeDTO> getEmployeeByDni(String dni) {
        final var result = this.employeeService.getByDni(dni);
        return makeResponse(this.employeeMapper.mapToEmployeeDTO(result), HttpStatus.OK, EmployeeDTO.class);
    }

    @Override
    public ResponseEntity<EmployeeDTO> saveEmployees(CreateEmployeeDTO createEmployeeDTO) {
        final var result = this.employeeService.save(this.employeeMapper.mapToCreateEmployeeEntity(createEmployeeDTO)); //yo guardo un employeeDTO y lo mapeo a Entity
        return makeResponse(this.employeeMapper.mapToEmployeeDTO(result), HttpStatus.CREATED, EmployeeDTO.class); // retorno justo lo contrario, me viene un Entity y lo mapeo a DTO
    }

    @Override
    public ResponseEntity<EmployeeDTO> updateEmployeeByID(UUID id, CreateEmployeeDTO createEmployeeDTO) {
        final var result = this.employeeService.update(id, this.employeeMapper.mapToCreateEmployeeEntity(createEmployeeDTO)); //yo guardo un employeeDTO y lo mapeo a Entity
        return makeResponse(this.employeeMapper.mapToEmployeeDTO(result), HttpStatus.CREATED, EmployeeDTO.class); // retorno justo lo contrario, me viene un Entity y lo mapeo a DTO
    }

    @Override
    public ResponseEntity<Void> deleteEmployeeById(UUID id) {

        this.employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
