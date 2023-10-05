package com.alkan.monobackend.controllers;

import com.alkan.monobackend.dtos.BasketDto;
import com.alkan.monobackend.request.AddBasketProductToBasketRequest;
import com.alkan.monobackend.responses.BasketResponse;
import com.alkan.monobackend.services.BasketProductService;
import com.alkan.monobackend.services.BasketService;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/baskets")
public class BasketController {

    private final BasketService service;
    private final BasketProductService basketProductService;

    public BasketController(BasketService service, @Lazy BasketProductService basketProductService) {
        this.service = service;
        this.basketProductService = basketProductService;
    }

    @PostMapping("/create/{customerId}")
    public ResponseEntity<BasketResponse> create(@PathVariable String customerId){
        return ResponseEntity.ok(new BasketResponse(2000,"Basket Created", service.create(customerId)));
    }

    @PostMapping("/addbasket")
    public ResponseEntity<BasketResponse> addToBasket(@RequestBody AddBasketProductToBasketRequest request){
        return ResponseEntity.ok(new BasketResponse(2000, "Product added to basket", service.addOrRemoveBasketProduct(request)));
    }
    @DeleteMapping("/deletebasketproduct/{id}")
    public String deleteBasketProduct(@PathVariable String id){
        return basketProductService.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BasketResponse> getBasket(@PathVariable String id){
        return ResponseEntity.ok(new BasketResponse(2000, "Basket found", service.findById(id)));
    }

    @PostMapping("/update")
    public ResponseEntity<BasketResponse> update(@PathVariable String id,@RequestBody BasketDto basketDto){
        return ResponseEntity.ok(new BasketResponse(2000, "Basket updated", service.update(id,basketDto)));
    }
    @GetMapping("/{customerId}/baskethistory")
    public ResponseEntity<BasketResponse> basketHistory(@PathVariable String customerId){
        return ResponseEntity.ok(new BasketResponse(2000, "Basket history", service.basketHistory(customerId)));
    }
    @GetMapping("/{customerId}/currentbasket")
    public ResponseEntity<BasketResponse> showCurrentBasket(@PathVariable String customerId){
        return ResponseEntity.ok(new BasketResponse(2000, "Current basket", service.showCurrentBasket(customerId)));
    }


}
