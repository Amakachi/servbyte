package com.servbyte.ecommerce.service.serviceImpl;

import com.servbyte.ecommerce.entities.*;
import com.servbyte.ecommerce.enums.ApiErrorCodes;
import com.servbyte.ecommerce.exceptions.BadRequestException;
import com.servbyte.ecommerce.repository.CartRepository;
import com.servbyte.ecommerce.repository.OrderRepository;
import com.servbyte.ecommerce.repository.RestaurantMenuRepository;
import com.servbyte.ecommerce.repository.RestaurantRepository;
import com.servbyte.ecommerce.service.OrderService;
import com.servbyte.ecommerce.utility.AuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {
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

    public String createOrders(Long userId){
        Order order = new Order();
        ApplicationUser user = AuthenticatedUser.getLoggedInUser();
        List<Cart> cartList = cartRepository.findByApplicationUser(user);
        if(cartList == null) throw new BadRequestException(ApiErrorCodes.INVALID_REQUEST.getKey(), "No cart found");
        order.setCart(cartList);
        order.setApplicationUser(user);
        order.setDeliveryTime(calculateDeliveryTime(cartList));
        order.setTotalPrice(calculateTotalPrice(cartList));
        orderRepository.save(order);
        return "Order created successfully for " + user.getFirstName();
    }

    private double calculateTotalPrice(List<Cart> cartList){
        return cartList.stream().mapToDouble(cart -> cart.getRestaurantMenu().getPrice() * cart.getQuantity()).sum();
    }

    private int calculateMaxPreparationTime(List<Cart> cartList){
        return cartList.stream().max(Comparator.comparingInt(c -> c.getRestaurantMenu().getPreparationTimeMinute())).get().getRestaurantMenu().getPreparationTimeMinute();
    }

    private LocalDateTime calculateDeliveryTime(List<Cart> cartList){
        return LocalDateTime.now().plusMinutes(calculateMaxPreparationTime(cartList));
    }
}
