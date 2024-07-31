package com.rgomez.empleados.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = "com.rgomez.empleados")
@EnableJpaRepositories(basePackages = "com.rgomez.empleados.repository")
//@EnableJpaAuditing(auditorAwareRef = "auditor") //esto se refiere al Auditor de entity
public class config {
}
