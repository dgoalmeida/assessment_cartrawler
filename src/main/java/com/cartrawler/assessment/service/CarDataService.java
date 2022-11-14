package com.cartrawler.assessment.service;

import com.cartrawler.assessment.repository.CarDataFactory;
import com.cartrawler.assessment.service.response.CarDataResponse;
import com.cartrawler.assessment.service.translator.CarDataTranslator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

@Service
public class CarDataService {

    private static final List<String> CORPORATE = List.of("AVIS", "BUDGET", "ENTERPRISE", "FIREFLY", "HERTZ", "SIXT", "THRIFTY");
    public static final String M = "M";
    public static final String E = "E";
    public static final String C = "C";
    @Autowired
    private CarDataFactory carDataFactory;

    private static final Logger LOG = LoggerFactory.getLogger(CarDataService.class);

    public Set<CarDataResponse> list(Boolean bank) {

        Set<CarDataResponse> carDataResponses = new CarDataTranslator().translate(carDataFactory.listCars(bank));

        long count = carDataResponses.stream()
                .collect(Collectors.groupingBy(CarDataResponse::toString, Collectors.counting()))
                .values().stream().filter(i -> i > 1).count();

        LOG.info("m=list duplicates: {}", count);

        LinkedHashSet<CarDataResponse> carList = carDataResponses.stream()
                .filter(distinctByKey(CarDataResponse::toString))
                .peek(identifyCorporateCar())
                .sorted(Comparator.comparing(CarDataResponse::getCorporate)
                        .thenComparingInt(getByLetter(M))
                        .thenComparingInt(getByLetter(E))
                        .thenComparingInt(getByLetter(C))
                        .thenComparing(CarDataResponse::getSupplierName)
                        .thenComparing(CarDataResponse::getRentalCost))
                .collect(Collectors.toCollection(LinkedHashSet::new));

        LOG.info("m=List: {}",carList);

        return carList;
    }

    private static ToIntFunction<CarDataResponse> getByLetter(String letter) {
        return s -> s.getSippCode().startsWith(letter) ? 0 : 1;
    }

    private static Consumer<CarDataResponse> identifyCorporateCar() {
        return x -> {
            if (CORPORATE.contains(x.getSupplierName()))
                x.setCorporate(0);
            else
                x.setCorporate(1);
        };
    }

    private <T> Predicate<T> distinctByKey(Function<? super T, Object> key) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(key.apply(t), Boolean.TRUE) == null;
    }

}
