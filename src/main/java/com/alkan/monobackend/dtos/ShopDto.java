package com.alkan.monobackend.dtos;

import com.alkan.monobackend.entities.OrderItem;

import java.io.Serializable;
import java.util.List;

public class ShopDto implements Serializable {

    public int id;
    public String shopName;
    public int shopAdminId;
    public List<CategoryDto> categoryList;
    public List<OrderItemDto> orderItemList;

    public ShopDto() {
    }

    public ShopDto(int id, String shopName, int shopAdminId, List<CategoryDto> categoryList, List<OrderItemDto> orderItemList) {
        this.id = id;
        this.shopName = shopName;
        this.shopAdminId = shopAdminId;
        this.categoryList = categoryList;
        this.orderItemList = orderItemList;
    }
}
