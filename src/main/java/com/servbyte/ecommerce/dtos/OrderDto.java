package com.servbyte.ecommerce.dtos;

import lombok.Data;

import java.util.List;


@Data
public class OrderDto {
    private long menuId;
    private long userId;
    private long restaurantId;
    private String deliveryCompany;
    private List<CartDto> cartDtos;
}
