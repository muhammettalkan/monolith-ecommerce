package com.alkan.monobackend.services.serviceImpl;

import com.alkan.monobackend.repositories.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
}
