package com.alkan.monobackend.responses;


import com.alkan.monobackend.dtos.ProductDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class ProductResponse extends BaseResponse{
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public ProductDto data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<ProductDto> dataList;

    public ProductResponse(int code, String message, ProductDto data) {
        super(code, message);
        this.data = data;
    }
    public ProductResponse(int code, String message, List<ProductDto> dataList) {
        super(code, message);
        this.dataList = dataList;
    }
}
