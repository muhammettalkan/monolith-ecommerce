package com.alkan.monobackend.services.serviceImpl;

import com.alkan.monobackend.dtos.ShopCategoryDto;
import com.alkan.monobackend.dtos.ShopDto;
import com.alkan.monobackend.entities.Shop;
import com.alkan.monobackend.entities.ShopCategory;
import com.alkan.monobackend.exception.custom.ShopNameIsAlreadyInUse;
import com.alkan.monobackend.repositories.ShopRepository;
import com.alkan.monobackend.request.CreateShopCategoryRequest;
import com.alkan.monobackend.services.OrderService;
import com.alkan.monobackend.services.ShopAdminService;
import com.alkan.monobackend.services.ShopCategoryService;
import com.alkan.monobackend.services.ShopService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    private ShopRepository repository;
    private ShopAdminService shopAdminService;
    private ShopCategoryService shopCategoryService;
    private OrderService orderService;

    public ShopServiceImpl(ShopRepository repository, @Lazy ShopAdminService shopAdminService,@Lazy ShopCategoryService shopCategoryService, OrderService orderService) {
        this.repository = repository;
        this.shopAdminService = shopAdminService;
        this.shopCategoryService = shopCategoryService;
        this.orderService = orderService;

    }

    public Shop findById(int id) {
        return repository.findById(id).get();
    }

    public Shop toEntity(ShopDto shopDto) {
        Shop shop = new Shop();
        shop.setId(shopDto.id);
        shop.setName(shopDto.name);
        shop.setAdmin(shopAdminService.findById(shopDto.shopAdminId));
        if (shopDto.categoryList != null)
            shop.setCategoryList(shopDto.categoryList
                    .stream()
                    .map(shopCategoryService::toEntity)
                    .toList());
        else shop.setCategoryList(null);
        if (shopDto.orderList != null)
            shop.setOrderList(shopDto.orderList
                    .stream()
                    .map(orderService::toEntity)
                    .toList());
        else shop.setOrderList(null);
        return shop;
    }

    public ShopDto toDto(Shop shop) {
        ShopDto shopDto = new ShopDto();
        shopDto.id = shop.getId();
        shopDto.name = shop.getName();
        shopDto.shopAdminId = shop.getAdmin().getId();
        if (shop.getCategoryList() != null) {
            shopDto.categoryList = shop.getCategoryList()
                    .stream()
                    .map(shopCategoryService::toDto)
                    .toList();
        } else shopDto.categoryList = null;
        if (shop.getOrderList() != null) {
            shopDto.orderList = shop.getOrderList()
                    .stream()
                    .map(orderService::toDto)
                    .toList();
        } else shopDto.orderList = null;
        return shopDto;
    }

    public ShopDto create(ShopDto shopDto) {
        if (repository.findByName(shopDto.name).isPresent()) {
            throw new ShopNameIsAlreadyInUse("Shop name is already in use by another Shop. Please try another name.");
        }
        Shop shop = toEntity(shopDto);
        repository.save(shop);
        return toDto(shop);
    }

    public String delete(int id) {
        Shop shop = repository.findById(id).get();
        repository.deleteById(id);
        return shop.getName() + " deleted";
    }

    public List<ShopDto> findAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }
    @Override
    public ShopDto addCategory(CreateShopCategoryRequest request) {
        Shop shop = findById(request.shopId);
        if (shop.getCategoryList()
                .stream()
                .map(ShopCategory::getCategory)
                .anyMatch(category -> category.getId() == request.categoryId)) {
            throw new ShopNameIsAlreadyInUse("Shop already has this category.");
        } else {
            ShopCategoryDto shopCategoryDto = shopCategoryService.create(request);
            shop.getCategoryList().add(shopCategoryService.toEntity(shopCategoryDto));
        }
        return toDto(shop);
    }

}
