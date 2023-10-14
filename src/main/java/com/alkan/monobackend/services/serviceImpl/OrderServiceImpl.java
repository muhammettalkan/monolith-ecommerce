package com.alkan.monobackend.services.serviceImpl;

import com.alkan.monobackend.dtos.OrderDto;
import com.alkan.monobackend.entities.BasketProduct;
import com.alkan.monobackend.entities.Order;
import com.alkan.monobackend.entities.OrderItem;
import com.alkan.monobackend.exception.custom.ObjectNotFoundException;
import com.alkan.monobackend.repositories.OrderRepository;
import com.alkan.monobackend.services.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final OrderItemService orderItemService;
    private final BasketService basketService;
    private final ShopService shopService;
    private final BasketProductService basketProductService;

    public OrderServiceImpl(OrderRepository repository, @Lazy OrderItemService orderItemService, @Lazy BasketService basketService, @Lazy ShopService shopService, @Lazy BasketProductService basketProductService) {
        this.repository = repository;
        this.orderItemService = orderItemService;
        this.basketService = basketService;
        this.shopService = shopService;
        this.basketProductService = basketProductService;
    }
    public Order findById(int id){
        return repository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Order not found with id: " + id));
    }
    public Order toEntity(OrderDto orderDto){
        Order order = new Order();
        order.setId(orderDto.id);
        order.setGivenCargo(orderDto.isGivenCargo);
        order.setDelivered(orderDto.isDelivered);
        order.setBasket(basketService.findBasketById(orderDto.basketId));
        order.setOrderItemList(orderDto.orderItemDtoList.stream().map(orderItemService::toEntity).toList());
        order.setShop(shopService.findById(orderDto.shopId));
        return order;
    }
    public OrderDto toDto(Order order){
        OrderDto orderDto = new OrderDto();
        orderDto.id = order.getId();
        orderDto.isGivenCargo = order.isGivenCargo();
        orderDto.isDelivered = order.isDelivered();
        orderDto.basketId = order.getBasket().getId();
        orderDto.orderItemDtoList = order.getOrderItemList().stream().map(orderItemService::toDto).toList();
        orderDto.shopId = order.getShop().getId();
        return orderDto;
    }
    @Override
    public List<OrderDto> giveOrder(int basketId) {
        List<OrderDto> orderDtoList = new ArrayList<>();
        List<BasketProduct> basketProducts = basketProductService.listBasketProductsByBasketId(String.valueOf(basketId))
                .stream()
                .map(basketProductService::mapToEntity)
                .toList();
        while (basketProducts.size() != 0){
            Order order = saveByShop(basketProducts);
            orderDtoList.add(toDto(order));
        }
        return orderDtoList;
    }

    public Order saveByShop(List<BasketProduct> basketProducts){
        if (basketProducts.size() == 0){
            return null;
        }
        List<Integer> indexes = new ArrayList<>();
        indexes.add(0);
        Order order = new Order();
        order.setShop(basketProducts.get(0).getProduct().getShopCategory().getShop());
        order.setBasket(basketProducts.get(0).getBasket());
        OrderItem initialItem = new OrderItem();
        initialItem.setOrder(order);
        initialItem.setBasketProduct(basketProducts.get(0));
        order.getOrderItemList().add(initialItem);
        for (int i = 1; i < basketProducts.size(); i++){
            if (basketProducts.get(i).getProduct().getShopCategory().getShop() == basketProducts.get(0).getProduct().getShopCategory().getShop()){
                OrderItem orderItem = new OrderItem();
                orderItem.setOrder(order);
                orderItem.setBasketProduct(basketProducts.get(i));
                order.getOrderItemList().add(orderItem);
                indexes.add(i);
            }
        }
        repository.save(order);
        for (int i = 0; i < indexes.size(); i++){
            basketProducts.remove(indexes.get(i));
        }
        return order;
    }

}
