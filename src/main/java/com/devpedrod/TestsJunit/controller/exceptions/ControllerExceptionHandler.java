package com.devpedrod.TestsJunit.controller.exceptions;

import com.devpedrod.TestsJunit.services.exceptions.DataIntegrityException;
import com.devpedrod.TestsJunit.services.exceptions.ObjectNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
        StandardError response = StandardError.builder()
                .timeStamp(LocalDateTime.now())
                .status(NOT_FOUND.value())
                .error(e.getMessage())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(NOT_FOUND).body(response);
    }

    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StandardError> dataIntegrityException(DataIntegrityException e, HttpServletRequest request) {
        StandardError response = StandardError.builder()
                .timeStamp(LocalDateTime.now())
                .status(BAD_REQUEST.value())
                .error(e.getMessage())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(BAD_REQUEST).body(response);
    }
}
