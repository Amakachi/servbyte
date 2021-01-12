package com.servbyte.ecommerce.repository;

import com.servbyte.ecommerce.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Restaurant findByRestaurantName(String restaurantName);
    List<Restaurant> findByListOfCities(String city);
    Restaurant findById(long id);
}
