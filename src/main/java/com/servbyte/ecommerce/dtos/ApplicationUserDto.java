package com.servbyte.ecommerce.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;

@Data
public class ApplicationUserDto {
    @NotBlank
    @ApiModelProperty(required = true)
    private String firstName;
    @NotBlank
    @ApiModelProperty(required = true)
    private String lastName;
    @NotBlank
    @ApiModelProperty(required = true)
    private String email;
    @NotBlank
    @ApiModelProperty(required = true)
    private String city;
    @NotBlank
    @ApiModelProperty(required = true)
    private String password;
    @ApiModelProperty(required = true)
    private String phoneNumber;
    @NotBlank
    @ApiModelProperty(required = true)
    private String role;

    public ApplicationUserDto(@NotBlank String firstName, @NotBlank String lastName, @NotBlank String email, @NotBlank String city, @NotBlank String password, String phoneNumber, @NotBlank String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.city = city;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }
}
