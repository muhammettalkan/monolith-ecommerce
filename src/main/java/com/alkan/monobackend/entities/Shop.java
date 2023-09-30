package com.alkan.monobackend.entities;

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
    public Shop() {
    }

    public Shop(int id, String shopName, ShopAdmin admin) {
        this.id = id;
        this.shopName = shopName;
        this.admin = admin;
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
}
