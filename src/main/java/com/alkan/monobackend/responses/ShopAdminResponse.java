package com.alkan.monobackend.responses;

import com.alkan.monobackend.dtos.ShopAdminDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ShopAdminResponse extends BaseResponse{

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public ShopAdminDto data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<ShopAdminDto> dataList;

    public ShopAdminResponse(int code, String message, ShopAdminDto data) {
        super(code, message);
        this.data = data;
    }
    public ShopAdminResponse(int code, String message, List<ShopAdminDto> dataList) {
        super(code, message);
        this.dataList = dataList;
    }
}
