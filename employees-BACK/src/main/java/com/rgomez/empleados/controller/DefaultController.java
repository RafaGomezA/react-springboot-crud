package com.rgomez.empleados.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

abstract class DefaultController {
    protected <T> ResponseEntity<List<T>> makeResponse (List<T> body, HttpStatus status, Class<T> type) {

        return ResponseEntity
                .status(status)
                .body(body);
    }

    protected <T> ResponseEntity<T> makeResponse (T body, HttpStatus status, Class<T> type) {

        return ResponseEntity
                .status(status)
                .body(body);
    }
}
