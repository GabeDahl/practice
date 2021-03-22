package com.infosys.schoolservice.util;

import com.infosys.schoolservice.models.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity notFoundHandler(CustomException ex) {
        return new ResponseEntity(ex.getMessage(), ex.getCode());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity generalHandler(Exception e) {
        return new ResponseEntity("Something went wrong. Please try again.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
