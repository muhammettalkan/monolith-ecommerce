package com.alkan.monobackend.services;

import com.alkan.monobackend.dtos.ShopDto;
import com.alkan.monobackend.entities.Shop;
import com.alkan.monobackend.repositories.ShopRepository;
import com.alkan.monobackend.responses.ShopResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopService {

    private ShopRepository repository;
    private ShopAdminService shopAdminService;
    public ShopService(ShopRepository repository, ShopAdminService shopAdminService) {
        this.repository = repository;
        this.shopAdminService = shopAdminService;
    }

    public Shop toEntity(ShopDto shopDto) {
        Shop shop = new Shop();
        shop.setId(shopDto.id);
        shop.setShopName(shopDto.shopName);
        shop.setAdmin(shopAdminService.toEntity(shopAdminService.findShopAdminById(shopDto.shopAdminId)));
        return shop;
    }

    public ShopDto toDto(Shop shop) {
        ShopDto shopDto = new ShopDto();
        shopDto.id = shop.getId();
        shopDto.shopName = shop.getShopName();
        shopDto.shopAdminId = shop.getAdmin().getId();
        return shopDto;
    }

    public ShopResponse create(ShopDto shopDto){
        Shop shop = toEntity(shopDto);
        repository.save(shop);
        ShopResponse response = new ShopResponse();
        response.responseBuilder("Shop Created", HttpStatus.CREATED,toDto(shop));
        return response;
    }
    public String delete(int id){
        Shop shop = repository.findById(id).get();
        repository.deleteById(id);
        return shop.getShopName() + " deleted";
    }
    public List<ShopDto> findAll(){
        List<Shop> shops = repository.findAll();
        List<ShopDto> shopDtos = new ArrayList<>();
        for (Shop shop : shops) {
            shopDtos.add(toDto(shop));
        }
        return shopDtos;
    }

}
