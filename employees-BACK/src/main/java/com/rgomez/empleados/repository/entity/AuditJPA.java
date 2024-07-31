package com.rgomez.empleados.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

//@Getter
//@Setter
//@MappedSuperclass
//@EntityListeners(AuditingEntityListener.class)
//public abstract class AuditJPA {
//
//    @CreatedDate
//    @Column(updatable = false, nullable = false) //cuando se ha creado
//    private LocalDateTime createdAt;
//
//    @LastModifiedDate
//    @Column(nullable = false) //cuando se ha modificado
//    private LocalDateTime modifiedAt;
//
//    @CreatedBy //quien lo ha creado
//    private String createdBy;
//
//    @LastModifiedBy //quien lo ha modificado
//    private String modifiedBy;
//}
