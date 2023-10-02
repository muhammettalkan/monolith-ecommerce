package com.alkan.monobackend.responses;

import com.alkan.monobackend.dtos.CustomerDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class CustomerResponse extends BaseResponse{

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public CustomerDto data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<CustomerDto> dataList;
    public CustomerResponse(int code, String message, CustomerDto customerDto) {
        super(code, message);
        this.data = customerDto;
    }
    public CustomerResponse(int code, String message, List<CustomerDto> customerDtoList) {
        super(code, message);
        this.dataList = customerDtoList;
    }
}
