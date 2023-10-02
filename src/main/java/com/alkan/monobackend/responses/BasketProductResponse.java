package com.alkan.monobackend.responses;

import com.alkan.monobackend.dtos.BasketProductDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class BasketProductResponse extends BaseResponse{

    @JsonInclude(JsonInclude.Include.NON_NULL)
    BasketProductDto data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<BasketProductDto> dataList;
    public BasketProductResponse(int code, String message, BasketProductDto data) {
        super(code, message);
        this.data = data;
    }
    public BasketProductResponse(int code, String message, List<BasketProductDto> dataList) {
        super(code, message);
        this.dataList = dataList;
    }
}
