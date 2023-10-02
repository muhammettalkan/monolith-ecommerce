package com.alkan.monobackend.controllers;

import com.alkan.monobackend.dtos.BasketDto;
import com.alkan.monobackend.dtos.BasketProductDto;
import com.alkan.monobackend.request.AddBasketProductToBasketRequest;
import com.alkan.monobackend.responses.BasketProductResponse;
import com.alkan.monobackend.responses.BasketResponse;
import com.alkan.monobackend.services.BasketProductService;
import com.alkan.monobackend.services.BasketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/basket")
public class BasketController {

    private BasketService basketService;
    private BasketProductService basketProductService;

    public BasketController(BasketService basketService, BasketProductService basketProductService) {
        this.basketService = basketService;
        this.basketProductService = basketProductService;
    }

    @PostMapping("/create/{customerId}")
    public ResponseEntity<BasketResponse> create(@PathVariable String customerId){
        return ResponseEntity.ok(new BasketResponse(2000,"Basket Created",basketService.create(customerId)));
    }

    @PostMapping("/addbasket")
    public ResponseEntity<BasketProductResponse> addToBasket(@RequestBody AddBasketProductToBasketRequest request){
        return ResponseEntity.ok(new BasketProductResponse(2000, "Product added to basket", basketProductService.addToBasket(request)));
    }
    @DeleteMapping("/deletebasketproduct/{id}")
    public String deleteBasketProduct(@PathVariable String id){
        return basketProductService.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BasketDto> getBasket(@PathVariable String id){
        return ResponseEntity.ok(basketService.findById(id));
    }

    @PostMapping("/update")
    public ResponseEntity<BasketResponse> update(@PathVariable String id,@RequestBody BasketDto basketDto){
        return ResponseEntity.ok(new BasketResponse(2000, "Basket updated", basketService.update(id,basketDto)));
    }


}
