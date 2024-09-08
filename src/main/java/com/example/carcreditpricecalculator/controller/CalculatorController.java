package com.example.carcreditpricecalculator.controller;

import com.example.carcreditpricecalculator.service.CalculatorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class CalculatorController {
    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }



    @PostMapping("/buyCar")
    public ResponseEntity<ArrayList<String>> buyCar(@RequestParam("costCar") Double costCar,
                                                       @RequestParam("deposit") Double deposit,
                                                    @RequestParam("wantPayInMonth") Double wantPayInMonth){
        return new ResponseEntity<>(calculatorService.canBuyCar(costCar,deposit,wantPayInMonth), HttpStatus.OK);
    }

    @PostMapping("/morePayInMonth")
    public ResponseEntity<ArrayList<String>> mayBeBuyCarButMorePayInMonth(@RequestParam("costCar") Double costCar,
                                                    @RequestParam("deposit") Double deposit){
        return new ResponseEntity<>(calculatorService.allOptionsWithThisCar(costCar,deposit), HttpStatus.OK);
    }

}
