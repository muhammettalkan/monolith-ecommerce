package com.alkan.monobackend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String shopName;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shop_admin_id")
    private ShopAdmin admin;
    @ManyToMany
    @JoinTable(
            name = "shop_category",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "shop_id"))
    private List<Category> categoryList;
    @JsonBackReference
    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    private List<OrderItem> orderItemList;
    public Shop() {
    }

    public Shop(int id, String shopName, ShopAdmin admin, List<Category> categoryList, List<OrderItem> orderItemList) {
        this.id = id;
        this.shopName = shopName;
        this.admin = admin;
        this.categoryList = categoryList;
        this.orderItemList = orderItemList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public ShopAdmin getAdmin() {
        return admin;
    }

    public void setAdmin(ShopAdmin admin) {
        this.admin = admin;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }
}
