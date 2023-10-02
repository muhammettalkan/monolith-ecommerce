package com.alkan.monobackend.services;

import com.alkan.monobackend.dtos.BasketProductDto;
import com.alkan.monobackend.entities.BasketProduct;
import com.alkan.monobackend.request.AddBasketProductToBasketRequest;

import java.util.List;

public interface BasketProductService {

    BasketProduct mapToEntity(BasketProductDto basketProductDto);
    BasketProductDto mapToDto(BasketProduct basketProduct);
    BasketProductDto addToBasket(AddBasketProductToBasketRequest request);
    String delete(String id);
    double calculateBasketProductAmount(double price, int quantity);
    List<BasketProductDto> list();
    BasketProductDto findById(String id);
    List<BasketProductDto> listBasketProductsByBasketId(String id);

}
