package com.alkan.monobackend.services;

import com.alkan.monobackend.dtos.ShopDto;
import com.alkan.monobackend.entities.Shop;

import java.util.List;

public interface ShopService {

    Shop toEntity(ShopDto shopDto);
    ShopDto toDto(Shop shop);
    ShopDto create(ShopDto shopDto);
    String delete(int id);
    List<ShopDto> findAll();

}
