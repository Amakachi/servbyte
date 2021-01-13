package com.servbyte.ecommerce.dtos;

import com.servbyte.ecommerce.entities.Restaurant;
import lombok.Data;

import java.util.List;

@Data
public class RestaurantMenuDto {
    private String name;
    private double price;
    private int preparationTimeMinute;
    private List<String> pictures;
    private String description;

    public RestaurantMenuDto(String name, double price, int preparationTime, List<String> pictures, String description) {
        this.name = name;
        this.price = price;
        this.preparationTimeMinute = preparationTime;
        this.pictures = pictures;
        this.description = description;
    }
}
