package com.icecreamshop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.icecreamshop.exception.ConflictException;
import com.icecreamshop.exception.IncorrectCredentialsException;
import com.icecreamshop.exception.NotFoundException;
import com.icecreamshop.exception.UnauthorizedException;

import javax.validation.ConstraintViolationException;
import javax.validation.Path.Node;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerController {
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<List<String>> handleHttpMessageNotReadableException() {
        List<String> errors = List.of("The request body is not valid.");
        log.info("[ExceptionHandlerController] HTTP message not readable: {}", errors);
        return ResponseEntity.badRequest().body(errors);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<String> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> String.format("The %s: '%s' %s.", fieldError.getField(),
                        fieldError.getRejectedValue(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        log.info("[ExceptionHandlerController] method argument not valid: {}", errors);
        return ResponseEntity.badRequest().body(errors);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<List<String>> handleConstraintViolationException(ConstraintViolationException exception) {
        List<String> errors = new ArrayList<>();
        exception.getConstraintViolations()
                .forEach(constraintViolation -> {
                    String field = null;
                    for (Node node : constraintViolation.getPropertyPath()) {
                        field = node.getName();
                    }
                    errors.add(String.format("The %s: '%s' %s.", field, constraintViolation.getInvalidValue(),
                            constraintViolation.getMessage()));
                });
        log.info("[ExceptionHandlerController] constraint violations: {}", errors);
        return ResponseEntity.badRequest().body(errors);
    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = IncorrectCredentialsException.class)
    public ResponseEntity<List<String>> handleIncorrectCredentials(IncorrectCredentialsException exception) {
        List<String> errors = List.of(exception.getMessage());
        log.info("[ExceptionHandlerController] incorrect credentials: {}", errors);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errors);
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(value = ConflictException.class)
    public ResponseEntity<List<String>> handleConflictException(ConflictException exception) {
        List<String> errors = List.of(exception.getMessage());
        log.info("[ExceptionHandlerController] conflict: {}", errors);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errors);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<List<String>> handleNotFoundException(NotFoundException exception) {
        List<String> errors = List.of(exception.getMessage());
        log.info("[ExceptionHandlerController] not found: {}", errors);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = UnauthorizedException.class)
    public ResponseEntity<List<String>> handleUnauthorizedException(UnauthorizedException exception) {
        List<String> errors = List.of(exception.getMessage());
        log.info("[ExceptionHandlerController] unauthorized operation: {}", errors);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errors);
    }
}
