package com.rgomez.empleados.repository;



import com.rgomez.empleados.repository.entity.DepartmentEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, UUID> {
    Optional<DepartmentEntity> findByName (String name);
}
