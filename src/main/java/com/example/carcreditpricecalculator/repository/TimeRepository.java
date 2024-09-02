package com.example.carcreditpricecalculator.repository;

import com.example.carcreditpricecalculator.model.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;

@Repository
public interface TimeRepository extends JpaRepository<Time, Integer> {
    //    boolean findTimeByMonth(Integer month);
    Time findTimeByMonthAndPercentDepositAndPercent(Integer month, Integer percentDeposit, Double percent);
    Time findTimeByMonthAndPercentDeposit(Integer month, Integer percentDeposit);
    ArrayList<Time> findAllByMonth(Integer month);
    ArrayList<Time> findAllByPercentDeposit(Integer percentDeposit);
}
