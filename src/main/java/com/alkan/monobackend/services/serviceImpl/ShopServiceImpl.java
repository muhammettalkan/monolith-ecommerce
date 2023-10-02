package com.alkan.monobackend.services.serviceImpl;

import com.alkan.monobackend.dtos.ShopDto;
import com.alkan.monobackend.entities.Shop;
import com.alkan.monobackend.repositories.ShopRepository;
import com.alkan.monobackend.services.ShopAdminService;
import com.alkan.monobackend.services.ShopService;
import com.alkan.monobackend.services.serviceImpl.ShopAdminServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    private ShopRepository repository;
    private ShopAdminService shopAdminService;
    public ShopServiceImpl(ShopRepository repository, ShopAdminService shopAdminService) {
        this.repository = repository;
        this.shopAdminService = shopAdminService;
    }

    public Shop toEntity(ShopDto shopDto) {
        Shop shop = new Shop();
        shop.setId(shopDto.id);
        shop.setShopName(shopDto.shopName);
        shop.setAdmin(shopAdminService.toEntity(shopAdminService.findShopAdminById(String.valueOf(shopDto.shopAdminId))));
        return shop;
    }

    public ShopDto toDto(Shop shop) {
        ShopDto shopDto = new ShopDto();
        shopDto.id = shop.getId();
        shopDto.shopName = shop.getShopName();
        shopDto.shopAdminId = shop.getAdmin().getId();
        return shopDto;
    }

    public ShopDto create(ShopDto shopDto){
        if (repository.existsByShopName(shopDto.shopName)){
            throw new RuntimeException("Shop name is already in use");
        }
        return toDto(repository.save(toEntity(shopDto)));
    }
    public String delete(int id){
        Shop shop = repository.findById(id).get();
        repository.deleteById(id);
        return shop.getShopName() + " deleted";
    }
    public List<ShopDto> findAll(){
        return repository.findAll().stream().map(this::toDto).toList();
    }

}
