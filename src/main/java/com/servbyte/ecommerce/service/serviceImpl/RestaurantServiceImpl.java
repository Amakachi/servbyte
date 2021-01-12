package com.servbyte.ecommerce.service.serviceImpl;

import com.servbyte.ecommerce.dtos.RestaurantDto;
import com.servbyte.ecommerce.dtos.RestaurantMenuDto;
import com.servbyte.ecommerce.entities.Cities;
import com.servbyte.ecommerce.entities.Restaurant;
import com.servbyte.ecommerce.entities.RestaurantMenu;
import com.servbyte.ecommerce.enums.ApiErrorCodes;
import com.servbyte.ecommerce.exceptions.BadRequestException;
import com.servbyte.ecommerce.exceptions.NotFoundException;
import com.servbyte.ecommerce.repository.CitiesRepository;
import com.servbyte.ecommerce.repository.LogisticsRepository;
import com.servbyte.ecommerce.repository.RestaurantMenuRepository;
import com.servbyte.ecommerce.repository.RestaurantRepository;
import com.servbyte.ecommerce.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
        List<RestaurantMenu> restaurantMenuList = new ArrayList<>();

        if(restaurantDto != null){
            BeanUtils.copyProperties(restaurantDto, restaurant);
            restaurant.setCreatedDate(LocalDateTime.now());
            restaurant.setListOfCities(restaurantDto.getListOfCities().stream().map(s -> citiesRepository.findByName(s)).collect(Collectors.toList()));
            restaurant.setLogisticsList(restaurantDto.getLogistics().stream().map(s -> logisticsRepository.findByCompanyName(s)).collect(Collectors.toList()));
            logger.info("Restaurant owner has been registered {}" + restaurant);
        }else throw new BadRequestException(ApiErrorCodes.INVALID_REQUEST.getKey(), "User should not be empty");
        return restaurant;
    }

    @Override
    public List<Restaurant> getAllRestaurants(){
            return restaurantRepository.findAll();
    }


    @Override
    public List<Restaurant> findRestaurantsByCity(String city){
        if(!city.isEmpty() && Objects.nonNull(city)){
            return restaurantRepository.findByListOfCities(city);
        }
        throw new BadRequestException(ApiErrorCodes.INVALID_REQUEST.getKey(), "city cannot be empty");
    }

    public List<Cities> fetchAllCities(){
        return citiesRepository.findAll();
    }

    public void addMenusToRestaurant(List<RestaurantMenuDto> restaurantMenuDto, final Long restaurantID){
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantID);
        if(restaurantID == null) throw new BadRequestException(ApiErrorCodes.INVALID_REQUEST.getKey(), "No restaurant id found");
        if(!restaurant.isPresent()) throw new NotFoundException(ApiErrorCodes.NOT_FOUND.getKey(), "Restaurant not found");

        restaurantMenuDto.forEach(menu -> {
            RestaurantMenu restaurantMenu = new RestaurantMenu();
            restaurantMenu.setRestaurant(restaurant.get());
            BeanUtils.copyProperties(menu, restaurantMenu);
            restaurantMenuRepository.save(restaurantMenu);
        });

    }

    public List<RestaurantMenu> findAllMenusByRestaurant(final Long restaurantId){
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        if(!restaurant.isPresent()) throw new NotFoundException(ApiErrorCodes.NOT_FOUND.getKey(), "Restaurant not found");

        return restaurantMenuRepository.findByRestaurant(restaurant.get());
    }
}
