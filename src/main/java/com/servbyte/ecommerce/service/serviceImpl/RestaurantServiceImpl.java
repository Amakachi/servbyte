package com.servbyte.ecommerce.service.serviceImpl;

import com.servbyte.ecommerce.dtos.RestaurantDto;
import com.servbyte.ecommerce.dtos.RestaurantMenuDto;
import com.servbyte.ecommerce.entities.*;
import com.servbyte.ecommerce.enums.ApiErrorCodes;
import com.servbyte.ecommerce.exceptions.BadRequestException;
import com.servbyte.ecommerce.exceptions.NotFoundException;
import com.servbyte.ecommerce.repository.CitiesRepository;
import com.servbyte.ecommerce.repository.LogisticsRepository;
import com.servbyte.ecommerce.repository.RestaurantMenuRepository;
import com.servbyte.ecommerce.repository.RestaurantRepository;
import com.servbyte.ecommerce.service.RestaurantService;
import com.servbyte.ecommerce.utility.AuthenticatedUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final RestaurantRepository restaurantRepository;
    private final RestaurantMenuRepository restaurantMenuRepository;
    private final CitiesRepository citiesRepository;
    private final LogisticsRepository logisticsRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, RestaurantMenuRepository restaurantMenuRepository, CitiesRepository citiesRepository, LogisticsRepository logisticsRepository) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantMenuRepository = restaurantMenuRepository;
        this.citiesRepository = citiesRepository;
        this.logisticsRepository = logisticsRepository;
    }

    @Override
    public Restaurant registerRestaurant(RestaurantDto restaurantDto){
        Restaurant restaurant = new Restaurant();
        ApplicationUser user = AuthenticatedUser.getLoggedInUser();
        if(!user.getRole().equals("RESTAURANT")) throw new BadRequestException(ApiErrorCodes.INVALID_REQUEST.getKey(), "Cannot create restaurant with " +user.getRole() +" role");

        if(restaurantDto != null){
            BeanUtils.copyProperties(restaurantDto, restaurant);
            restaurant.setCreatedDate(LocalDateTime.now());
            restaurant.setListOfCities(restaurantDto.getListOfCities().stream().map(s -> citiesRepository.findByName(s.toUpperCase())).collect(Collectors.toList()));
            List<Logistics> logisticsList = restaurantDto.getLogistics().stream().map(s -> logisticsRepository.findByCompanyName(s)).collect(Collectors.toList());
            if(logisticsList == null) throw new NotFoundException(ApiErrorCodes.NOT_FOUND.getKey(), "No logistics found");
            restaurant.setLogisticsList(logisticsList);
            restaurant.setApplicationUser(user);
            logger.info("Restaurant owner has been registered {}" + restaurant);
            restaurantRepository.save(restaurant);
        }else throw new BadRequestException(ApiErrorCodes.INVALID_REQUEST.getKey(), "User should not be empty");
        return restaurant;
    }

    @Override
    public List<Restaurant> getAllRestaurants(){
            return restaurantRepository.findAll();
    }


    public List<Restaurant> findRestaurantsByCity(String city){
        if(city.isEmpty() || city == null) throw new BadRequestException(ApiErrorCodes.INVALID_REQUEST.getKey(), "city cannot be empty");
        Cities cities = citiesRepository.findByName(city.toUpperCase());
        if(cities == null) throw new NotFoundException(ApiErrorCodes.NOT_FOUND.getKey(), "This city is not registered");
        List<Restaurant> restaurantList = restaurantRepository.findByListOfCities(cities);
        if(restaurantList != null) return restaurantList;
        throw new NotFoundException(ApiErrorCodes.NOT_FOUND.getKey(), "No restaurants found in " + city);
    }

    public List<Cities> fetchAllCities(){
        return citiesRepository.findAll();
    }

    public String addMenusToRestaurant(List<RestaurantMenuDto> restaurantMenuDto, final Long restaurantID){
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantID);
        if(restaurantID == null) throw new BadRequestException(ApiErrorCodes.INVALID_REQUEST.getKey(), "No restaurant id found");
        if(!restaurant.isPresent()) throw new NotFoundException(ApiErrorCodes.NOT_FOUND.getKey(), "Restaurant not found");

        restaurantMenuDto.forEach(menu -> {
            RestaurantMenu restaurantMenu = new RestaurantMenu();
            restaurantMenu.setRestaurant(restaurant.get());
            restaurantMenu.setCreatedDate(LocalDateTime.now());
            BeanUtils.copyProperties(menu, restaurantMenu);
            restaurantMenuRepository.save(restaurantMenu);
        });
        return "Menu saved successfully for restaurant with name " + restaurant.get().getRestaurantName();
    }

    public List<RestaurantMenu> findAllMenusByRestaurant(final Long restaurantId){
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        if(!restaurant.isPresent()) throw new NotFoundException(ApiErrorCodes.NOT_FOUND.getKey(), "Restaurant not found");

        return restaurantMenuRepository.findByRestaurant(restaurant.get());
    }
}
