package com.servbyte.ecommerce.service;

import com.servbyte.ecommerce.dtos.LogisticsDto;
import com.servbyte.ecommerce.entities.Logistics;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface LogisticsService {
    String registerLogisticsCompany(LogisticsDto logisticsDto);
    List<Logistics> fetchAllLogisticsCompany();
}
