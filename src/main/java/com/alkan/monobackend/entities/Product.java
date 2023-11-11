package com.alkan.monobackend.entities;

import javax.persistence.*;

import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private double price;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shop_category_id")
    private ShopCategory shopCategory;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<BasketProduct> basketProductList;
    public Product() {
    }
    public Product(int id, String name, double price, ShopCategory shopCategory, List<BasketProduct> basketProductList) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.shopCategory = shopCategory;
        this.basketProductList = basketProductList;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ShopCategory getShopCategory() {
        return shopCategory;
    }

    public void setShopCategory(ShopCategory category) {
        this.shopCategory = category;
    }

    public List<BasketProduct> getBasketProductList() {
        return basketProductList;
    }

    public void setBasketProductList(List<BasketProduct> basketProductList) {
        this.basketProductList = basketProductList;
    }
}
