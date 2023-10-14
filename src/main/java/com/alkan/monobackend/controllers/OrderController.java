package com.alkan.monobackend.controllers;

import com.alkan.monobackend.responses.OrderResponse;
import com.alkan.monobackend.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/give-order")
    public ResponseEntity<OrderResponse> giveOrder(@RequestParam int basketId){
        return ResponseEntity.ok(new OrderResponse(2000,"Order given successfully",orderService.giveOrder(basketId)));
    }

}
