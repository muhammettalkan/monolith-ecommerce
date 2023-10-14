package com.alkan.monobackend.repositories;

import com.alkan.monobackend.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    List<OrderItem> findOrderItemsByShopId(int shopId);

}
