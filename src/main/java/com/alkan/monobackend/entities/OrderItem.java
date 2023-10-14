package com.alkan.monobackend.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    private BasketProduct basketProduct;
    private boolean isGivenCargo = false;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "shop_id")
    private Shop shop;

    public OrderItem() {
    }
    public OrderItem(int id, BasketProduct basketProduct, boolean isGivenCargo, Shop shop, Order order) {
        this.id = id;
        this.basketProduct = basketProduct;
        this.isGivenCargo = isGivenCargo;
        this.shop = shop;
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BasketProduct getBasketProduct() {
        return basketProduct;
    }

    public void setBasketProduct(BasketProduct basketProduct) {
        this.basketProduct = basketProduct;
    }

    public boolean isGivenCargo() {
        return isGivenCargo;
    }

    public void setGivenCargo(boolean givenCargo) {
        isGivenCargo = givenCargo;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
