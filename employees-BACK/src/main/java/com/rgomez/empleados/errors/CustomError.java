package com.rgomez.empleados.errors;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomError {

    private HttpStatusCode status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dateTime;

    private String className;

    private String message;

    @JsonIgnore
    private String exceptionMessage;

    @JsonIgnore
    private Object data;

    public CustomError () {

        dateTime = LocalDateTime.now();
    }

}
