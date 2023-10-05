package com.alkan.monobackend.services;

import com.alkan.monobackend.dtos.CategoryDto;
import com.alkan.monobackend.entities.Category;

import java.util.List;

public interface CategoryService {
    Category findById(int id);
    Category toEntity(CategoryDto categoryDto);
    CategoryDto toDto(Category category);
    CategoryDto create(String name);
    CategoryDto findById(String id);
    List<CategoryDto> findAll();
    String delete(String id);

}
