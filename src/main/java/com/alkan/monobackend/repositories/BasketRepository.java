package com.alkan.monobackend.repositories;

import com.alkan.monobackend.entities.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BasketRepository extends JpaRepository<Basket,Integer> {

    List<Basket> findAllByCustomer_Id(int customerId);

}
