package com.alkan.monobackend.services;

import com.alkan.monobackend.dtos.BasketDto;
import com.alkan.monobackend.dtos.BasketProductDto;
import com.alkan.monobackend.entities.BasketProduct;
import com.alkan.monobackend.repositories.BasketProductRepository;
import com.alkan.monobackend.request.AddBasketProductToBasketRequest;
import com.alkan.monobackend.responses.BasketProductResponse;
import com.alkan.monobackend.responses.BasketResponse;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketProductService {

    private BasketProductRepository repository;
    private BasketService basketService;
    private ProductService productService;
    public BasketProductService(BasketProductRepository repository, @Lazy BasketService basketService,@Lazy ProductService productService) {
        this.repository = repository;
        this.basketService = basketService;
        this.productService = productService;
    }

    public BasketProduct mapToEntity(BasketProductDto basketProductDto){
        BasketProduct basketProduct = new BasketProduct();
        basketProduct.setId(basketProductDto.id);
        basketProduct.setQuantity(basketProductDto.quantity);
        basketProduct.setBasket(basketService.findById(basketProductDto.basketId));
        basketProduct.setProduct(productService.findById(basketProductDto.productId));
        basketProduct.setAmount(basketProductDto.amount);
        return basketProduct;
    }

    public BasketProductDto mapToDto(BasketProduct basketProduct){
        BasketProductDto basketProductDto = new BasketProductDto();
        basketProductDto.id = basketProduct.getId();
        basketProductDto.quantity = basketProduct.getQuantity();
        basketProductDto.basketId = basketProduct.getBasket().getId();
        basketProductDto.productId = basketProduct.getProduct().getId();
        basketProductDto.amount = basketProduct.getAmount();
        return basketProductDto;
    }
    /*public ResponseEntity<Object> addToBasket(AddBasketProductToBasketRequest request){
        BasketProduct basketProduct = new BasketProduct();
        basketProduct.setQuantity(request.quantity);
        basketProduct.setProduct(productService.findById(request.productId));
        basketProduct.setAmount(calculateBasketProductAmount(basketProduct.getProduct().getPrice(), basketProduct.getQuantity()));
        basketProduct.setBasket(basketService.findById(request.basketId));
        repository.save(basketProduct);
        BasketProductResponse basketProductResponse = new BasketProductResponse();
        return basketProductResponse.responseBuilder("BasketProduct added to basket", HttpStatus.OK, basketProduct);
    }*/

    /*public String delete(int id){
        repository.deleteById(id);
        return "BasketProduct deleted";
    }*/

    /*public List<BasketProduct> list(){
        return repository.findAll();
    }*/

    public BasketProductDto findById(int id){ //GET
        return mapToDto(repository.findById(id).get());
    }

    public double calculateBasketProductAmount(double price, int quantity){
        return price * quantity;
    }

//    public List<BasketProduct> getBasketProductsByBasketId(int id) {
//        return repository.findBasketProductsByBasket_Id(id);
//    }

}
