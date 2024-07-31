package com.rgomez.empleados.errors.mappers;


import com.rgomez.empleados.errors.CustomError;
import com.rgomez.employees.model.Error;
import org.mapstruct.Mapper;
import org.springframework.http.HttpStatusCode;

@Mapper(componentModel = "spring")
public interface ErrorResponseMapper {

    Error map (CustomError customError);

    default HttpStatusCode map (Integer value) {

        return HttpStatusCode.valueOf(value);
    }

    default Integer map (HttpStatusCode value) {

        return value.value();
    }

}