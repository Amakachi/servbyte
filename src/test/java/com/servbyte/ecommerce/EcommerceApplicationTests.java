package com.servbyte.ecommerce;

import com.servbyte.ecommerce.dtos.ApplicationUserDto;
import com.servbyte.ecommerce.dtos.LogisticsDto;
import com.servbyte.ecommerce.dtos.RestaurantDto;
import com.servbyte.ecommerce.dtos.RestaurantMenuDto;
import com.servbyte.ecommerce.entities.Logistics;
import com.servbyte.ecommerce.entities.Restaurant;
import com.servbyte.ecommerce.service.LogisticsService;
import com.servbyte.ecommerce.service.RestaurantService;
import com.servbyte.ecommerce.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.devtools.autoconfigure.DevToolsProperties;
import org.springframework.boot.test.context.SpringBootTest;
import  org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EcommerceApplicationTests {

    @Autowired
    private UserService userService;
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private LogisticsService logisticsService;
    private ApplicationUserDto applicationUserDto;
    private ApplicationUserDto restaurantUser;
    private RestaurantDto restaurantDto;
    private static RestaurantMenuDto restaurantMenuDto;
    private Restaurant restaurant;
    private LogisticsDto logisticsDto;
    private static List<RestaurantMenuDto> restaurantMenuDtoList;


    @Before
    public void setParametera(){
       applicationUserDto = new ApplicationUserDto("Chiamaka", "Okenwa", "amaka@gmail.com", "LAGOS", "password", "08069497823", "USER");
       applicationUserDto = new ApplicationUserDto("Uchenna", "Okeke", "uche@gmail.com", "ABUJA", "password", "08068786594", "RESTAURANT");
       logisticsDto = new LogisticsDto("Sumec Logistics", "sumeg.png", "info@sumec.com", "09075648956", "LAGOS");
       logisticsDto = new LogisticsDto("Delfa Logistics", "delfa.png", "info@delfa.com", "09076785432", "LAGOS");
       restaurantDto = new RestaurantDto("Black olive restaurant", "info@black-olive.com", "logo.png", Arrays.asList("LAGOS"), "08064894876", Arrays.asList("Sumec Logistics", "Delfa Logistics"));
       restaurantMenuDto = new RestaurantMenuDto("Fried rice", 1500, 20, Arrays.asList("rice.png", "friedrice.png"), "A full plate of rice");
    }

    @Test
    public void registerUser(){
        assert(userService.registerUser(applicationUserDto).contains("saved") );

    }

    @Test
    public void createLogistics(){
        assert(logisticsService.registerLogisticsCompany(logisticsDto).contains("saved") );

    }

    @Test
    public void createRestaurant(){
        restaurant = restaurantService.registerRestaurant(restaurantDto);
        assert(restaurant != null );
    }

    @Test
    public void createRestaurantMenu(){
        restaurantMenuDtoList.add(restaurantMenuDto);
        assert(restaurantService.addMenusToRestaurant(restaurantMenuDtoList, restaurant.getId()).contains("saved"));

    }
}
