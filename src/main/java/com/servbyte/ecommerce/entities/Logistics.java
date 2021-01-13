package com.servbyte.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "logistics")
@Getter
@Setter
public class Logistics extends AbstractEntity{
    private String companyName;
    private String logo;
    private String companyEmail;
    private String companyPhoneNumber;
    private String companyCity;
    @ManyToOne
    @JsonIgnore
    private ApplicationUser applicationUser;
}
