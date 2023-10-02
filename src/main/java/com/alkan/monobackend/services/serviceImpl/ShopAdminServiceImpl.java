package com.alkan.monobackend.services.serviceImpl;

import com.alkan.monobackend.dtos.ShopAdminDto;
import com.alkan.monobackend.entities.ShopAdmin;
import com.alkan.monobackend.exception.custom.EmailHasBeenTakenAlreadyException;
import com.alkan.monobackend.repositories.ShopAdminRepository;
import com.alkan.monobackend.request.LoginRequest;
import com.alkan.monobackend.services.ShopAdminService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ShopAdminServiceImpl implements ShopAdminService {

    private final ShopAdminRepository repository;
    public ShopAdminServiceImpl(ShopAdminRepository repository) {
        this.repository = repository;
    }

    public ShopAdminDto toDto(ShopAdmin shopAdmin){
        ShopAdminDto dto = new ShopAdminDto();
        dto.id = shopAdmin.getId();
        dto.address = shopAdmin.getAddress();
        dto.email = shopAdmin.getEmail();
        dto.firstName = shopAdmin.getFirstName();
        dto.lastName = shopAdmin.getLastName();
        dto.phoneNumber = shopAdmin.getPhoneNumber();
        dto.password = shopAdmin.getPassword();
        return dto;
    }
    public ShopAdmin toEntity(ShopAdminDto dto) {
        ShopAdmin shopAdmin = new ShopAdmin();
        shopAdmin.setId(dto.id);
        shopAdmin.setAddress(dto.address);
        shopAdmin.setEmail(dto.email);
        shopAdmin.setFirstName(dto.firstName);
        shopAdmin.setLastName(dto.lastName);
        shopAdmin.setPhoneNumber(dto.phoneNumber);
        shopAdmin.setPassword(dto.password);
        return shopAdmin;
    }
    public ShopAdminDto findShopAdminById(String id) {
        return toDto(repository.findById(Integer.parseInt(id)).get());
    }
    public List<ShopAdminDto> findAll() {
        if (repository.findAll().isEmpty()){
            throw new NoSuchElementException("There is no Shop Admin");
        }
        List<ShopAdminDto> shopAdminDtoList = repository.findAll().stream().map(this::toDto).toList();
        return shopAdminDtoList;
    }
    public ShopAdminDto create(ShopAdminDto shopAdminDto) {
        if (repository.existsByEmail(shopAdminDto.email)){
            throw new EmailHasBeenTakenAlreadyException("Email is already in use by another Shop Admin.");
        }
        return toDto(repository.save(toEntity(shopAdminDto)));
    }
    public ShopAdminDto update(String id, ShopAdminDto shopAdminDto) {
        ShopAdmin shopAdmin = repository.findById(Integer.parseInt(id)).get();
        shopAdmin.setFirstName(shopAdminDto.firstName);
        shopAdmin.setLastName(shopAdminDto.lastName);
        shopAdmin.setPhoneNumber(shopAdminDto.phoneNumber);
        shopAdmin.setEmail(shopAdminDto.email);
        shopAdmin.setPassword(shopAdminDto.password);
        shopAdmin.setAddress(shopAdminDto.address);
        return toDto(repository.save(shopAdmin));
    }
    public String delete(String id){
        repository.deleteById(Integer.parseInt(id));
        return "Shop Admin with id " + id + " is deleted";
    }
    public ShopAdminDto login(LoginRequest loginRequest) {
        ShopAdminDto shopAdminDto = toDto(repository.findByEmailAndPassword(loginRequest.getEmail(),loginRequest.getPassword()));
        return shopAdminDto;
    }


}
