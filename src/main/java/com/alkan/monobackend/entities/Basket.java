package com.alkan.monobackend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private double totalAmount;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonManagedReference
    private Customer customer;
    private boolean isOrdered = false;
    @JsonBackReference
    @OneToMany(mappedBy = "basket",cascade = CascadeType.ALL)
    @Nullable
    private List<BasketProduct> basketProductList;

    public Basket() {
    }

    public Basket(int id, double totalAmount, Customer customer, List<BasketProduct> basketProductList, int quantity, boolean isOrdered) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.customer = customer;
        this.basketProductList = basketProductList;
        this.quantity = quantity;
        this.isOrdered = isOrdered;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double basketAmount) {
        this.totalAmount = basketAmount;
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

    public boolean isOrdered() {
        return isOrdered;
    }

    public void setOrdered(boolean ordered) {
        isOrdered = ordered;
    }
}
