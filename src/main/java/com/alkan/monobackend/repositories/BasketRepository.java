package com.alkan.monobackend.repositories;

import com.alkan.monobackend.entities.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket,Integer> {

    Basket findByCustomerId(int customerId);
    Basket findById(int basketId);

}
