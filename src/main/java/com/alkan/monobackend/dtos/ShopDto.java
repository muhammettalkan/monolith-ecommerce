package com.alkan.monobackend.dtos;


import java.io.Serializable;
import java.util.List;

public class ShopDto implements Serializable {

    public int id;
    public String name;
    public int shopAdminId;
    public List<ShopCategoryDto> categoryList;
    public List<OrderDto> orderList;

    public ShopDto() {
    }

    public ShopDto(int id, String name, int shopAdminId, List<ShopCategoryDto> categoryList, List<OrderDto> orderList) {
        this.id = id;
        this.name = name;
        this.shopAdminId = shopAdminId;
        this.categoryList = categoryList;
        this.orderList = orderList;
    }
}
