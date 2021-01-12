package com.servbyte.ecommerce.dtos;

import lombok.Data;


@Data
public class CartDto {
    private Long restaurantMenuId;
    private Long user_id;
    private int quantity;
}
