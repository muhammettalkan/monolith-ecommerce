package com.alkan.monobackend.dtos;


import java.io.Serializable;
import java.util.List;

public class ProductDto implements Serializable {

    public int id;
    public String name;
    public double price;
    public int shopCategoryId;
    public List<BasketProductDto> basketProductDtoList;

    public ProductDto() {
    }
    public ProductDto(int id, String name, double price, int shopCategoryId, List<BasketProductDto> basketProductDtoList) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.shopCategoryId = shopCategoryId;
        this.basketProductDtoList = basketProductDtoList;
    }
}
