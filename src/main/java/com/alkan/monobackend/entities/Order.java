package com.alkan.monobackend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private boolean isGivenCargo = false;
    private boolean isDelivered = false;
    @OneToOne(cascade = CascadeType.ALL)
    private Basket basket;

    public Order() {
    }

    public Order(int id, boolean isGivenCargo, boolean isDelivered, Basket basket) {
        this.id = id;
        this.isGivenCargo = isGivenCargo;
        this.isDelivered = isDelivered;
        this.basket = basket;
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
}
