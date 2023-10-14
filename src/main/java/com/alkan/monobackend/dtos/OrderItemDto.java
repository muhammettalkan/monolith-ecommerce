package com.alkan.monobackend.dtos;

import java.io.Serializable;

public class OrderItemDto implements Serializable {

    public int id;
    public int basketProductId;
    public boolean isGivenCargo = false;
    public int shopId;
    public int orderId;

    public OrderItemDto() {
    }

    public OrderItemDto(int id, int basketProductId, boolean isGivenCargo, int shopId, int orderId) {
        this.id = id;
        this.basketProductId = basketProductId;
        this.isGivenCargo = isGivenCargo;
        this.shopId = shopId;
        this.orderId = orderId;
    }
}
