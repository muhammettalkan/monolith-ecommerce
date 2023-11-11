package com.alkan.monobackend.services.serviceImpl;

import com.alkan.monobackend.dtos.OrderDto;
import com.alkan.monobackend.entities.BasketProduct;
import com.alkan.monobackend.entities.Order;
import com.alkan.monobackend.entities.OrderItem;
import com.alkan.monobackend.entities.Shop;
import com.alkan.monobackend.exception.custom.ObjectNotFoundException;
import com.alkan.monobackend.repositories.OrderRepository;
import com.alkan.monobackend.services.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
    public List<OrderDto> giveOrder(int basketId) {
        List<OrderDto> orderDtoList = new ArrayList<>();
        List<BasketProduct> basketProducts = basketProductService.listBasketProductsByBasketId(String.valueOf(basketId))
                .stream()
                .map(basketProductService::mapToEntity)
                .toList();

        if (basketProducts.isEmpty()) {
            throw new EntityNotFoundException("Add products to the basket first!");
        }

        while (!basketProducts.isEmpty()) {
            Order order = saveByShop(basketProducts);
            orderDtoList.add(toDto(order));
        }

        return orderDtoList;
    }

    public Order saveByShop(List<BasketProduct> basketProducts) {
        if (basketProducts.isEmpty()) {
            throw new EntityNotFoundException("Add products to the basket first!");
        }

        BasketProduct firstBasketProduct = basketProducts.get(0);
        Shop shop = firstBasketProduct.getProduct().getShopCategory().getShop();

        Order order = new Order();
        order.setShop(shop);
        order.setBasket(firstBasketProduct.getBasket());

        List<OrderItem> orderItems = new ArrayList<>();

        for (BasketProduct basketProduct : basketProducts) {
            if (basketProduct.getProduct().getShopCategory().getShop() == shop) {
                OrderItem orderItem = new OrderItem();
                orderItem.setOrder(order);
                orderItem.setBasketProduct(basketProduct);
                orderItems.add(orderItem);
            }
        }

        order.getOrderItemList().addAll(orderItems);

        repository.save(order);

        basketProducts.removeAll(orderItems);

        return order;
    }

}
