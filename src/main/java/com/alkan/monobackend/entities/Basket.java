package com.alkan.monobackend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private double basketAmount;
    private int quantity;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @JsonBackReference
    @OneToMany(mappedBy = "basket",cascade = CascadeType.ALL)
    private List<BasketProduct> basketProductList;

    public Basket() {
    }

    public Basket(int id, double basketAmount, Customer customer, List<BasketProduct> basketProductList, int quantity) {
        this.id = id;
        this.basketAmount = basketAmount;
        this.customer = customer;
        this.basketProductList = basketProductList;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBasketAmount() {
        return basketAmount;
    }

    public void setBasketAmount(double basketAmount) {
        this.basketAmount = basketAmount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<BasketProduct> getBasketProductList() {
        return basketProductList;
    }

    public void setBasketProductList(List<BasketProduct> basketProductList) {
        this.basketProductList = basketProductList;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
