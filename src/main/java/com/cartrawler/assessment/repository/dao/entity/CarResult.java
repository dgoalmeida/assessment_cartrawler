package com.cartrawler.assessment.repository.dao.entity;

import com.cartrawler.assessment.repository.enums.FuelPolicy;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@AllArgsConstructor
@Entity
public class CarResult {

    @Column
    @Id
    private final String description;

    @Column
    private final String supplierName;

    @Column
    private final String sippCode;

    @Column
    private final double rentalCost;

    @Column
    private final FuelPolicy fuelPolicy;

}
