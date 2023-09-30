package com.alkan.monobackend.services;

import com.alkan.monobackend.dtos.ProductDto;
import com.alkan.monobackend.entities.Product;
import com.alkan.monobackend.repositories.ProductRepository;
import com.alkan.monobackend.responses.ProductResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository repository;
    private CategoryService categoryService;

    public ProductService(ProductRepository repository, CategoryService categoryService) {
        this.repository = repository;
        this.categoryService = categoryService;
    }

    public Product toEntity(ProductDto productDto){
        Product product = new Product();
        product.setId(productDto.id);
        product.setName(productDto.name);
        product.setPrice(productDto.price);
        product.setCategory(categoryService.findById(productDto.categoryId));
        return product;
    }
    public ProductDto toDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.id = product.getId();
        productDto.name = product.getName();
        productDto.price = product.getPrice();
        productDto.categoryId = product.getCategory().getId();
        return productDto;
    }

    public ProductResponse create(ProductDto productDto){
        Product product = toEntity(productDto);
        repository.save(product);
        ProductResponse productResponse = new ProductResponse();
        productResponse.responseBuilder("Product Created", HttpStatus.CREATED, productDto);
        return productResponse;
    }

    public List<Product> getAll(){
        return repository.findAll();
    }

    public String delete(int id){
        repository.deleteById(id);
        return "Product deleted";
    }

    public Product findById(int id) {
        Product product = repository.findById(id).get();
        return product;
    }

}
