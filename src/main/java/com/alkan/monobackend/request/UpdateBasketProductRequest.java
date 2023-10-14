package com.alkan.monobackend.request;

import java.io.Serializable;

public class UpdateBasketProductRequest implements Serializable {

    public int customerId;
    public int basketProductId;
    public int quantity;
    public UpdateBasketProductRequest() {
    }
    public UpdateBasketProductRequest(int customerId, int basketProductId, int quantity) {
        this.customerId = customerId;
        this.basketProductId = basketProductId;
        this.quantity = quantity;
    }
}
