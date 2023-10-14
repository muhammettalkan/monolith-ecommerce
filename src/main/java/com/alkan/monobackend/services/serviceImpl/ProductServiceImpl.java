package com.alkan.monobackend.services.serviceImpl;

import com.alkan.monobackend.dtos.ProductDto;
import com.alkan.monobackend.entities.Product;
import com.alkan.monobackend.repositories.ProductRepository;
import com.alkan.monobackend.request.CreateProductRequest;
import com.alkan.monobackend.services.BasketProductService;
import com.alkan.monobackend.services.ProductService;
import com.alkan.monobackend.services.ShopCategoryService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository repository;
    private ShopCategoryService shopCategoryService;
    private BasketProductService basketProductService;

    public ProductServiceImpl(ProductRepository repository,@Lazy ShopCategoryService shopCategoryService, BasketProductService basketProductService) {
        this.repository = repository;
        this.shopCategoryService = shopCategoryService;
        this.basketProductService = basketProductService;
    }
    public Product findProductById(int id){
        return repository.findById(id).get();
    }

    public Product toEntity(ProductDto productDto){
        Product product = new Product();
        product.setId(productDto.id);
        product.setName(productDto.name);
        product.setPrice(productDto.price);
        product.setShopCategory(shopCategoryService.findShopCategoryById(productDto.shopCategoryId));
        if (productDto.basketProductDtoList != null){
            product.setBasketProductList(productDto.basketProductDtoList
                    .stream()
                    .map(basketProductService::mapToEntity)
                    .toList());
        }
        else product.setBasketProductList(null);
        return product;
    }
    public ProductDto toDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.id = product.getId();
        productDto.name = product.getName();
        productDto.price = product.getPrice();
        productDto.shopCategoryId = product.getShopCategory().getId();
        if (product.getBasketProductList() != null){
            productDto.basketProductDtoList = product.getBasketProductList()
                    .stream()
                    .map(basketProductService::mapToDto)
                    .toList();
        }
        else productDto.basketProductDtoList = null;
        return productDto;
    }

    public ProductDto addProductToShop(CreateProductRequest request){
        Product product = new Product();
        product.setName(request.name);
        product.setPrice(request.price);
        product.setShopCategory(shopCategoryService.findShopCategoryById(request.shopCategoryId));
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

    @Override
    public List<ProductDto> findByCategoryId(String id) {
        return repository.findAllByShopCategoryId(Integer.parseInt(id)).stream().map(this::toDto).toList();
    }

}
