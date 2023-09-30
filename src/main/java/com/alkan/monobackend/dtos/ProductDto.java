package com.alkan.monobackend.dtos;

import java.io.Serializable;

public class ProductDto implements Serializable {

    public int id;
    public String name;
    public double price;
    public int categoryId;

    public ProductDto() {
    }
    public ProductDto(int id, String name, double price, int categoryId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
    }
}
