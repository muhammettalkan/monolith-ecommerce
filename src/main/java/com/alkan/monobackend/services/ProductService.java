package com.alkan.monobackend.services;

import com.alkan.monobackend.dtos.ProductDto;
import com.alkan.monobackend.entities.Product;

import java.util.List;

public interface ProductService {

    Product toEntity(ProductDto productDto);
    ProductDto toDto(Product product);
    ProductDto create(ProductDto productDto);
    List<ProductDto> getAll();
    String delete(String id);
    ProductDto findById(String id);

}
