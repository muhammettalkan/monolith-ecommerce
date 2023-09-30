package com.alkan.monobackend.dtos;


import jakarta.annotation.Nullable;

import java.io.Serializable;
import java.util.List;

public class BasketDto implements Serializable {

    public int id;
    public int customerId;
    @Nullable
    public int quantity;
    @Nullable
    public List<BasketProductDto> basketProductDtoList;
    @Nullable
    public double totalAmount;

    public BasketDto() {
    }

    public BasketDto(int id, int customerId, List<BasketProductDto> basketProductDtoList, double totalAmount, int quantity) {
        this.id = id;
        this.customerId = customerId;
        this.basketProductDtoList = basketProductDtoList;
        this.totalAmount = totalAmount;
        this.quantity = quantity;
    }
}
