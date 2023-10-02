package com.alkan.monobackend.services;

import com.alkan.monobackend.dtos.CategoryDto;
import com.alkan.monobackend.entities.Category;

import java.util.List;

public interface CategoryService {

    Category mapToEntity(CategoryDto categoryDto);
    CategoryDto mapToDto(Category category);
    CategoryDto create(CategoryDto categoryDto);
    CategoryDto findById(String id);
    List<CategoryDto> findAll();
    String delete(String id);

}
