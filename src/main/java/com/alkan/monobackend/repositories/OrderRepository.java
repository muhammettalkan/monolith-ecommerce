package com.alkan.monobackend.repositories;

import com.alkan.monobackend.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
