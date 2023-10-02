package com.alkan.monobackend.controllers;

import com.alkan.monobackend.dtos.CustomerDto;
import com.alkan.monobackend.responses.CustomerResponse;
import com.alkan.monobackend.services.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService service;

    public CustomerController(CustomerService customerService) {
        this.service = customerService;
    }

    @PostMapping("/register")
    public ResponseEntity<CustomerResponse> register(@RequestBody CustomerDto customerDto){
        return ResponseEntity.ok(new CustomerResponse(2000,"Registered successfully",service.register(customerDto)));
    }

    @GetMapping("/findall")
    public ResponseEntity<CustomerResponse> list(){
        return ResponseEntity.ok(new CustomerResponse(2000, "Customers found", service.findAll()));
    }
//
    @GetMapping("/find/{id}")
    public ResponseEntity<CustomerResponse> find(@PathVariable String id){
        return ResponseEntity.ok(new CustomerResponse(2000, "Customer found", service.findById(id)));
    }
//
    @PutMapping("/update/{id}")
    public ResponseEntity<CustomerResponse> update(@PathVariable String id, @RequestBody CustomerDto customerDto){
        return ResponseEntity.ok(new CustomerResponse(2000, "Customer updated", service.updateProfile(id, customerDto)));
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable String id){
        return service.delete(id);
    }


}
