package com.alkan.monobackend.services;

import com.alkan.monobackend.dtos.OrderItemDto;
import com.alkan.monobackend.entities.OrderItem;

public interface OrderItemService {

    OrderItem findOrderItemById(int id);
    OrderItem toEntity(OrderItemDto orderItemDto);
    OrderItemDto toDto(OrderItem orderItem);
    OrderItemDto findById(int id);

    OrderItemDto save(OrderItem orderItem);
}
