package com.alkan.monobackend.dtos;

import java.io.Serializable;
import java.util.List;

public class CustomerDto implements Serializable {
    public int id;
    public String firstName;
    public String lastName;
    public String phoneNumber;
    public String email;
    public String password;
    public String address;
    public List<BasketDto> basketList;

    public CustomerDto() {
    }

    public CustomerDto(int id, String firstName, String lastName, String phoneNumber, String email, String password, String address, List<BasketDto> basketList) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.address = address;
        this.basketList = basketList;
    }
}
