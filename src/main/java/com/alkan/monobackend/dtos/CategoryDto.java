package com.alkan.monobackend.dtos;

import java.io.Serializable;
import java.util.List;

public class CategoryDto implements Serializable {

    public int id;

    public String name;
    public List<ProductDto> productDtoList;
    public CategoryDto() {
    }

    public CategoryDto(int id, String categoryDtoName, List<ProductDto> productDtos) {
        this.id = id;
        this.name = name;
        this.productDtoList = productDtos;
    }

}
