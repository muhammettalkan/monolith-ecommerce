package com.alkan.monobackend.services;

import com.alkan.monobackend.dtos.ShopDto;
import com.alkan.monobackend.entities.Category;
import com.alkan.monobackend.entities.Shop;
import com.alkan.monobackend.request.CreateShopCategoryRequest;

import java.util.List;

public interface ShopService {

    Shop findById(int id);
    Shop toEntity(ShopDto shopDto);
    ShopDto toDto(Shop shop);
    ShopDto create(ShopDto shopDto);
    String delete(int id);
    List<ShopDto> findAll();
    ShopDto addCategory(CreateShopCategoryRequest request);
}
