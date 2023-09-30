package com.alkan.monobackend.responses;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ShopAdminResponse extends BaseResponse{

    @Override
    public ResponseEntity<Object> responseBuilder(String message, HttpStatus httpStatus, Object object) {
        return super.responseBuilder(message, httpStatus, object);
    }
}
