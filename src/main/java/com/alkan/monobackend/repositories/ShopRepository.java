package com.alkan.monobackend.repositories;

import com.alkan.monobackend.entities.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shop, Integer> {
    Optional<Shop> findByName(String shopName);
}
