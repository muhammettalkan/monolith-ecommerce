package com.alkan.monobackend.services;

import com.alkan.monobackend.dtos.ShopCategoryDto;
import com.alkan.monobackend.entities.ShopCategory;
import com.alkan.monobackend.request.CreateShopCategoryRequest;

import java.util.List;

public interface ShopCategoryService {
    ShopCategory toEntity(ShopCategoryDto dto);

    ShopCategoryDto toDto(ShopCategory shopCategory);

    ShopCategoryDto create(CreateShopCategoryRequest request);

    List<ShopCategoryDto> findAll();

    ShopCategory findShopCategoryById(int id);

    ShopCategoryDto findById(int id);

    String delete(int id);
}
