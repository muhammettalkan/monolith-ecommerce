package com.alkan.monobackend.controllers;

import com.alkan.monobackend.dtos.CategoryDto;
import com.alkan.monobackend.entities.Category;
import com.alkan.monobackend.responses.CategoryResponse;
import com.alkan.monobackend.services.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public List<Category> getAll() {
        return categoryService.findAll();
    }

    @PostMapping("/create")
    public CategoryResponse create(@RequestBody CategoryDto categoryDto) {
        return categoryService.create(categoryDto);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        return categoryService.delete(id);
    }

}
