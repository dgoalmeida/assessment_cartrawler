package com.cartrawler.assessment.repository;

import com.cartrawler.assessment.repository.dao.CarDataRepository;
import com.cartrawler.assessment.repository.dao.entity.CarResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class CarDataFactory {

    @Autowired
    private CarData carData;

    @Autowired
    private CarDataRepository carDataRepository;

    public Set<CarResult> listCars(Boolean isData) {
        if (isData) {
            List<CarResult> all = carDataRepository.findAll();
            return new HashSet<>(all);
        }
        return carData.listCars();
    }
}
