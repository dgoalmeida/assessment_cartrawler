package com.cartrawler.assessment.service.response;

import com.cartrawler.assessment.repository.enums.FuelPolicy;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class CarDataResponse {

    @ToString.Exclude()
    private String description;

    private String supplierName;

    private String sippCode;

    @ToString.Exclude()
    private double rentalCost;

    private FuelPolicy fuelPolicy;

    @JsonIgnore
    @ToString.Exclude
    private Integer corporate;

}
