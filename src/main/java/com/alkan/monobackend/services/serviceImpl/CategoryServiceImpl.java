package com.alkan.monobackend.services.serviceImpl;

import com.alkan.monobackend.dtos.CategoryDto;
import com.alkan.monobackend.entities.Category;
import com.alkan.monobackend.repositories.CategoryRepository;
import com.alkan.monobackend.services.CategoryService;
import com.alkan.monobackend.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository repository;
    private ProductService productService;

    public CategoryServiceImpl(CategoryRepository repository, ProductService productService) {
        this.repository = repository;
        this.productService = productService;
    }

    public Category mapToEntity(CategoryDto categoryDto){
        Category category=new Category();
        category.setId(categoryDto.id);
        category.setName(categoryDto.name);
        category.setProductList(categoryDto.productDtoList
                .stream()
                .map(productService::toEntity)
                .toList());
        return category;
    }

    public CategoryDto mapToDto(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.id = category.getId();
        categoryDto.name = category.getName();
        return categoryDto;
    }

    public CategoryDto create(CategoryDto categoryDto) {
        Category category = repository.save(mapToEntity(categoryDto));
        return mapToDto(category);
    }
    public CategoryDto findById(String id) {
        Category category = repository.findById(Integer.parseInt(id)).get();
        return mapToDto(category);
    }
    public List<CategoryDto> findAll() {
        return repository.findAll().stream().map(this::mapToDto).toList();
    }

    public String delete(String id) {
        repository.deleteById(Integer.parseInt(id));
        return "Category deleted";
    }

}
