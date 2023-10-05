package com.alkan.monobackend.services.serviceImpl;

import com.alkan.monobackend.dtos.CustomerDto;
import com.alkan.monobackend.entities.Customer;
import com.alkan.monobackend.exception.custom.EmailHasBeenTakenAlreadyException;
import com.alkan.monobackend.repositories.CustomerRepository;
import com.alkan.monobackend.services.BasketService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CustomerServiceImpl implements com.alkan.monobackend.services.CustomerService {

    private final CustomerRepository repository;
    private final BasketService basketService;
    public CustomerServiceImpl(CustomerRepository repository,@Lazy BasketService basketService) {
        this.repository = repository;
        this.basketService = basketService;
    }
    public CustomerDto toDto(Customer customer) {
        CustomerDto dto = new CustomerDto();
        dto.id = customer.getId();
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
        customer.setId(dto.id);
        customer.setFirstName(dto.firstName);
        customer.setLastName(dto.lastName);
        customer.setAddress(dto.address);
        customer.setPhoneNumber(dto.phoneNumber);
        customer.setEmail(dto.email);
        customer.setPassword(dto.password);
        return customer;
    }
    public CustomerDto findById(String id){
        return toDto(repository.findById(Integer.parseInt(id)).get());
    }
    public CustomerDto register(CustomerDto customerDto) {
        if (repository.existsCustomerByEmail(customerDto.email)){
            throw new EmailHasBeenTakenAlreadyException("This email has been taken already");
        }
        Customer customer = toEntity(customerDto);
        repository.save(customer);
        return toDto(customer);
    }
    public List<CustomerDto> findAll(){
        if (repository.findAll().isEmpty()){
            throw new NoSuchElementException("There is nothing in the database");
        }
        List<CustomerDto> customerDtoList = repository.findAll().stream().map(this::toDto).toList();
        return customerDtoList;
    }
    public CustomerDto updateProfile(String id, CustomerDto customerDto){Customer customer = repository.findById(Integer.parseInt(id)).get();
        customer.setFirstName(customerDto.firstName);
        customer.setLastName(customerDto.lastName);
        customer.setAddress(customerDto.address);
        customer.setPhoneNumber(customerDto.phoneNumber);
        customer.setEmail(customerDto.email);
        customer.setPassword(customerDto.password);
        repository.save(customer);
        return toDto(customer);
    }

    public String delete(String id) {
        repository.deleteById(Integer.parseInt(id));
        return "Customer is deleted";
    }


}
