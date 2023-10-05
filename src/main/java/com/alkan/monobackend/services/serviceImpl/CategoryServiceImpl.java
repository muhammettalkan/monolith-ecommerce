package com.alkan.monobackend.services.serviceImpl;

import com.alkan.monobackend.dtos.CategoryDto;
import com.alkan.monobackend.entities.Category;
import com.alkan.monobackend.repositories.CategoryRepository;
import com.alkan.monobackend.services.CategoryService;
import com.alkan.monobackend.services.ProductService;
import com.alkan.monobackend.services.ShopService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;
    private final ProductService productService;
    private final ShopService shopService;

    public CategoryServiceImpl(CategoryRepository repository, ProductService productService, ShopService shopService) {
        this.repository = repository;
        this.productService = productService;
        this.shopService = shopService;
    }
    public Category findById(int id) {
        return repository.findById(id).get();
    }

    public Category toEntity(CategoryDto categoryDto){
        Category category=new Category();
        category.setId(categoryDto.id);
        category.setName(categoryDto.name);
        category.setProductList(categoryDto.productDtoList
                .stream()
                .map(productService::toEntity)
                .toList());
        category.setShopList(categoryDto.shopDtoList
                .stream()
                .map(shopService::toEntity)
                .toList());
        return category;
    }

    public CategoryDto toDto(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.id = category.getId();
        categoryDto.name = category.getName();
        categoryDto.productDtoList = productService.findByCategoryId(String.valueOf(category.getId()));
//        categoryDto.shopDtoList = shopService.findByCategoryId(String.valueOf(category.getId()));
        return categoryDto;
    }

    public CategoryDto create(String name) {
        Category category = new Category();
        category.setName(name);
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
