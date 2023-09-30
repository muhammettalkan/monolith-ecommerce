package com.alkan.monobackend.responses;


import com.alkan.monobackend.dtos.BasketDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BasketResponse extends BaseResponse{
    @Override
    public ResponseEntity<Object> responseBuilder(String message, HttpStatus httpStatus, Object object) {
        return super.responseBuilder(message, httpStatus, object);
    }
}
