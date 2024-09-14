package com.example.carcreditpricecalculator.controller;

import com.example.carcreditpricecalculator.model.dto.CreditSettingDTO;
import com.example.carcreditpricecalculator.service.CalculatorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class CalculatorController {
    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }



    @PostMapping("/buyCar/{nameAutoDealer}/{bank}")
    public ResponseEntity<ArrayList<CreditSettingDTO>> buyCar(@PathVariable("nameAutoDealer") String nameAutoDealer,
                                                              @PathVariable("bank") String nameBank,
                                                              @RequestParam("costCar") Double costCar,
                                                              @RequestParam("deposit") Double deposit,
                                                              @RequestParam("wantPayInMonth") Double wantPayInMonth){
        System.out.println(nameAutoDealer+"  "+nameBank+"   controller");
        return new ResponseEntity<>(calculatorService.canBuyCar(costCar,deposit,wantPayInMonth,nameAutoDealer,nameBank), HttpStatus.OK);
    }

    @PostMapping("/morePayInMonth")
    public ResponseEntity<ArrayList<CreditSettingDTO>> mayBeBuyCarButMorePayInMonth(@RequestParam("costCar") Double costCar,
                                                    @RequestParam("deposit") Double deposit){
        return new ResponseEntity<>(calculatorService.allOptionsWithThisCar(costCar,deposit), HttpStatus.OK);
    }

}
