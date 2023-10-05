package com.alkan.monobackend.dtos;


import java.io.Serializable;
import java.util.List;

public class BasketDto implements Serializable {

    public int id;
    public int customerId;
    public int quantity;
    public boolean isOrdered = false;
    public List<BasketProductDto> basketProductDtoList;
    public double totalAmount;

    public BasketDto() {
    }

    public BasketDto(int id, int customerId, List<BasketProductDto> basketProductDtoList, double totalAmount, int quantity, boolean isOrdered) {
        this.id = id;
        this.customerId = customerId;
        this.basketProductDtoList = basketProductDtoList;
        this.totalAmount = totalAmount;
        this.quantity = quantity;
        this.isOrdered = isOrdered;
    }
}
