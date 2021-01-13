package com.servbyte.ecommerce.service.serviceImpl;

import com.servbyte.ecommerce.dtos.CartDto;
import com.servbyte.ecommerce.entities.ApplicationUser;
import com.servbyte.ecommerce.entities.Cart;
import com.servbyte.ecommerce.entities.RestaurantMenu;
import com.servbyte.ecommerce.repository.CartRepository;
import com.servbyte.ecommerce.repository.RestaurantMenuRepository;
import com.servbyte.ecommerce.service.CartService;
import com.servbyte.ecommerce.utility.AuthenticatedUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final RestaurantMenuRepository restaurantMenuRepository;

    public CartServiceImpl(CartRepository cartRepository, RestaurantMenuRepository restaurantMenuRepository) {
        this.cartRepository = cartRepository;
        this.restaurantMenuRepository = restaurantMenuRepository;
    }

    public String addCart(CartDto cartDto){
        Cart cart = new Cart();
        ApplicationUser user = AuthenticatedUser.getLoggedInUser();
        Optional<RestaurantMenu> restaurantMenu = restaurantMenuRepository.findById(cartDto.getRestaurantMenuId());
        cart.setApplicationUser(user);
        cart.setQuantity(cartDto.getQuantity());
        cart.setRestaurantMenu(restaurantMenu.get());
        return "Cart added successfully for " + user.getFirstName();
    }
}

