package com.alkan.monobackend.request;

import java.io.Serializable;

public class CreateShopCategoryRequest implements Serializable {
    public int shopId;
    public int categoryId;

    public CreateShopCategoryRequest() {
    }
    public CreateShopCategoryRequest(int shopId, int categoryId) {
        this.shopId = shopId;
        this.categoryId = categoryId;
    }
}
