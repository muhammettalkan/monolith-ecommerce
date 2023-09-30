package com.alkan.monobackend.repositories;

import com.alkan.monobackend.entities.BasketProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BasketProductRepository extends JpaRepository<BasketProduct, Integer> {

    List<BasketProduct> findBasketProductsByBasket_Id(int basketId);
}
