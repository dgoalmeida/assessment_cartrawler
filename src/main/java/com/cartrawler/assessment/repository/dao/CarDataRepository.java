package com.cartrawler.assessment.repository.dao;

import com.cartrawler.assessment.repository.dao.entity.CarResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarDataRepository extends JpaRepository<CarResult, String> {

    default List<CarResult> findAll() {
        throw new UnsupportedOperationException();
    }

    ;
}
