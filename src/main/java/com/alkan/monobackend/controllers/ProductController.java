package com.alkan.monobackend.controllers;

import com.alkan.monobackend.dtos.ProductDto;
import com.alkan.monobackend.entities.Product;
import com.alkan.monobackend.responses.ProductResponse;
import com.alkan.monobackend.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ProductResponse create(@RequestBody ProductDto productDto){
        return service.create(productDto);
    }

    @GetMapping("/list")
    public List<Product> list(){
        return service.getAll();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        return service.delete(id);
    }
    @GetMapping("/{id}")
    public Product findById(@PathVariable int id){
        return service.findById(id);
    }
}
