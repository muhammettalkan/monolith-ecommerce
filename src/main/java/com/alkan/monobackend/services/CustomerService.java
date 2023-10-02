package com.alkan.monobackend.services;

import com.alkan.monobackend.dtos.CustomerDto;
import com.alkan.monobackend.entities.Customer;

import java.util.List;

public interface CustomerService {

    CustomerDto toDto(Customer customer);
    Customer toEntity(CustomerDto dto);
    CustomerDto findById(String id);
    CustomerDto register(CustomerDto customerDto);
    List<CustomerDto> findAll();
    CustomerDto updateProfile(String id, CustomerDto customerDto);
    String delete(String id);
}
