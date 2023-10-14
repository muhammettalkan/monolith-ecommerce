package com.alkan.monobackend.services.serviceImpl;

import com.alkan.monobackend.dtos.BasketDto;
import com.alkan.monobackend.entities.Basket;
import com.alkan.monobackend.entities.BasketProduct;
import com.alkan.monobackend.exception.custom.ObjectNotFoundException;
import com.alkan.monobackend.repositories.BasketRepository;
import com.alkan.monobackend.request.AddBasketProductToBasketRequest;
import com.alkan.monobackend.request.UpdateBasketProductRequest;
import com.alkan.monobackend.services.BasketProductService;
import com.alkan.monobackend.services.BasketService;
import com.alkan.monobackend.services.CustomerService;
import com.alkan.monobackend.services.ProductService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BasketServiceImpl implements BasketService {

    private final BasketRepository repository;
    private final CustomerService customerService;
    private final BasketProductService basketProductService;
    private final ProductService productService;

    public BasketServiceImpl(BasketRepository repository, CustomerService customerService, @Lazy BasketProductService basketProductService, ProductService productService) {
        this.repository = repository;
        this.customerService = customerService;
        this.basketProductService = basketProductService;
        this.productService = productService;
    }

    public Basket findBasketById(int id) {
        return repository.findById(id).get();
    }

    public Basket mapDtoToEntity(BasketDto basketDto) {
        Basket basket = new Basket();
        basket.setId(basketDto.id);
        basket.setTotalAmount(basketDto.totalAmount);
        basket.setCustomer(customerService.toEntity(customerService.findById(String.valueOf(basketDto.customerId))));
        basket.setOrdered(basketDto.isOrdered);
        basket.setQuantity(basketDto.quantity);
        if (basketDto.basketProductDtoList != null) {
            basket.setBasketProductList(basketProductService.listBasketProductsByBasketId(String.valueOf(basketDto.id))
                    .stream()
                    .map(basketProductService::mapToEntity)
                    .toList());
        }else basket.setBasketProductList(null);
        return basket;
    }
    public BasketDto mapEntityToDto(Basket basket) {
        BasketDto basketDto = new BasketDto();
        basketDto.id = basket.getId();
        basketDto.totalAmount = basket.getTotalAmount();
        basketDto.customerId = basket.getCustomer().getId();
        if (basket.getBasketProductList() != null) {
            basketDto.basketProductDtoList = basketProductService.listBasketProductsByBasketId(String.valueOf(basket.getId()));
        }else basketDto.basketProductDtoList = null;
        basketDto.isOrdered = basket.isOrdered();
        basketDto.quantity = basket.getQuantity();
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
            throw new ObjectNotFoundException("Basket not found");
        }
        return mapEntityToDto(repository.findById(Integer.parseInt(basketId)).get());
    }
    public double calculateTotalAmount(int basketId){
        Basket basket = findBasketById(basketId);
        double totalAmount = 0;
        for (BasketProduct basketProduct: basket.getBasketProductList()) {
            totalAmount += basketProduct.getAmount();
        }
        return totalAmount;
    }
    public BasketDto update(UpdateBasketProductRequest request){
        BasketProduct basketProduct = basketProductService.findBasketProductById(request.basketProductId);
        basketProduct.setQuantity(request.quantity);
        basketProduct.setAmount(basketProductService.calculateBasketProductAmount(basketProduct.getProduct().getPrice(), request.quantity));
        Basket basket = findBasketById(showCurrentBasket(String.valueOf(request.customerId)).id);
        basket.setTotalAmount(calculateTotalAmount(basket.getId()));
        return mapEntityToDto(repository.save(basket));
    }

    public BasketDto addBasketProduct(AddBasketProductToBasketRequest request){
        Basket basket = findBasketById(request.basketId);
        BasketProduct basketProduct = new BasketProduct();
        basketProduct.setBasket(basket);
        basketProduct.setProduct(productService.findProductById(request.productId));
        basketProduct.setQuantity(request.quantity);
        basketProduct.setAmount(basketProductService.calculateBasketProductAmount(basketProduct.getProduct().getPrice(), request.quantity));
        basket.getBasketProductList().add(basketProduct);
        basket.setQuantity(basket.getQuantity() + request.quantity);
        basket.setTotalAmount(calculateTotalAmount(request.basketId));
        repository.save(basket);
        return mapEntityToDto(basket);
    }

    public List<BasketDto> basketHistory(String customerId){
        return repository.findAllByCustomer_Id(Integer.parseInt(customerId)).stream().map(this::mapEntityToDto).toList();
    }
    public BasketDto showCurrentBasket(String customerId){
        List<Basket> basketList = repository.findAllByCustomer_Id(Integer.parseInt(customerId));
        for (Basket basket: basketList) {
            if (!basket.isOrdered()){
                return mapEntityToDto(basket);
            }
        }
        throw new ObjectNotFoundException("There is no current basket");
    }

    @Override
    public BasketDto increaseProductQuantityByOne(int basketProductId){
        BasketProduct basketProduct = basketProductService.findBasketProductById(basketProductId);
        basketProduct.setQuantity(basketProduct.getQuantity() + 1);
        basketProduct.setAmount(basketProductService.calculateBasketProductAmount(basketProduct.getProduct().getPrice(), basketProduct.getQuantity()));
        Basket basket = findBasketById(basketProduct.getBasket().getId());
        basket.setTotalAmount(calculateTotalAmount(basket.getId()));
        return mapEntityToDto(repository.save(basket));
    }
    @Override
    public BasketDto decreaseProductQuantityByOne(int basketProductId){
        BasketProduct basketProduct = basketProductService.findBasketProductById(basketProductId);
        if (basketProduct.getQuantity() == 1){
            basketProductService.delete(String.valueOf(basketProductId));
            return mapEntityToDto(findBasketById(basketProduct.getBasket().getId()));
        }
        basketProduct.setQuantity(basketProduct.getQuantity() - 1);
        basketProduct.setAmount(basketProductService.calculateBasketProductAmount(basketProduct.getProduct().getPrice(), basketProduct.getQuantity()));
        Basket basket = findBasketById(basketProduct.getBasket().getId());
        basket.setTotalAmount(calculateTotalAmount(basket.getId()));
        return mapEntityToDto(repository.save(basket));
    }

    public String order(int id){
        Basket basket = findBasketById(id);
        basket.setOrdered(true);
        repository.save(basket);
        return "Order created";
    }

}
