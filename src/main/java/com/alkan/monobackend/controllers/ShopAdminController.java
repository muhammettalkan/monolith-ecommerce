package com.alkan.monobackend.controllers;

import com.alkan.monobackend.dtos.ShopAdminDto;
import com.alkan.monobackend.request.LoginRequest;
import com.alkan.monobackend.responses.ShopAdminResponse;
import com.alkan.monobackend.services.ShopAdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shopadmin")
public class ShopAdminController {

    private ShopAdminService service;

    public ShopAdminController(ShopAdminService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShopAdminResponse> findById(@PathVariable (value = "id") String id){
        return ResponseEntity.ok(new ShopAdminResponse(2000,"Shop Admin Found",service.findShopAdminById(id)));
    }

    @PostMapping("/create")
    public ResponseEntity<ShopAdminResponse> create(@RequestBody ShopAdminDto shopAdminDto){
        return ResponseEntity.ok(new ShopAdminResponse(2000,"Shop Admin Created",service.create(shopAdminDto)));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ShopAdminResponse> update(@PathVariable String id, @RequestBody ShopAdminDto shopAdminDto){
        return ResponseEntity.ok(new ShopAdminResponse(2000,"Shop Admin Updated",service.update(id, shopAdminDto)));
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable String id){
        return service.delete(id);
    }

    @PostMapping("/login")
    public ResponseEntity<ShopAdminResponse> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(new ShopAdminResponse(2000,"Login Successful",service.login(loginRequest)));
    }

}
