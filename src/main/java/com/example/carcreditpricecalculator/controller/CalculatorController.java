package com.example.carcreditpricecalculator.controller;

import com.example.carcreditpricecalculator.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class CalculatorController {
    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @PostMapping("/api/addSetting")
    public ResponseEntity<String> addSetting(@RequestParam("month") Integer month,
                                             @RequestParam("percentDeposit") Integer percentDeposit,
                                             @RequestParam("percent") Double percent){
//        System.out.println(month+percentDeposit+percent);
        calculatorService.addSettingsPercent(month, percentDeposit, percent);
        return new ResponseEntity<>("Add setting: "+month+" "+percentDeposit+"% "+percent, HttpStatus.CREATED);
    }

    @PostMapping("/api/buyCar")
    public ResponseEntity<ArrayList<String>> buyCar(@RequestParam("costCar") Double costCar,
                                                       @RequestParam("deposit") Double deposit){
//        System.out.println(month+percentDeposit+percent);

        return new ResponseEntity<>(calculatorService.canBuyCar(costCar,deposit), HttpStatus.OK);
    }
}
