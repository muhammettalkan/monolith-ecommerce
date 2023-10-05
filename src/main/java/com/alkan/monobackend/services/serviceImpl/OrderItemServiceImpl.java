package com.alkan.monobackend.services.serviceImpl;

import com.alkan.monobackend.dtos.OrderItemDto;
import com.alkan.monobackend.entities.OrderItem;
import com.alkan.monobackend.exception.custom.ObjectNotFoundException;
import com.alkan.monobackend.repositories.OrderItemRepository;
import com.alkan.monobackend.services.BasketProductService;
import com.alkan.monobackend.services.OrderItemService;
import com.alkan.monobackend.services.ShopService;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository repository;
    private final BasketProductService basketProductService;
    private final ShopService shopService;
    public OrderItemServiceImpl(OrderItemRepository repository, BasketProductService basketProductService, ShopService shopService) {
        this.repository = repository;
        this.basketProductService = basketProductService;
        this.shopService = shopService;
    }

    public OrderItem findById(int id){
        return repository.findById(id).orElseThrow(()-> new ObjectNotFoundException("OrderItem not found with id: " + id));
    }
    public OrderItem toEntity(OrderItemDto orderItemDto){
        OrderItem orderItem = new OrderItem();
        orderItem.setId(orderItemDto.id);
        orderItem.setGivenCargo(orderItemDto.isGivenCargo);
        orderItem.setBasketProduct(basketProductService.findBasketProductById(orderItemDto.basketProductId));
        orderItem.setShop(shopService.findById(orderItemDto.shopId));
        return orderItem;
    }
    public OrderItemDto toDto(OrderItem orderItem){
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.id = orderItem.getId();
        orderItemDto.isGivenCargo = orderItem.isGivenCargo();
        orderItemDto.basketProductId = orderItem.getBasketProduct().getId();
        orderItemDto.shopId = orderItem.getShop().getId();
        return orderItemDto;
    }

}
