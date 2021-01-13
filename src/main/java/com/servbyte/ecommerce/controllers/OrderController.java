package com.servbyte.ecommerce.controllers;

import com.servbyte.ecommerce.dtos.CartDto;
import com.servbyte.ecommerce.dtos.OrderDto;
import com.servbyte.ecommerce.service.CartService;
import com.servbyte.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;
    private CartService cartService;

    @Autowired
    public OrderController(OrderService orderService, CartService cartService) {
        this.orderService = orderService;
        this.cartService = cartService;

    }

    @PostMapping("/create")
    public ResponseEntity<?> createOrders(@RequestParam Long userId){
        return ResponseEntity.ok(orderService.createOrders(userId));
    }

    @PostMapping("/add-cart")
    public ResponseEntity<?> addCart(@RequestBody CartDto cartDto){
        return ResponseEntity.ok(cartService.addCart(cartDto));
    }


}
