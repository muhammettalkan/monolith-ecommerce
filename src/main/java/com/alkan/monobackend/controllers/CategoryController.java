package com.alkan.monobackend.controllers;

import com.alkan.monobackend.dtos.CategoryDto;
import com.alkan.monobackend.responses.CategoryResponse;
import com.alkan.monobackend.services.CategoryService;
import com.alkan.monobackend.services.serviceImpl.CategoryServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping()
    public ResponseEntity<CategoryResponse> getAll() {
        return ResponseEntity.ok(new CategoryResponse(2000, "Categories found", categoryService.findAll()));
    }
    @PostMapping("/create")
    public ResponseEntity<CategoryResponse> create(@RequestParam String name) {
        return ResponseEntity.ok(new CategoryResponse(2000, "Category created", categoryService.create(name)));
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        return categoryService.delete(id);
    }

}
