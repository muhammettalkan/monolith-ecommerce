package com.alkan.monobackend.services.serviceImpl;

import com.alkan.monobackend.dtos.CategoryDto;
import com.alkan.monobackend.entities.Category;
import com.alkan.monobackend.repositories.CategoryRepository;
import com.alkan.monobackend.request.UpdateCategoryRequest;
import com.alkan.monobackend.services.CategoryService;
import com.alkan.monobackend.services.ShopCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;
    private final ShopCategoryService shopCategoryService;

    public CategoryServiceImpl(CategoryRepository repository, ShopCategoryService shopCategoryService) {
        this.repository = repository;
        this.shopCategoryService = shopCategoryService;
    }
    public Category findById(int id) {
        return repository.findById(id).get();
    }

    public Category toEntity(CategoryDto categoryDto){
        Category category=new Category();
        category.setId(categoryDto.id);
        category.setName(categoryDto.name);
        if (categoryDto.shopCategoryDtoList != null) {
            category.setShopCategoryList(categoryDto.shopCategoryDtoList
                    .stream()
                    .map(shopCategoryService::toEntity)
                    .toList());
        }else category.setShopCategoryList(null);
        return category;
    }

    public CategoryDto toDto(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.id = category.getId();
        categoryDto.name = category.getName();
        if (category.getShopCategoryList() != null) {
            categoryDto.shopCategoryDtoList = category.getShopCategoryList()
                    .stream()
                    .map(shopCategoryService::toDto)
                    .toList();
        }else categoryDto.shopCategoryDtoList = null;
        return categoryDto;
    }

    public CategoryDto create(String name) {
        Category category = new Category();
        category.setName(name);
        repository.save(category);
        return toDto(category);
    }
    @Override
    public CategoryDto update(UpdateCategoryRequest request) {
        Category category = repository.findById(request.id).get();
        category.setName(request.name);
        repository.save(category);
        return toDto(category);
    }
    public CategoryDto findById(String id) {
        Category category = repository.findById(Integer.parseInt(id)).get();
        return toDto(category);
    }
    public List<CategoryDto> findAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    public String delete(String id) {
        repository.deleteById(Integer.parseInt(id));
        return "Category deleted";
    }

}
