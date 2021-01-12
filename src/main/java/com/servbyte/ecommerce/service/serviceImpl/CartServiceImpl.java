package com.servbyte.ecommerce.service.serviceImpl;

import com.servbyte.ecommerce.dtos.CartDto;
import com.servbyte.ecommerce.entities.ApplicationUser;
import com.servbyte.ecommerce.entities.Cart;
import com.servbyte.ecommerce.entities.RestaurantMenu;
import com.servbyte.ecommerce.repository.CartRepository;
import com.servbyte.ecommerce.repository.RestaurantMenuRepository;
import com.servbyte.ecommerce.utility.AuthenticatedUser;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl {
    private final CartRepository cartRepository;
    private final RestaurantMenuRepository restaurantMenuRepository;

    public CartServiceImpl(CartRepository cartRepository, RestaurantMenuRepository restaurantMenuRepository) {
        this.cartRepository = cartRepository;
        this.restaurantMenuRepository = restaurantMenuRepository;
    }

    private void addCart(CartDto cartDto){
        Cart cart = new Cart();
        ApplicationUser user = AuthenticatedUser.getLoggedInUser();
        Optional<RestaurantMenu> restaurantMenu = restaurantMenuRepository.findById(cartDto.getRestaurantMenuId());
        cart.setApplicationUser(user);
        cart.setQuantity(cartDto.getQuantity());
        cart.setRestaurantMenu(restaurantMenu.get());
    }
}

