package com.example.carcreditpricecalculator.repository;

import com.example.carcreditpricecalculator.model.CarDealer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarDealerRepository extends JpaRepository<CarDealer,Integer> {
    CarDealer findByNameCarDealer(String nameCarDealer);
}
