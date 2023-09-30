package com.alkan.monobackend.request;

import java.io.Serializable;

public class AddBasketProductToBasketRequest implements Serializable {

    public int basketId;
    public int productId;
    public int quantity;

    public AddBasketProductToBasketRequest() {
    }

    public AddBasketProductToBasketRequest(int basketId, int productId, int quantity) {
        this.basketId = basketId;
        this.productId = productId;
        this.quantity = quantity;
    }
}
