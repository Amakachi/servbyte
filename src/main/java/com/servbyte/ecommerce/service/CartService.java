package com.servbyte.ecommerce.service;

import com.servbyte.ecommerce.dtos.CartDto;

public interface CartService {
    String addCart(CartDto cartDto);
}
