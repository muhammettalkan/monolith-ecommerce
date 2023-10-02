package com.alkan.monobackend.responses;


public class BaseResponse {
    public int code;
    public String message;
    public Object data;
    public BaseResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }
    public BaseResponse(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
