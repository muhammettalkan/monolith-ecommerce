package com.alkan.monobackend.entities;

import javax.persistence.*;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<ShopCategory> shopCategoryList;

    public Category() {
    }

    public Category(int id, String name, List<ShopCategory> shopCategoryList) {
        this.id = id;
        this.name = name;
        this.shopCategoryList = shopCategoryList;
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

    public void setName(String name) {
        this.name = name;
    }

    public List<ShopCategory> getShopCategoryList() {
        return shopCategoryList;
    }

    public void setShopCategoryList(List<ShopCategory> shopCategoryList) {
        this.shopCategoryList = shopCategoryList;
    }
}
