package com.alkan.monobackend.services.serviceImpl;

import com.alkan.monobackend.dtos.ProductDto;
import com.alkan.monobackend.entities.Product;
import com.alkan.monobackend.repositories.ProductRepository;
import com.alkan.monobackend.request.CreateProductRequest;
import com.alkan.monobackend.services.CategoryService;
import com.alkan.monobackend.services.ProductService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository repository;
    private CategoryService categoryService;

    public ProductServiceImpl(ProductRepository repository,@Lazy CategoryService categoryService) {
        this.repository = repository;
        this.categoryService = categoryService;
    }
    public Product findProductById(int id){
        return repository.findById(id).get();
    }

    public Product toEntity(ProductDto productDto){
        Product product = new Product();
        product.setId(productDto.id);
        product.setName(productDto.name);
        product.setPrice(productDto.price);
        product.setCategory(categoryService.toEntity(categoryService.findById(String.valueOf(productDto.categoryId))));
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

    public ProductDto create(CreateProductRequest request){
        Product product = new Product();
        product.setName(request.name);
        product.setPrice(request.price);
        product.setCategory(categoryService.findById(request.categoryId));
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

    public List<ProductDto> findByCategoryId(String id) {
        return repository.findByCategoryId(Integer.parseInt(id)).stream().map(this::toDto).toList();
    }

}
