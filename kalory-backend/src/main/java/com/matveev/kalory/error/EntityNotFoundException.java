package com.matveev.kalory.error;

import org.springframework.web.bind.annotation.ResponseStatus;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(BAD_REQUEST)
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Long id) {
        super(format("Entity with ID=%s not found", id));
    }
}
