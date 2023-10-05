package com.alkan.monobackend.services;

import com.alkan.monobackend.dtos.ProductDto;
import com.alkan.monobackend.entities.Product;
import com.alkan.monobackend.request.CreateProductRequest;

import java.util.List;

public interface ProductService {

    Product findProductById(int id);

    Product toEntity(ProductDto productDto);

    ProductDto toDto(Product product);

    ProductDto create(CreateProductRequest request);

    List<ProductDto> getAll();

    String delete(String id);

    ProductDto findById(String id);

    List<ProductDto> findByCategoryId(String id);

}
