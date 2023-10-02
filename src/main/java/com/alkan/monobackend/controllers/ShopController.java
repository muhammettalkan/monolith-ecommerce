package com.alkan.monobackend.controllers;

import com.alkan.monobackend.dtos.ShopDto;
import com.alkan.monobackend.responses.ShopResponse;
import com.alkan.monobackend.services.serviceImpl.ShopServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shop")
public class ShopController {

    private ShopServiceImpl service;

    public ShopController(ShopServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<ShopResponse> create(@RequestBody ShopDto shopDto){
        return ResponseEntity.ok(new ShopResponse(2000,"Shop created", service.create(shopDto)));
    }
    @GetMapping("/findAll")
    public ResponseEntity<ShopResponse> findAll(){
        return ResponseEntity.ok(new ShopResponse(2000,"Shops found", service.findAll()));
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        return service.delete(id);
    }
}
