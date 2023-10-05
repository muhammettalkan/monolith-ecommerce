package com.alkan.monobackend.exception.custom;

public class ShopNameIsAlreadyInUse extends RuntimeException{

        public ShopNameIsAlreadyInUse(String message) {
            super(message);
        }
}
