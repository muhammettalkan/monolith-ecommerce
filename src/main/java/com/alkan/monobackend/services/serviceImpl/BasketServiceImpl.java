package com.alkan.monobackend.services.serviceImpl;

import com.alkan.monobackend.dtos.BasketDto;
import com.alkan.monobackend.entities.Basket;
import com.alkan.monobackend.repositories.BasketRepository;
import com.alkan.monobackend.services.BasketProductService;
import com.alkan.monobackend.services.BasketService;
import com.alkan.monobackend.services.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BasketServiceImpl implements BasketService {

    private final BasketRepository repository;
    private final CustomerService customerService;
    private final BasketProductService basketProductService;

    public BasketServiceImpl(BasketRepository repository, CustomerService customerService, BasketProductService basketProductService) {
        this.repository = repository;
        this.customerService = customerService;
        this.basketProductService = basketProductService;
    }

    public Basket mapDtoToEntity(BasketDto basketDto) {
        Basket basket = new Basket();
        basket.setId(basketDto.id);
        basket.setBasketAmount(basketDto.totalAmount);
        basket.setCustomer(customerService.toEntity(customerService.findById(String.valueOf(basketDto.customerId))));
        basket.setBasketProductList(basketProductService.listBasketProductsByBasketId(String.valueOf(basketDto.id))
                .stream()
                .map(basketProductService::mapToEntity)
                .toList());
        return basket;
    }
    public BasketDto mapEntityToDto(Basket basket) {
        BasketDto basketDto = new BasketDto();
        basketDto.id = basket.getId();
        basketDto.totalAmount = calculateTotalAmount(String.valueOf(basket.getId()));
        basketDto.customerId = basket.getCustomer().getId();
        basketDto.basketProductDtoList = basketProductService.listBasketProductsByBasketId(String.valueOf(basket.getId()));
        return basketDto;
    }
    public BasketDto create(String customerId){
        Basket basket = new Basket();
        basket.setCustomer(customerService.toEntity(customerService.findById(customerId)));
        return mapEntityToDto(repository.save(basket));
    }
    public String delete(String id){
        if (!repository.existsById(Integer.parseInt(id))){
            return "Basket not found";
        }
        repository.deleteById(Integer.parseInt(id));
        return "Basket deleted";
    }

    public List<BasketDto> list() {
        if (repository.findAll().isEmpty()){
            throw new NoSuchElementException("There is no basket");
        }
        return repository.findAll().stream().map(this::mapEntityToDto).toList();
    }

    public BasketDto findById(String basketId) {
        if (!repository.existsById(Integer.parseInt(basketId))){
            throw new NoSuchElementException("Basket not found");
        }
        return mapEntityToDto(repository.findById(Integer.parseInt(basketId)));
    }
    public double calculateTotalAmount(String basketId){
        Basket basket = mapDtoToEntity(findById(basketId));
        double totalAmount = 0;
        for (int i = 0; i < basket.getBasketProductList().size(); i++) {
            totalAmount += basket.getBasketProductList().get(i).getProduct().getPrice() * basket.getBasketProductList().get(i).getQuantity();
        }
        return totalAmount;
    }
    public BasketDto update(String customerId,BasketDto basketDto){
        Basket basket = mapDtoToEntity(findById(String.valueOf(basketDto.id)));
        basket.setCustomer(customerService.toEntity(customerService.findById(customerId)));
        basket.setBasketAmount(calculateTotalAmount(String.valueOf(basket.getId())));
        return mapEntityToDto(repository.save(basket));
    }



}
