package com.alkan.monobackend.repositories;

import com.alkan.monobackend.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
