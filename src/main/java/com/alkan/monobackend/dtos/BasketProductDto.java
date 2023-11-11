package com.alkan.monobackend.dtos;

import java.io.Serializable;

public class BasketProductDto implements Serializable {

    public int id;
    public int basketId;
    public int productId;
    public int quantity;
    public double amount;

    public BasketProductDto() {
    }

    public BasketProductDto(int id, int basketId, int productId, int quantity, double amount) {
        this.id = id;
        this.basketId = basketId;
        this.productId = productId;
        this.quantity = quantity;
        this.amount = amount;
    }
}
