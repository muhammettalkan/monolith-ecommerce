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
    public ResponseEntity<Object> findById(@PathVariable (value = "id") int id){
        return service.find(id);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody ShopAdminDto shopAdminDto){
        return service.create(shopAdminDto);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody ShopAdminDto shopAdminDto){
        return service.update(id, shopAdminDto);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        return service.delete(id);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest){
        return service.login(loginRequest);
    }

}
