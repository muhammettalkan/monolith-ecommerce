package com.alkan.monobackend.exception.custom;

public class EmailHasBeenTakenAlreadyException extends RuntimeException{

        public EmailHasBeenTakenAlreadyException(String message) {
            super(message);
        }
}
