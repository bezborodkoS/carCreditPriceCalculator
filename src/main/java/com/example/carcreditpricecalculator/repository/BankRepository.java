package com.example.carcreditpricecalculator.repository;

import com.example.carcreditpricecalculator.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface BankRepository extends JpaRepository<Bank,Integer> {
    Bank findByNameBank(String nameBank);
    ArrayList<Bank> findAllBy();
}
