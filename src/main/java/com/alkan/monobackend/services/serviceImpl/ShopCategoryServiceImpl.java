package com.alkan.monobackend.services.serviceImpl;

import com.alkan.monobackend.dtos.ShopCategoryDto;
import com.alkan.monobackend.entities.ShopCategory;
import com.alkan.monobackend.repositories.ShopCategoryRepository;
import com.alkan.monobackend.request.CreateShopCategoryRequest;
import com.alkan.monobackend.services.CategoryService;
import com.alkan.monobackend.services.ProductService;
import com.alkan.monobackend.services.ShopCategoryService;
import com.alkan.monobackend.services.ShopService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {

    private ShopCategoryRepository repository;
    private ShopService shopService;
    private CategoryService categoryService;
    private ProductService productService;

    public ShopCategoryServiceImpl(ShopCategoryRepository repository, @Lazy ShopService shopService,@Lazy CategoryService categoryService, ProductService productService) {
        this.repository = repository;
        this.shopService = shopService;
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @Override
    public ShopCategory toEntity(ShopCategoryDto dto){
        ShopCategory shopCategory = new ShopCategory();
        shopCategory.setId(dto.id);
        shopCategory.setShop(shopService.findById(dto.shopId));
        shopCategory.setCategory(categoryService.findById(dto.categoryId));
        if (dto.productDtoList != null)
            shopCategory.setProductList(dto.productDtoList
                    .stream()
                    .map(productService::toEntity)
                    .toList());
        else shopCategory.setProductList(null);
        return shopCategory;
    }

    @Override
    public ShopCategoryDto toDto(ShopCategory shopCategory){
        ShopCategoryDto dto = new ShopCategoryDto();
        dto.id = shopCategory.getId();
        dto.shopId = shopCategory.getShop().getId();
        dto.categoryId = shopCategory.getCategory().getId();
        return dto;
    }

    @Override
    public ShopCategoryDto create(CreateShopCategoryRequest request){
        ShopCategory shopCategory = new ShopCategory();
        shopCategory.setShop(shopService.findById(request.shopId));
        shopCategory.setCategory(categoryService.findById(request.categoryId));
        repository.save(shopCategory);
        return toDto(shopCategory);
    }

    @Override
    public List<ShopCategoryDto> findAll(){
        return repository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }
    @Override
    public ShopCategory findShopCategoryById(int id){
        return repository.findById(id).get();
    }

    @Override
    public ShopCategoryDto findById(int id){
        ShopCategory shopCategory = findShopCategoryById(id);
        return toDto(shopCategory);
    }

    @Override
    public String delete(int id){
        repository.deleteById(id);
        return "ShopCategory deleted";
    }


}
