package com.alkan.monobackend.controllers;

import com.alkan.monobackend.dtos.BasketDto;
import com.alkan.monobackend.entities.Basket;
import com.alkan.monobackend.request.AddBasketProductToBasketRequest;
import com.alkan.monobackend.responses.BasketResponse;
import com.alkan.monobackend.services.BasketProductService;
import com.alkan.monobackend.services.BasketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



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
    public String create(@PathVariable int customerId){
        return basketService.create(customerId);
    }

//    @PostMapping("/addbasket")
//    public ResponseEntity<Object> addToBasket(@RequestBody AddBasketProductToBasketRequest request){
//        return basketProductService.addToBasket(request);
//    }
//    @DeleteMapping("/deletebasketproduct/{id}")
//    public String deleteBasketProduct(@PathVariable int id){
//        return basketProductService.delete(id);
//    }
//
//    @GetMapping("/{id}")
//    public Basket getBasket(@PathVariable int id){
//        return basketService.findById(id);
//    }
//
//    @PostMapping("/update")
//    public BasketResponse update(@RequestBody BasketDto basketDto){
//        return basketProductService.updateBasket(basketDto);
//    }


}
