package com.alkan.monobackend.responses;


import com.alkan.monobackend.dtos.BasketDto;
import com.alkan.monobackend.dtos.BasketProductDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class BasketResponse extends BaseResponse{

    @JsonInclude(JsonInclude.Include.NON_NULL)
    BasketDto data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<BasketDto> dataList;
    public BasketResponse(int code, String message, BasketDto data) {
        super(code, message);
        this.data = data;
    }

    public BasketResponse(int code, String message, List<BasketDto> dataList) {
        super(code, message);
        this.dataList = dataList;
    }
}
