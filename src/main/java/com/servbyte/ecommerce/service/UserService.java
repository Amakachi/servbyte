package com.servbyte.ecommerce.service;

import com.servbyte.ecommerce.dtos.ApplicationUserDto;
import com.servbyte.ecommerce.dtos.ApplicationUserLoginDto;
import com.servbyte.ecommerce.dtos.response.ApplicationUserResponse;
import com.servbyte.ecommerce.dtos.response.JwtResponse;
import com.servbyte.ecommerce.entities.ApplicationUser;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UserService {
    String registerUser(ApplicationUserDto userDTO);
    List<ApplicationUserResponse> findAllUsers();
    ApplicationUser findUserByEmail(String email);
    JwtResponse generateToken(ApplicationUserLoginDto loginDTO);
    List<String> fetchAllRoles();
}
