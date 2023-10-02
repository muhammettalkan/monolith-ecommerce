package com.alkan.monobackend.responses;


import com.alkan.monobackend.dtos.ShopDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class ShopResponse extends BaseResponse{
    @JsonInclude(JsonInclude.Include.NON_NULL)
    ShopDto data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<ShopDto> dataList;

    public ShopResponse(int code, String message, ShopDto data) {
        super(code, message);
        this.data = data;
    }
    public ShopResponse(int code, String message, List<ShopDto> dataList) {
        super(code, message);
        this.dataList = dataList;
    }
}
