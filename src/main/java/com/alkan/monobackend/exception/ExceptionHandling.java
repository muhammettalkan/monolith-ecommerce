package com.alkan.monobackend.exception;

import com.alkan.monobackend.exception.custom.EmailHasBeenTakenAlreadyException;
import com.alkan.monobackend.exception.custom.ObjectNotFoundException;
import com.alkan.monobackend.exception.custom.ShopNameIsAlreadyInUse;
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
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<Object> objectNotFoundResponse(ObjectNotFoundException exception){
        BaseResponse response = new BaseResponse(1004, exception.getMessage(), null);
        ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.OK);
        return entity;
    }
    @ExceptionHandler(ShopNameIsAlreadyInUse.class)
    public ResponseEntity<Object> shopNameIsAlreadyInUseResponse(ShopNameIsAlreadyInUse exception){
        BaseResponse response = new BaseResponse(1005, exception.getMessage(), null);
        ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.OK);
        return entity;
    }


}
