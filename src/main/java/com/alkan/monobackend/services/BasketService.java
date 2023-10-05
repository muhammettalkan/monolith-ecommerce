package com.alkan.monobackend.services;

import com.alkan.monobackend.dtos.BasketDto;
import com.alkan.monobackend.entities.Basket;
import com.alkan.monobackend.request.AddBasketProductToBasketRequest;

import java.util.List;

public interface BasketService {
    Basket findBasketById(int id);
    Basket mapDtoToEntity(BasketDto basketDto);
    BasketDto mapEntityToDto(Basket basket);
    BasketDto create(String customerId);
    String delete(String id);
    List<BasketDto> list();
    BasketDto findById(String basketId);
    double calculateTotalAmount(int basketId);
    BasketDto update(String customerId,BasketDto basketDto);
    BasketDto addOrRemoveBasketProduct(AddBasketProductToBasketRequest request);
    List<BasketDto> basketHistory(String customerId);
    BasketDto showCurrentBasket(String customerId);
}
