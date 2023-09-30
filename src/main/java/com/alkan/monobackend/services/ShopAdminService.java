package com.alkan.monobackend.services;

import com.alkan.monobackend.dtos.ShopAdminDto;
import com.alkan.monobackend.entities.ShopAdmin;
import com.alkan.monobackend.repositories.ShopAdminRepository;
import com.alkan.monobackend.request.LoginRequest;
import com.alkan.monobackend.responses.ShopAdminResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ShopAdminService {

    private final ShopAdminRepository repository;
    public ShopAdminService(ShopAdminRepository repository) {
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

    public ShopAdminDto findShopAdminById(int id) {
        return toDto(repository.findById(id).get());
    }

    public ResponseEntity<Object> find(int id) {
        ShopAdminResponse response = new ShopAdminResponse();
        return response.responseBuilder("Shop Admin Found", HttpStatus.OK,findShopAdminById(id));
    }

    public ResponseEntity<Object> create(ShopAdminDto shopAdminDto) {
        ShopAdmin shopAdmin = repository.save(toEntity(shopAdminDto));
        ShopAdminResponse shopAdminResponse = new ShopAdminResponse();
        return shopAdminResponse.responseBuilder("Shop Admin Created", HttpStatus.CREATED, toDto(shopAdmin));
    }
    public ResponseEntity<Object> update(int id, ShopAdminDto shopAdminDto) {
        ShopAdmin shopAdmin = repository.findById(id).get();
        shopAdmin.setFirstName(shopAdminDto.firstName);
        shopAdmin.setLastName(shopAdminDto.lastName);
        shopAdmin.setPhoneNumber(shopAdminDto.phoneNumber);
        shopAdmin.setEmail(shopAdminDto.email);
        shopAdmin.setPassword(shopAdminDto.password);
        shopAdmin.setAddress(shopAdminDto.address);
        repository.save(shopAdmin);
        ShopAdminResponse shopAdminResponse = new ShopAdminResponse();
        return shopAdminResponse.responseBuilder("Shop Admin Updated", HttpStatus.OK, toDto(shopAdmin));
    }

    public String delete(int id){
        repository.deleteById(id);
        return "Shop Admin with id " + id + " is deleted";
    }

    public ResponseEntity<Object> login(LoginRequest loginRequest) {
        ShopAdmin shopAdmin = repository.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
        ShopAdminResponse shopAdminResponse = new ShopAdminResponse();
        if (shopAdmin == null) {
            return shopAdminResponse.responseBuilder("Invalid Email or Password", HttpStatus.BAD_REQUEST, null);
        }
        return shopAdminResponse.responseBuilder("Successfully Logged In", HttpStatus.OK, toDto(shopAdmin));
    }


}
