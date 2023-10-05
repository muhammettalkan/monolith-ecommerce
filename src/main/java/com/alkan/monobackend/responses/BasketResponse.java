package com.alkan.monobackend.responses;


import com.alkan.monobackend.dtos.BasketDto;
import com.alkan.monobackend.dtos.BasketProductDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class BasketResponse extends BaseResponse{

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public BasketDto basket;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<BasketDto> basketList;
    public BasketResponse(int code, String message, BasketDto basket) {
        super(code, message);
        this.data = basket;
    }

    public BasketResponse(int code, String message, List<BasketDto> basketList) {
        super(code, message);
        this.basketList = basketList;
    }
}
