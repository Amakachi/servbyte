package com.servbyte.ecommerce.service.serviceImpl;

import com.servbyte.ecommerce.entities.*;
import com.servbyte.ecommerce.enums.ApiErrorCodes;
import com.servbyte.ecommerce.exceptions.BadRequestException;
import com.servbyte.ecommerce.repository.CartRepository;
import com.servbyte.ecommerce.repository.OrderRepository;
import com.servbyte.ecommerce.repository.RestaurantMenuRepository;
import com.servbyte.ecommerce.repository.RestaurantRepository;
import com.servbyte.ecommerce.utility.AuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;


public class OrderServiceImpl {
    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;
    private final RestaurantMenuRepository restaurantMenuRepository;
    private final CartRepository cartRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, RestaurantRepository restaurantRepository, RestaurantMenuRepository restaurantMenuRepository, CartRepository cartRepository) {
        this.orderRepository = orderRepository;
        this.restaurantRepository = restaurantRepository;
        this.restaurantMenuRepository = restaurantMenuRepository;
        this.cartRepository = cartRepository;
    }

    public String createOrders(String userId ){
        Order order = new Order();
        ApplicationUser user = AuthenticatedUser.getLoggedInUser();
        List<Cart> cartList = cartRepository.findByApplicationUser(user);
        if(cartList == null) throw new BadRequestException(ApiErrorCodes.INVALID_REQUEST.getKey(), "No cart found");
        order.setCart(cartList);


        return null;

    }

    private double calculateTotalPrice(List<Cart> cartList){
        return cartList.stream().mapToDouble(cart -> cart.getRestaurantMenu().getPrice() * cart.getQuantity()).sum();
    }

    private int calculateMaxPreparationTime(List<Cart> cartList){
        return cartList.stream().max(Comparator.comparingInt(c -> c.getRestaurantMenu().getPreparationTime())).get().getRestaurantMenu().getPreparationTime();
    }

    private LocalDateTime calculateDeliveryTime(List<Cart> cartList){
        return LocalDateTime.now().plusMinutes(calculateMaxPreparationTime(cartList));
    }
}
