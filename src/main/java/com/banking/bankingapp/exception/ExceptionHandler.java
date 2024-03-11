package com.banking.bankingapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;


@ControllerAdvice
public class ExceptionHandler {


    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<CustomErrorResponse> handleException(AccountNotFoundException exc){

        // create ErrorResponse
        CustomErrorResponse error = new CustomErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());


        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<CustomErrorResponse> handleException(Exception exc){

        // create s StudentErrorResponse
        CustomErrorResponse error = new CustomErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());


        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);

    }

}
