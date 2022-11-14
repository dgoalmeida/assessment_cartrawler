package com.cartrawler.assessment.service.translator;

import com.cartrawler.assessment.repository.dao.entity.CarResult;
import com.cartrawler.assessment.service.response.CarDataResponse;

import java.util.Set;
import java.util.stream.Collectors;

public class CarDataTranslator {

    private CarDataResponse translate(CarResult carData) {
        return CarDataResponse
                .builder()
                .description(carData.getDescription())
                .fuelPolicy(carData.getFuelPolicy())
                .rentalCost(carData.getRentalCost())
                .sippCode(carData.getSippCode())
                .supplierName(carData.getSupplierName())
                .build();
    }

    public Set<CarDataResponse> translate(Set<CarResult> carResults) {
        return carResults.stream().map(this::translate).collect(Collectors.toSet());
    }

}
