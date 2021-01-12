package com.servbyte.ecommerce.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LogisticsDto {
    @ApiModelProperty(required = true)
    @NotBlank
    private String companyName;
    @ApiModelProperty(required = true)
    @NotBlank
    private String logo;
    @ApiModelProperty(required = true)
    @NotBlank
    private String companyEmail;
    @ApiModelProperty(required = true)
    @NotBlank
    private String companyPhoneNumber;
    @NotBlank
    @ApiModelProperty(required = true)
    private String companyCity;

    public LogisticsDto() {
    }

    public LogisticsDto(@NotBlank String companyName, @NotBlank String logo, @NotBlank String companyEmail, @NotBlank String companyPhoneNumber, @NotBlank String companyCity) {
        this.companyName = companyName;
        this.logo = logo;
        this.companyEmail = companyEmail;
        this.companyPhoneNumber = companyPhoneNumber;
        this.companyCity = companyCity;
    }
}
