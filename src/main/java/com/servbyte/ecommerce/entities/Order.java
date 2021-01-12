package com.servbyte.ecommerce.entities;

import com.servbyte.ecommerce.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "restaurant_orders")
public class Order extends AbstractEntity {
    @NotBlank
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private int deliveryTime;
    private String paymentReference;
    private int quantity;
    @ManyToOne
    private ApplicationUser applicationUser;
    @OneToMany
    @NotBlank
    private List<Cart> cart;
}
