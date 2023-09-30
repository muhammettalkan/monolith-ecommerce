package com.alkan.monobackend.services;

import com.alkan.monobackend.dtos.CustomerDto;
import com.alkan.monobackend.entities.Customer;
import com.alkan.monobackend.repositories.CustomerRepository;
import com.alkan.monobackend.responses.CustomerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public CustomerDto toDto(Customer customer) {
        CustomerDto dto = new CustomerDto();
        dto.firstName = customer.getFirstName();
        dto.lastName = customer.getLastName();
        dto.address = customer.getAddress();
        dto.phoneNumber = customer.getPhoneNumber();
        dto.email = customer.getEmail();
        dto.password = customer.getPassword();
        return dto;
    }

    public Customer toEntity(CustomerDto dto){
        Customer customer = new Customer();
        customer.setFirstName(dto.firstName);
        customer.setLastName(dto.lastName);
        customer.setAddress(dto.address);
        customer.setPhoneNumber(dto.phoneNumber);
        customer.setEmail(dto.email);
        customer.setPassword(dto.password);
        return customer;
    }

    public CustomerDto findById(int id){
        return toDto(repository.findById(id).get());
    }
    public ResponseEntity<Object> register(CustomerDto customerDto) {
        CustomerResponse response = new CustomerResponse();
        repository.save(toEntity(customerDto));
        return response.responseBuilder("Registered successfully", HttpStatus.CREATED, customerDto);
    }

    public ResponseEntity<Object> findAll(){
        CustomerResponse response = new CustomerResponse();
        List<CustomerDto> customerDtoList = repository.findAll().stream().map(this::toDto).toList();
        return response.responseBuilder("Customers found", HttpStatus.OK, customerDtoList);
    }
    public ResponseEntity<Object> find(int id){
        CustomerResponse response = new CustomerResponse();
        CustomerDto customerDto = toDto(repository.findById(id).get());
        return response.responseBuilder("Customer found", HttpStatus.OK, customerDto);
    }

    public ResponseEntity<Object> updateProfile(int id, CustomerDto customerDto){
        Customer customer = repository.findById(id).get();
        customer.setFirstName(customerDto.firstName);
        customer.setLastName(customerDto.lastName);
        customer.setAddress(customerDto.address);
        customer.setPhoneNumber(customerDto.phoneNumber);
        customer.setEmail(customerDto.email);
        customer.setPassword(customerDto.password);
        repository.save(customer);
        CustomerResponse response = new CustomerResponse();
        return response.responseBuilder("Customer updated", HttpStatus.OK, toDto(customer));
    }

    public String delete(int id){
        repository.deleteById(id);
        return "Customer is deleted";
    }


}
