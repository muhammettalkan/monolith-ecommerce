package com.alkan.monobackend.services.serviceImpl;

import com.alkan.monobackend.dtos.ShopDto;
import com.alkan.monobackend.entities.Category;
import com.alkan.monobackend.entities.Shop;
import com.alkan.monobackend.exception.custom.ShopNameIsAlreadyInUse;
import com.alkan.monobackend.repositories.ShopRepository;
import com.alkan.monobackend.services.CategoryService;
import com.alkan.monobackend.services.OrderItemService;
import com.alkan.monobackend.services.ShopAdminService;
import com.alkan.monobackend.services.ShopService;
import com.alkan.monobackend.services.serviceImpl.ShopAdminServiceImpl;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    private ShopRepository repository;
    private ShopAdminService shopAdminService;
    private CategoryService categoryService;
    private OrderItemService orderItemService;
    public ShopServiceImpl(ShopRepository repository, ShopAdminService shopAdminService,@Lazy CategoryService categoryService,@Lazy OrderItemService orderItemService) {
        this.repository = repository;
        this.shopAdminService = shopAdminService;
        this.categoryService = categoryService;
        this.orderItemService = orderItemService;
    }
    public Shop findById(int id) {
        return repository.findById(id).get();
    }

    public Shop toEntity(ShopDto shopDto) {
        Shop shop = new Shop();
        shop.setId(shopDto.id);
        shop.setShopName(shopDto.shopName);
        shop.setAdmin(shopAdminService.findById(shopDto.shopAdminId));
        shop.setCategoryList(new ArrayList<>());
        shop.setOrderItemList(new ArrayList<>());
        return shop;
    }

    public ShopDto toDto(Shop shop) {
        ShopDto shopDto = new ShopDto();
        shopDto.id = shop.getId();
        shopDto.shopName = shop.getShopName();
        shopDto.shopAdminId = shop.getAdmin().getId();
        if (shop.getCategoryList() != null){
            shopDto.categoryList = shop.getCategoryList().stream().map(categoryService::toDto).toList();
        }
        if (shop.getOrderItemList() != null){
            shopDto.orderItemList = shop.getOrderItemList().stream().map(orderItemService::toDto).toList();
        }
        return shopDto;
    }

    public ShopDto create(ShopDto shopDto) {
        if (repository.findByShopName(shopDto.shopName).isPresent()){
            throw new ShopNameIsAlreadyInUse("Shop name is already in use by another Shop.");
        }
        Shop shop = new Shop();
        shop.setShopName(shopDto.shopName);
        shop.setAdmin(shopAdminService.findById(shopDto.shopAdminId));
        repository.save(shop);
        return toDto(shop);
    }
    public String delete(int id){
        Shop shop = repository.findById(id).get();
        repository.deleteById(id);
        return shop.getShopName() + " deleted";
    }
    public List<ShopDto> findAll(){
        return repository.findAll().stream().map(this::toDto).toList();
    }
    public List<ShopDto> findByCategory(String id) {
        return repository.findByCategoryList(categoryService.findById(Integer.parseInt(id))).stream().map(this::toDto).toList();
    }

}
