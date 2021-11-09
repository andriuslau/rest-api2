package com.juniorjavadeveloper.restapidemo.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleUserNotFoundException(UserNotFoundException ex) {
        return ResponseEntity.status(NOT_FOUND).body(
                ErrorDetails.builder()
                        .status(NOT_FOUND)
                        .message(ex.getMessage())
                        .timeStamp(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(DataExistException.class)
    @ResponseStatus(code = BAD_REQUEST)
    public final ResponseEntity<ErrorDetails> handleDataExistException(DataExistException ex) {
        return ResponseEntity.status(BAD_REQUEST).body(
                ErrorDetails.builder()
                        .status(BAD_REQUEST)
                        .message(ex.getMessage())
                        .timeStamp(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ErrorDetails> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(BAD_REQUEST).body(
                ErrorDetails.builder()
                        .status(BAD_REQUEST)
                        .message(ex.getFieldErrors().stream().
                                map(DefaultMessageSourceResolvable::getDefaultMessage)
                                .collect(Collectors.joining(" ")))
                        .timeStamp(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public final ResponseEntity<ErrorDetails> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return ResponseEntity.status(BAD_REQUEST).body(
                ErrorDetails.builder()
                        .status(BAD_REQUEST)
                        .message(Objects.requireNonNull(ex.getRootCause()).getMessage())
                        .timeStamp(LocalDateTime.now())
                        .build());
    }
}
