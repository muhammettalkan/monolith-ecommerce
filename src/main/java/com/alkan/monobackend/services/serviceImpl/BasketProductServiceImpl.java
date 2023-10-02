package com.alkan.monobackend.services.serviceImpl;

import com.alkan.monobackend.dtos.BasketProductDto;
import com.alkan.monobackend.entities.BasketProduct;
import com.alkan.monobackend.repositories.BasketProductRepository;
import com.alkan.monobackend.request.AddBasketProductToBasketRequest;
import com.alkan.monobackend.services.BasketProductService;
import com.alkan.monobackend.services.BasketService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BasketProductServiceImpl implements BasketProductService {

    private BasketProductRepository repository;
    private BasketService basketService;
    private ProductServiceImpl productServiceImpl;
    public BasketProductServiceImpl(BasketProductRepository repository, @Lazy BasketServiceImpl basketService, @Lazy ProductServiceImpl productServiceImpl) {
        this.repository = repository;
        this.basketService = basketService;
        this.productServiceImpl = productServiceImpl;
    }

    public BasketProduct mapToEntity(BasketProductDto basketProductDto){
        BasketProduct basketProduct = new BasketProduct();
        basketProduct.setId(basketProductDto.id);
        basketProduct.setQuantity(basketProductDto.quantity);
        basketProduct.setBasket(basketService.mapDtoToEntity(basketService.findById(String.valueOf(basketProductDto.basketId))));
        basketProduct.setProduct(productServiceImpl.findById(basketProductDto.productId));
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
    public BasketProductDto addToBasket(AddBasketProductToBasketRequest request){
        BasketProduct basketProduct = new BasketProduct();
        basketProduct.setQuantity(request.quantity);
        basketProduct.setProduct(productServiceImpl.findById(request.productId));
        basketProduct.setAmount(calculateBasketProductAmount(basketProduct.getProduct().getPrice(), basketProduct.getQuantity()));
        basketProduct.setBasket(basketService.mapDtoToEntity(basketService.findById(String.valueOf(request.basketId))));
        return mapToDto(repository.save(basketProduct));
    }
    public String delete(String id){
        if (!repository.existsById(Integer.parseInt(id))){
            return "BasketProduct not found";
        }
        repository.deleteById(Integer.parseInt(id));
        return "BasketProduct deleted";
    }
    public List<BasketProductDto> list(){
        if (repository.findAll().isEmpty()){
            throw new NoSuchElementException("There is no basketProduct");
        }
        return repository.findAll().stream().map(this::mapToDto).toList();
    }
    public BasketProductDto findById(String id){
        if (!repository.existsById(Integer.parseInt(id))){
            throw new NoSuchElementException("BasketProduct with this id is not found");
        }
        return mapToDto(repository.findById(Integer.parseInt(id)).get());
    }
    public double calculateBasketProductAmount(double price, int quantity){
        return price * quantity;
    }
    public List<BasketProductDto> listBasketProductsByBasketId(String id) {
        if (repository.findBasketProductsByBasket_Id(Integer.parseInt(id)).isEmpty()){
            throw new NoSuchElementException("There is no basketProduct");
        }
        return repository.findBasketProductsByBasket_Id(Integer.parseInt(id)).stream().map(this::mapToDto).toList();
    }
}
