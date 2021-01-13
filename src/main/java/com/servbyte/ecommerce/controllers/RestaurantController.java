package com.servbyte.ecommerce.controllers;

import com.servbyte.ecommerce.dtos.RestaurantDto;
import com.servbyte.ecommerce.dtos.RestaurantMenuDto;
import com.servbyte.ecommerce.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/all-cities")
    public ResponseEntity<?> fetchAllCities(){
        return ResponseEntity.ok(restaurantService.fetchAllCities());
    }

    @PostMapping("/find/{city}")
    public ResponseEntity<?> fetchRestaurantsByCities(@RequestParam String city){
        return ResponseEntity.ok(restaurantService.findRestaurantsByCity(city));
    }

    @PostMapping("/register")
    public ResponseEntity registerRestaurants(@RequestBody RestaurantDto restaurantDto){
        return ResponseEntity.ok(restaurantService.registerRestaurant(restaurantDto));
    }

    @GetMapping("/find-menus/{restaurantId}")
    public ResponseEntity<?> findAllMenusByRestaurant(@RequestParam Long restaurantId){
        return ResponseEntity.ok(restaurantService.findAllMenusByRestaurant(restaurantId));
    }

    @PostMapping("/save-menu/{restaurantId}")
    public ResponseEntity<?> saveMenusByRestaurant(@RequestBody List<RestaurantMenuDto> restaurantMenuDto, @RequestParam Long restaurantID){
        return ResponseEntity.ok(restaurantService.addMenusToRestaurant(restaurantMenuDto, restaurantID)); }


    @GetMapping("/all")
    public ResponseEntity fetchAll(){
        return ResponseEntity.ok(restaurantService.getAllRestaurants());
    }
}
