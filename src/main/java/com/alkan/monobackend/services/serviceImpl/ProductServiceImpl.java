package com.alkan.monobackend.services.serviceImpl;

import com.alkan.monobackend.dtos.ProductDto;
import com.alkan.monobackend.entities.Product;
import com.alkan.monobackend.repositories.ProductRepository;
import com.alkan.monobackend.services.CategoryService;
import com.alkan.monobackend.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository repository;
    private CategoryService categoryService;

    public ProductServiceImpl(ProductRepository repository, CategoryService categoryService) {
        this.repository = repository;
        this.categoryService = categoryService;
    }

    public Product toEntity(ProductDto productDto){
        Product product = new Product();
        product.setId(productDto.id);
        product.setName(productDto.name);
        product.setPrice(productDto.price);
        product.setCategory(categoryService.mapToEntity(categoryService.findById(String.valueOf(productDto.categoryId))));
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

    public ProductDto create(ProductDto productDto){
        Product product = toEntity(productDto);
        repository.save(product);
        return toDto(product);
    }

    public List<ProductDto> getAll(){
        return repository.findAll().stream().map(this::toDto).toList();
    }

    public String delete(String id){
        repository.deleteById(Integer.parseInt(id));
        return "Product deleted";
    }

    public ProductDto findById(String id) {
        return toDto(repository.findById(Integer.parseInt(id)).get());
    }

}
