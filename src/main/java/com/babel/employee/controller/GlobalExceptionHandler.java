package com.babel.employee.controller;

import com.babel.employee.dto.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        String errores = fieldErrors.stream().map(fiel -> fiel.getDefaultMessage()).collect(Collectors.joining(", "));
        return ResponseEntity.badRequest().body(Result.failedResult(errores, HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleValidationErrors(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body(Result.failedResult(
                "It is not a valid value: "+ ex.getMessage(), HttpStatus.METHOD_NOT_ALLOWED.value()));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> handleValidationErrors(HttpRequestMethodNotSupportedException ex) {
        return ResponseEntity.badRequest().body(Result.failedResult(
                ex.getMessage(), HttpStatus.METHOD_NOT_ALLOWED.value()));
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<?> handleValidationErrors(NumberFormatException ex) {
        return ResponseEntity.badRequest().body(Result.failedResult(
                ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<?> handleValidationErrors(NoResourceFoundException ex) {
        return ResponseEntity.badRequest().body(Result.failedResult(
                ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }
}
