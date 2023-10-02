package com.alkan.monobackend.repositories;

import com.alkan.monobackend.entities.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Integer> {
    boolean existsByShopName(String shopName);
}
