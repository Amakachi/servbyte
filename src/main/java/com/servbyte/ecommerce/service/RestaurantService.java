package com.servbyte.ecommerce.service;

import com.servbyte.ecommerce.dtos.RestaurantDto;
import com.servbyte.ecommerce.dtos.RestaurantMenuDto;
import com.servbyte.ecommerce.entities.Cities;
import com.servbyte.ecommerce.entities.Restaurant;
import com.servbyte.ecommerce.entities.RestaurantMenu;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RestaurantService {
     Restaurant registerRestaurant(RestaurantDto restaurantDto);
     void addMenusToRestaurant(List<RestaurantMenuDto> restaurantMenuDto, final Long restaurantID);
     List<RestaurantMenu> findAllMenusByRestaurant(final Long restaurantId);
     List<Restaurant> getAllRestaurants();
     List<Restaurant> findRestaurantsByCity(String city);
     List<Cities> fetchAllCities();
}
