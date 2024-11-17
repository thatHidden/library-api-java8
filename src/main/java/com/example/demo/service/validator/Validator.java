package com.example.demo.service.validator;

public interface Validator<T> {
    void validate(T entity);
}
