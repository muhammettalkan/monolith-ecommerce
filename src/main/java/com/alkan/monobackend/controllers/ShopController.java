package com.alkan.monobackend.controllers;

import com.alkan.monobackend.dtos.ShopDto;
import com.alkan.monobackend.responses.ShopResponse;
import com.alkan.monobackend.services.ShopService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shop")
public class ShopController {

    private ShopService service;

    public ShopController(ShopService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ShopResponse create(@RequestBody ShopDto shopDto){
        return service.create(shopDto);
    }
    @GetMapping("/findAll")
    public List<ShopDto> findAll(){
        return service.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        return service.delete(id);
    }
}
