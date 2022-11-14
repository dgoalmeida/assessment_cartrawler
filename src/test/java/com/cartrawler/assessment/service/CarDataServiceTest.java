package com.cartrawler.assessment.service;


import com.cartrawler.assessment.repository.CarData;
import com.cartrawler.assessment.repository.CarDataFactory;
import com.cartrawler.assessment.service.response.CarDataResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CarDataServiceTest {

    @InjectMocks
    @Spy
    private CarDataService service;

    @Mock
    private CarDataFactory factory;

    @Test
    public void notCarsDataDuplicated() throws Exception {

        when(factory.listCars(anyBoolean())).thenReturn(CarData.CARS);
        Set<CarDataResponse> list = service.list(true);

        long count = list.stream()
                .collect(Collectors.groupingBy(CarDataResponse::toString, Collectors.counting()))
                .values().stream().filter(i -> i > 1).count();

        Assertions.assertEquals(0, count);

        verify(factory, times(1)).listCars(anyBoolean());

    }

    @Test
    public void groupFirstTest() {

        when(factory.listCars(anyBoolean())).thenReturn(CarData.CARS);
        Set<CarDataResponse> list = service.list(true);

        List group = List.of("M", "E", "C");

        List<String> collect = list.stream().map(s -> String.valueOf(s.getSippCode().charAt(0)))
                .distinct().collect(Collectors.toList()).subList(0, 3);

        assertArrayEquals(group.toArray(), collect.toArray());
    }
}
