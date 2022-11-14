package com.cartrawler.assessment.resource;

import com.cartrawler.assessment.service.CarDataService;
import com.cartrawler.assessment.service.response.CarDataResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/cardata")
public class CarDataResource {

    private static final Logger LOG = LoggerFactory.getLogger(CarDataResource.class);

    @Autowired
    private CarDataService service;

    @GetMapping("/list")
    public HttpEntity<Set<CarDataResponse>> listCarData(@RequestParam(defaultValue = "0") Boolean bank) {

        try {
            LOG.info("listCarData init");
            return ResponseEntity.ok(service.list(bank));
        } catch (UnsupportedOperationException e) {
            LOG.error("error: ", e);
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            LOG.error("error: ", e);
            return ResponseEntity.internalServerError().build();
        } finally {
            LOG.info("listCarData end");
        }
    }
}
