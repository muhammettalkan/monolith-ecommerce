package com.alkan.monobackend.exception.handler;

import com.alkan.monobackend.exception.custom.EmailHasBeenTakenAlreadyException;
import com.alkan.monobackend.responses.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;


@ControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<Object> parseNumberResponse(){
        BaseResponse response = new BaseResponse(1002, "Please enter a valid number", null);
        ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.OK);
        return entity;
    }
    @ExceptionHandler(EmailHasBeenTakenAlreadyException.class)
    public ResponseEntity<Object> emailHasBeenTakenResponse(EmailHasBeenTakenAlreadyException exception){
        BaseResponse response = new BaseResponse(1003, exception.getMessage(), null);
        ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.OK);
        return entity;
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> noSuchElementResponse(){
        BaseResponse response = new BaseResponse(1004, "The object you are looking for does not exist", null);
        ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.OK);
        return entity;
    }

}
