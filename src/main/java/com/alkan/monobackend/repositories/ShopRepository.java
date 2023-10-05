package com.alkan.monobackend.repositories;

import com.alkan.monobackend.entities.Category;
import com.alkan.monobackend.entities.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shop, Integer> {
    Optional<Shop> findByShopName(String shopName);

    List<Shop> findByCategoryList(Category category);
}
