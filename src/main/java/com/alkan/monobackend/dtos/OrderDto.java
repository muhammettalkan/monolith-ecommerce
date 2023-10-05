package com.alkan.monobackend.dtos;

import java.io.Serializable;

public class OrderDto implements Serializable {

    public int id;
    public boolean isGivenCargo = false;
    public boolean isDelivered = false;
    public BasketDto basketDto;

    public OrderDto() {
    }

    public OrderDto(int id, boolean isGivenCargo, boolean isDelivered, BasketDto basketDto) {
        this.id = id;
        this.isGivenCargo = isGivenCargo;
        this.isDelivered = isDelivered;
        this.basketDto = basketDto;
    }
}
