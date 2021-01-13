package com.servbyte.ecommerce.service;

import com.servbyte.ecommerce.entities.Cart;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    String createOrders(Long userId);
}
