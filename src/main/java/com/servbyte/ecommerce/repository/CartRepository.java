package com.servbyte.ecommerce.repository;

import com.servbyte.ecommerce.entities.ApplicationUser;
import com.servbyte.ecommerce.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByApplicationUser(ApplicationUser user);
}
