package com.servbyte.ecommerce.repository;

import com.servbyte.ecommerce.entities.Restaurant;
import com.servbyte.ecommerce.entities.RestaurantMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantMenuRepository extends JpaRepository<RestaurantMenu, Long> {
    List<RestaurantMenu> findByRestaurant(Restaurant restaurant);
    RestaurantMenu findById(long id);
}
