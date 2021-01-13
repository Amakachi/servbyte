package com.servbyte.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "restaurants")
public class Restaurant extends AbstractEntity{
    private String restaurantName;
    private String restaurantEmail;
    private String restaurantLogo;
    @OneToMany
    private List<Cities> listOfCities;
    @OneToMany
    private List<Logistics> logisticsList;
    private String phoneNumber;
    @ManyToOne
    @JsonIgnore
    private ApplicationUser applicationUser;

}
