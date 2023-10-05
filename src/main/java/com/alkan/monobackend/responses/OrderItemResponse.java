package com.alkan.monobackend.responses;

import com.alkan.monobackend.dtos.OrderItemDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class OrderItemResponse extends BaseResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public OrderItemDto data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<OrderItemDto> dataList;
    public OrderItemResponse(int code, String message, OrderItemDto data) {
        super(code, message);
        this.data = data;
    }
    public OrderItemResponse(int code, String message, List<OrderItemDto> dataList) {
        super(code, message);
        this.dataList = dataList;
    }
}
