package com.alkan.monobackend.exception.custom;

public class ObjectNotFoundException extends RuntimeException{

        public ObjectNotFoundException(String message) {
            super(message);
        }
}
