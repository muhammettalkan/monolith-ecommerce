package com.alkan.monobackend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private boolean isGivenCargo = false;
    private boolean isDelivered = false;
    @JsonBackReference
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem> orderItemList;
    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shop_id")
    private Shop shop;
    @OneToOne(cascade = CascadeType.ALL)
    private Basket basket;

    public Order() {
    }

    public Order(int id, boolean isGivenCargo, boolean isDelivered, Basket basket, List<OrderItem> orderItemList) {
        this.id = id;
        this.isGivenCargo = isGivenCargo;
        this.isDelivered = isDelivered;
        this.basket = basket;
        this.orderItemList = orderItemList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isGivenCargo() {
        return isGivenCargo;
    }

    public void setGivenCargo(boolean givenCargo) {
        isGivenCargo = givenCargo;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public void setDelivered(boolean delivered) {
        isDelivered = delivered;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
