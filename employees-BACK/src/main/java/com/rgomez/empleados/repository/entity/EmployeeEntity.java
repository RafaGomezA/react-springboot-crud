package com.rgomez.empleados.repository.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.time.LocalDate;

import java.util.UUID;
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class EmployeeEntity {
    @Id
    @JdbcTypeCode(Types.VARCHAR)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 75)
    private String name;

    @Column(length = 75)
    private String middleName;

    @Column(length = 75)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String dni;

    private LocalDate birthdate;

    private String phone;

    @Column(unique = true, nullable = false)
    private String email;

    //@ManyToOne(cascade = CascadeType.ALL)
    @ManyToOne
    @JoinColumn(name = "department")
    private DepartmentEntity department;
}

