package com.alkan.monobackend.repositories;

import com.alkan.monobackend.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByShopCategoryId(int shopCategoryId);
}
