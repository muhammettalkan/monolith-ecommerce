package com.alkan.monobackend.request;

import java.io.Serializable;

public class UpdateCategoryRequest implements Serializable {

    public int id;
    public String name;
    public UpdateCategoryRequest() {
    }

    public UpdateCategoryRequest(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
