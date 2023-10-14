package com.alkan.monobackend.repositories;

import com.alkan.monobackend.entities.ShopCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopCategoryRepository extends JpaRepository<ShopCategory,Integer> {

}
