package com.alkan.monobackend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shop_admin_id")
    private ShopAdmin admin;
    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<ShopCategory> categoryList;
    @JsonBackReference
    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    private List<Order> orderList;
    public Shop() {
    }

    public Shop(int id, String name, ShopAdmin admin, List<Order> orderList, List<ShopCategory> categoryList) {
        this.id = id;
        this.name = name;
        this.admin = admin;
        this.orderList = orderList;
        this.categoryList = categoryList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String shopName) {
        this.name = shopName;
    }

    public ShopAdmin getAdmin() {
        return admin;
    }

    public void setAdmin(ShopAdmin admin) {
        this.admin = admin;
    }

    public List<ShopCategory> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<ShopCategory> categoryList) {
        this.categoryList = categoryList;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
