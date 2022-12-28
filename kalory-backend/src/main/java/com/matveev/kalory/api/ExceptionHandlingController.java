package com.matveev.kalory.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.ResponseEntity.status;

@Component
public class ExceptionHandlingController {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> error(RuntimeException exception) {
        return status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }
}
