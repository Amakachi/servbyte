package com.servbyte.ecommerce.entities;

import com.servbyte.ecommerce.enums.OrderStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "restaurant_orders")
public class Order extends AbstractEntity {
    @NotBlank
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @DateTimeFormat
    private LocalDateTime deliveryTime;
    private String paymentReference;
    private double totalPrice;
    @ManyToOne
    private ApplicationUser applicationUser;
    @OneToMany
    @NotBlank
    private List<Cart> cart;
}
