package com.alkan.monobackend.dtos;

import java.io.Serializable;
import java.util.List;

public class CategoryDto implements Serializable {

    public int id;

    public String name;
    public List<ShopCategoryDto> shopCategoryDtoList;
    public CategoryDto() {
    }

    public CategoryDto(int id, String name, List<ProductDto> productDtoList, List<ShopCategoryDto> shopCategoryDtoList) {
        this.id = id;
        this.name = name;
        this.shopCategoryDtoList = shopCategoryDtoList;
    }

}
