package com.alkan.monobackend.responses;

import com.alkan.monobackend.dtos.OrderDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class OrderResponse extends BaseResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public OrderDto data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<OrderDto> dataList;

    public OrderResponse(int code, String message, OrderDto data) {
        super(code, message);
        this.data = data;
    }

    public OrderResponse(int code, String message, List<OrderDto> dataList) {
        super(code, message);
        this.dataList = dataList;
    }
}
