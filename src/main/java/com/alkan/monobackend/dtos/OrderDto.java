package com.alkan.monobackend.dtos;

import java.io.Serializable;
import java.util.List;

public class OrderDto implements Serializable {

    public int id;
    public boolean isGivenCargo = false;
    public boolean isDelivered = false;
    public int basketId;
    public int shopId;
    public List<OrderItemDto> orderItemDtoList;

    public OrderDto() {
    }

    public OrderDto(int id, boolean isGivenCargo, boolean isDelivered, int basketId, List<OrderItemDto> orderItemDtoList) {
        this.id = id;
        this.isGivenCargo = isGivenCargo;
        this.isDelivered = isDelivered;
        this.basketId = basketId;
        this.orderItemDtoList = orderItemDtoList;
    }
}
