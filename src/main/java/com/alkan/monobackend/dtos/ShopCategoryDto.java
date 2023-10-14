package com.alkan.monobackend.dtos;

import java.io.Serializable;
import java.util.List;

public class ShopCategoryDto implements Serializable {

    public int id;
    public int shopId;
    public int categoryId;
    public List<ProductDto> productDtoList;

    public ShopCategoryDto() {
    }

    public ShopCategoryDto(int id, int shopId, int categoryId, List<ProductDto> productDtoList) {
        this.id = id;
        this.shopId = shopId;
        this.categoryId = categoryId;
        this.productDtoList = productDtoList;
    }
}
