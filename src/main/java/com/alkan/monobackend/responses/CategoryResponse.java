package com.alkan.monobackend.responses;


import com.alkan.monobackend.dtos.CategoryDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class CategoryResponse extends BaseResponse{
    @JsonInclude(JsonInclude.Include.NON_NULL)
    CategoryDto data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<CategoryDto> dataList;


    public CategoryResponse(int code, String message, CategoryDto data) {
        super(code, message);
        this.data = data;
    }
    public CategoryResponse(int code, String message, List<CategoryDto> dataList) {
        super(code, message);
        this.dataList = dataList;
    }
}
