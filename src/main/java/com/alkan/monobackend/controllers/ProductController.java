package com.alkan.monobackend.controllers;

import com.alkan.monobackend.dtos.ProductDto;
import com.alkan.monobackend.entities.Product;
import com.alkan.monobackend.responses.ProductResponse;
import com.alkan.monobackend.services.ProductService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ProductResponse> create(@RequestBody ProductDto productDto){
        return ResponseEntity.ok( new ProductResponse(2000,"Product created",service.create(productDto)));
    }

    @GetMapping("/list")
    public ResponseEntity<ProductResponse> list(){
        return ResponseEntity.ok(new ProductResponse(2000,"Products found",service.getAll()));
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable String id){
        return service.delete(id);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable String id){
        return ResponseEntity.ok(new ProductResponse(2000,"Product found",service.findById(id)));
    }
}
