package com.servbyte.ecommerce.service.serviceImpl;

import com.servbyte.ecommerce.dtos.LogisticsDto;
import com.servbyte.ecommerce.entities.ApplicationUser;
import com.servbyte.ecommerce.entities.Logistics;
import com.servbyte.ecommerce.enums.ApiErrorCodes;
import com.servbyte.ecommerce.exceptions.BadRequestException;
import com.servbyte.ecommerce.repository.LogisticsRepository;
import com.servbyte.ecommerce.service.LogisticsService;
import com.servbyte.ecommerce.utility.AuthenticatedUser;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class LogisticsServiceImpl implements LogisticsService {

    private final LogisticsRepository logisticsRepository;

    public LogisticsServiceImpl(LogisticsRepository logisticsRepository) {
        this.logisticsRepository = logisticsRepository;
    }

    public String registerLogisticsCompany(LogisticsDto logisticsDto){
        ApplicationUser user = AuthenticatedUser.getLoggedInUser();
        if(!user.getRole().equals("LOGISTICS")) throw new BadRequestException(ApiErrorCodes.INVALID_REQUEST.getKey(), "Cannot create logistics with " + user.getRole() + " role");

        if(logisticsDto != null){
            Logistics logistics = new Logistics();
            BeanUtils.copyProperties(logisticsDto, logistics);
            logistics.setApplicationUser(user);
            logisticsRepository.save(logistics);
        }else throw new BadRequestException(ApiErrorCodes.INVALID_REQUEST.getKey(), "Company should not be empty");

        return "Company saved successfully";
    }

    public List<Logistics> fetchAllLogisticsCompany(){
        return logisticsRepository.findAll();
    }

}
