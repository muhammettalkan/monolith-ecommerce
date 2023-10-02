package com.alkan.monobackend.services;

import com.alkan.monobackend.dtos.ShopAdminDto;
import com.alkan.monobackend.entities.ShopAdmin;
import com.alkan.monobackend.request.LoginRequest;

import java.util.List;

public interface ShopAdminService {

    ShopAdmin toEntity(ShopAdminDto dto);
    ShopAdminDto toDto(ShopAdmin shopAdmin);
    ShopAdminDto findShopAdminById(String id);
    List<ShopAdminDto> findAll();
    ShopAdminDto update(String id, ShopAdminDto shopAdminDto);
    ShopAdminDto create(ShopAdminDto shopAdminDto);
    String delete(String id);
    ShopAdminDto login(LoginRequest loginRequest);

}
