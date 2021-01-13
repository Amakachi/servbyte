package com.servbyte.ecommerce.entities;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "restaurant_menus")
public class RestaurantMenu extends AbstractEntity {
    private String name;
    private double price;
    private int preparationTimeMinute;
    @ElementCollection
    private List<String> pictures;
    private String description;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Restaurant restaurant;
}
