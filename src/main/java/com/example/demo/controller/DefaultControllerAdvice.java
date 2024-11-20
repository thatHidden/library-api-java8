package com.example.demo.controller;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.EntityConflictException;
import com.example.demo.exception.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(annotations = Controller.class)
@Slf4j
public class DefaultControllerAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    public String handleEntityNotFoundException(EntityNotFoundException e, Model model) {
        log.error(e.getMessage(), e);
        model.addAttribute("errorMessage", e.getMessage());
        return "error";
    }

    @ExceptionHandler(BadRequestException.class)
    public String handleValidationException(BadRequestException e, Model model) {
        log.error(e.getMessage(), e);
        model.addAttribute("errorMessage", e.getMessage());
        return "error";
    }

    @ExceptionHandler(EntityConflictException.class)
    public String handleEntityConflictException(BadRequestException e, Model model) {
        log.error(e.getMessage(), e);
        model.addAttribute("errorMessage", e.getMessage());
        return "error";
    }

    @ExceptionHandler(Throwable.class)
    public String handleBaseException(Throwable e, Model model) {
        log.error("unexpected error", e);
        model.addAttribute("errorMessage", "unexpected error");
        return "error";
    }
}
