package com.infosys.studentservice.models;


import org.springframework.http.HttpStatus;

public class CustomException extends Exception {
    private HttpStatus code;
    private String message;

    public CustomException(String message, HttpStatus code) {
        this.code = code;
        this.message = message;
    }

    public HttpStatus getCode() {
        return code;
    }

    public void setCode(HttpStatus code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
