package com.alkan.monobackend.services;

import com.alkan.monobackend.dtos.CategoryDto;
import com.alkan.monobackend.entities.Category;
import com.alkan.monobackend.repositories.CategoryRepository;
import com.alkan.monobackend.responses.CategoryResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Category mapToEntity(CategoryDto categoryDto){
        Category category=new Category();
        category.setId(categoryDto.id);
        category.setName(categoryDto.name);
        return category;
    }

    public CategoryDto mapToDto(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.id = category.getId();
        categoryDto.name = category.getName();
        return categoryDto;
    }

    public CategoryResponse create(CategoryDto categoryDto) {
        Category category = repository.save(mapToEntity(categoryDto));
        CategoryResponse categoryResponse=new CategoryResponse();
        categoryResponse.responseBuilder("Category Created", HttpStatus.CREATED, mapToDto(category));
        return categoryResponse;
    }
    public Category findById(int id) {
        Category category = repository.findById(id).get();
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.responseBuilder("Category Found", HttpStatus.OK, category);
        return category;
    }
    public List<Category> findAll() {
        return repository.findAll();
    }

    public String delete(int id) {
        repository.deleteById(id);
        return "Category deleted";
    }

}
