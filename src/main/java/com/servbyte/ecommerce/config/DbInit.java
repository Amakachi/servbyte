package com.servbyte.ecommerce.config;

import com.servbyte.ecommerce.dtos.CitiesDto;
import com.servbyte.ecommerce.dtos.LogisticsDto;
import com.servbyte.ecommerce.entities.Cities;
import com.servbyte.ecommerce.entities.Logistics;
import com.servbyte.ecommerce.repository.ApplicationUserRepository;
import com.servbyte.ecommerce.repository.CitiesRepository;
import com.servbyte.ecommerce.repository.LogisticsRepository;
import com.servbyte.ecommerce.repository.RestaurantRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class DbInit {
    private final CitiesRepository citiesRepository;
    private final LogisticsRepository logisticsRepository;
    private final ApplicationUserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    public DbInit(CitiesRepository citiesRepository, LogisticsRepository logisticsRepository, ApplicationUserRepository userRepository, RestaurantRepository restaurantRepository) {
        this.citiesRepository = citiesRepository;
        this.logisticsRepository = logisticsRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @PostConstruct
    private void creatCities(){

        List<CitiesDto> citiesList = Arrays.asList(new CitiesDto("LAGOS"),
                new CitiesDto("ABUJA"), new CitiesDto("IBADAN"), new CitiesDto("UYO"),
                new CitiesDto("PORTHACOURT"), new CitiesDto("ENUGU"), new CitiesDto("ASABA"),
                new CitiesDto("KANO"), new CitiesDto("UMUAHIA"), new CitiesDto("ABA"), new CitiesDto("OWERRI"));

        citiesList.forEach(city -> {
            Cities cities = new Cities();
            BeanUtils.copyProperties(city, cities);
            citiesRepository.save(cities);
        });

    }

    @PostConstruct
    private void createLogistics(){
        List<LogisticsDto> logisticsDtos = Arrays.asList(new LogisticsDto("Naye Logistics", "naye.png", "naye@company.com", "08079672345", "LAGOS"),
                new LogisticsDto("Sumec Logistics", "sumec.png", "sumec@company.com", "080796456745", "ABUJA"));
        logisticsDtos.forEach(company -> {
            Logistics logistics = new Logistics();
            BeanUtils.copyProperties(company, logistics);
            logistics.setCreatedDate(LocalDateTime.now());
            logisticsRepository.save(logistics);
        });

    }


}
