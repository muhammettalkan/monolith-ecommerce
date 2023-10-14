package com.alkan.monobackend.services;

import com.alkan.monobackend.dtos.OrderDto;
import com.alkan.monobackend.entities.Order;

import java.util.List;

public interface OrderService {

    Order findById(int id);
    Order toEntity(OrderDto orderDto);
    OrderDto toDto(Order order);
    List<OrderDto> giveOrder(int basketId);
}
