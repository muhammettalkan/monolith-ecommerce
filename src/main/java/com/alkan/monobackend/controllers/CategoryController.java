package com.alkan.monobackend.controllers;

import com.alkan.monobackend.dtos.CategoryDto;
import com.alkan.monobackend.responses.CategoryResponse;
import com.alkan.monobackend.services.serviceImpl.CategoryServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private CategoryServiceImpl categoryServiceImpl;

    public CategoryController(CategoryServiceImpl categoryServiceImpl) {
        this.categoryServiceImpl = categoryServiceImpl;
    }
    @GetMapping()
    public ResponseEntity<CategoryResponse> getAll() {
        return ResponseEntity.ok(new CategoryResponse(2000, "Categories found", categoryServiceImpl.findAll()));
    }
    @PostMapping("/create")
    public ResponseEntity<CategoryResponse> create(@RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok(new CategoryResponse(2000, "Category created", categoryServiceImpl.create(categoryDto)));
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        return categoryServiceImpl.delete(id);
    }

}
