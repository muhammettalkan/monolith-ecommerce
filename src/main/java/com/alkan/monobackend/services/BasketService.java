package com.alkan.monobackend.services;

import com.alkan.monobackend.dtos.BasketDto;
import com.alkan.monobackend.entities.Basket;

import java.util.List;

public interface BasketService {
    Basket mapDtoToEntity(BasketDto basketDto);
    BasketDto mapEntityToDto(Basket basket);
    BasketDto create(String customerId);
    String delete(String id);
    List<BasketDto> list();
    BasketDto findById(String basketId);
    double calculateTotalAmount(String basketId);
    BasketDto update(String customerId,BasketDto basketDto);
}
