package com.rgomez.empleados.errors;


import com.rgomez.empleados.errors.exceptions.EntityNotFound;
import com.rgomez.empleados.errors.mappers.ErrorResponseMapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice // para capturar excepciones
@RequiredArgsConstructor
@ConditionalOnExpression("${spring.controller-advice.global.enabled}") //con el $ busca en la carpeta resource/config
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomGlobalHandlerException extends ResponseEntityExceptionHandler {

    private static final Logger log = LogManager.getLogger(CustomGlobalHandlerException.class);

    private final ErrorResponseMapper errorResponseMapper;

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<Object> globalError (HttpClientErrorException ex) {

        CustomError customError = new CustomError();
        customError.setMessage(ex.getMessage());
        customError.setExceptionMessage(ex.getLocalizedMessage());
        StackTraceElement element = ex.getStackTrace()[0];
        customError.setClassName(element.getClassName() + ":" + element.getLineNumber());
        customError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        return buildResponseEntity(customError);
    }

    @ExceptionHandler(EntityNotFound.class)
    public ResponseEntity<Object> EmptyResult (EntityNotFound ex) {

        CustomError customError = new CustomError();
        customError.setMessage(ex.getMessage());
        customError.setExceptionMessage(ex.getMessage());
        customError.setStatus(HttpStatus.NOT_FOUND);
        customError.setClassName("");

        return buildResponseEntity(customError);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<Object> integrityConstrainsViolation (SQLIntegrityConstraintViolationException ex) {

        CustomError customError = new CustomError();
        customError.setMessage(String.format("SQL Error: %s", ex.getMessage()));
        customError.setExceptionMessage(ex.getLocalizedMessage());
        StackTraceElement element = ex.getStackTrace()[0];
        customError.setClassName(element.getClassName() + ":" + element.getLineNumber());
        customError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        return buildResponseEntity(customError);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported (HttpMediaTypeNotSupportedException ex,
                                                                      HttpHeaders headers, HttpStatusCode status,
                                                                      WebRequest request) {

        CustomError customError = new CustomError();
        customError.setMessage(String.format("Message: %s - %s", ex.getMessage(), ex.getSupportedMediaTypes()));
        customError.setExceptionMessage(customError.getExceptionMessage());
        StackTraceElement element = ex.getStackTrace()[0];
        customError.setClassName(element.getClassName() + ":" + element.getLineNumber());
        customError.setStatus(status);

        return buildResponseEntity(customError);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported (HttpRequestMethodNotSupportedException ex,
                                                                          HttpHeaders headers, HttpStatusCode status,
                                                                          WebRequest request) {

        CustomError customError = new CustomError();
        customError.setMessage(
                "Method " + ex.getMethod() + " not supported, allow methods: " + ex.getSupportedHttpMethods());
        customError.setExceptionMessage(ex.getMessage());
        customError.setClassName(ex
                .getClass()
                .getName());
        customError.setStatus(status);

        return buildResponseEntity(customError);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable (HttpMessageNotWritableException ex,
                                                                   HttpHeaders headers, HttpStatusCode status,
                                                                   WebRequest request) {

        CustomError customError = new CustomError();
        customError.setMessage(ex.getMessage());
        customError.setExceptionMessage(ex.getLocalizedMessage());
        StackTraceElement element = ex.getStackTrace()[0];
        customError.setClassName(element.getClassName() + ":" + element.getLineNumber());
        customError.setStatus(status);

        return buildResponseEntity(customError);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable (HttpMessageNotReadableException ex,
                                                                   HttpHeaders headers, HttpStatusCode status,
                                                                   WebRequest request) {

        CustomError customError = new CustomError();
        customError.setMessage("JSON parse error, " + request.getContextPath());
        customError.setExceptionMessage(ex.getLocalizedMessage());
        StackTraceElement element = ex.getStackTrace()[0];
        customError.setClassName(element.getClassName() + ":" + element.getLineNumber());
        customError.setStatus(status);

        return buildResponseEntity(customError);
    }

    private ResponseEntity<Object> buildResponseEntity (final CustomError customError) {

        int status = customError
                .getStatus()
                .value();

        if (status >= 500 && status < 600) log.error(customError);
        else if (status >= 400 && status < 500) log.warn(customError);

        return new ResponseEntity<>(this.errorResponseMapper.map(customError), customError.getStatus());
    }
}
