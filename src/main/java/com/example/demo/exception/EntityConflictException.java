package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class EntityConflictException extends RuntimeException {
    public EntityConflictException() {
        super("entity conflict");
    }
}
