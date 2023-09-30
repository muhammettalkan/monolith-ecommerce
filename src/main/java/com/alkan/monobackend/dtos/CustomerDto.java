package com.alkan.monobackend.dtos;

import java.io.Serializable;

public class CustomerDto implements Serializable {
    public String firstName;
    public String lastName;
    public String phoneNumber;
    public String email;
    public String password;
    public String address;

    public CustomerDto() {
    }
}
