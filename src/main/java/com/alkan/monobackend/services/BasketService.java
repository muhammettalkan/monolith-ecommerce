package com.alkan.monobackend.services;

import com.alkan.monobackend.dtos.BasketDto;
import com.alkan.monobackend.entities.Basket;
import com.alkan.monobackend.repositories.BasketRepository;
import com.alkan.monobackend.responses.BasketResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketService {

    private final BasketRepository repository;
    private final CustomerService customerService;
    private final BasketProductService basketProductService;

    public BasketService(BasketRepository repository, CustomerService customerService, BasketProductService basketProductService) {
        this.repository = repository;
        this.customerService = customerService;
        this.basketProductService = basketProductService;
    }

    public Basket mapDtoToEntity(BasketDto basketDto) {
        Basket basket = new Basket();
        basket.setId(basketDto.id);
        basket.setBasketAmount(basketDto.totalAmount);
        basket.setCustomer(customerService.toEntity(customerService.findById(basketDto.customerId)));
        return basket;
    }

    public BasketDto mapEntityToDto(Basket basket) {
        BasketDto basketDto = new BasketDto();
        basketDto.id = basket.getId();
        basketDto.totalAmount = calculateTotalAmount(basket.getId());
        basketDto.customerId = basket.getCustomer().getId();
        return basketDto;
    }


    public String create(int customerId){
        Basket basket = new Basket();
        basket.setCustomer(customerService.toEntity(customerService.findById(customerId)));
        repository.save(basket);
        return "Basket created";
    }

    public String delete(int id){
        repository.deleteById(id);
        return "Basket deleted";
    }

    public List<Basket> list() {
        return repository.findAll();
    }

    public Basket findById(int basketId) {
        return repository.findById(basketId);
    }
    public double calculateTotalAmount(int basketId){
        Basket basket = findById(basketId);
        double totalAmount = 0;
        for (int i = 0; i < basket.getBasketProductList().size(); i++) {
            totalAmount += basket.getBasketProductList().get(i).getProduct().getPrice() * basket.getBasketProductList().get(i).getQuantity();
        }
        return totalAmount;
    }

//    public BasketResponse updateBasket(BasketDto basketDto){
//
//    }



}
