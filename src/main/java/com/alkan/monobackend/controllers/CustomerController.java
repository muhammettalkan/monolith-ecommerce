package com.alkan.monobackend.controllers;

import com.alkan.monobackend.dtos.BasketProductDto;
import com.alkan.monobackend.dtos.CustomerDto;
import com.alkan.monobackend.entities.Customer;
import com.alkan.monobackend.responses.BasketProductResponse;
import com.alkan.monobackend.responses.CustomerResponse;
import com.alkan.monobackend.services.BasketProductService;
import com.alkan.monobackend.services.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService service;

    public CustomerController(CustomerService customerService) {
        this.service = customerService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody CustomerDto customerDto){
        return service.register(customerDto);
    }

    @GetMapping("/findall")
    public ResponseEntity<Object> list(){
        return service.findAll();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Object> find(@PathVariable int id){
        return service.find(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody CustomerDto customerDto){
        return service.updateProfile(id, customerDto);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        return service.delete(id);
    }


}
