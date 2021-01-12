package com.servbyte.ecommerce.entities;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;



@Entity
@Getter
@Setter
public class Cart extends AbstractEntity {

    @ManyToOne
    private RestaurantMenu restaurantMenu;
    @ManyToOne
    private ApplicationUser applicationUser;
    private int quantity;


}
