package com.example.carcreditpricecalculator.controller;

import com.example.carcreditpricecalculator.service.admin.BankService;
import com.example.carcreditpricecalculator.service.admin.CarDealerService;
import com.example.carcreditpricecalculator.service.admin.CreditSettingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/admin")
public class AdminController {
    private final BankService bankService;
    private final CarDealerService carDealerService;
    private final CreditSettingService creditSettingService;

    public AdminController(BankService bankService, CarDealerService carDealerService, CreditSettingService creditSettingService) {
        this.bankService = bankService;
        this.carDealerService = carDealerService;
        this.creditSettingService = creditSettingService;
    }

    @PostMapping("/addSetting")
    public ResponseEntity<String> addSetting(@RequestParam("month") Integer month,
                                             @RequestParam("percentDeposit") Integer percentDeposit,
                                             @RequestParam("percent") Double percent,
                                             @RequestParam("nameBank") String nameBank,
                                             @RequestParam("nameCarDealer") String nameCarDealer) {
        creditSettingService.addSettingsPercent(month, percentDeposit, percent,nameBank,nameCarDealer);
        return new ResponseEntity<>("Add setting: " + month + " " + percentDeposit + "% " + percent, HttpStatus.CREATED);
    }

    @PostMapping("/addBank")
    public ResponseEntity<String> addBank(@RequestParam("nameBank") String nameBank) {
        bankService.addBank(nameBank);
        return new ResponseEntity<>("Add bank: " + nameBank, HttpStatus.CREATED);
    }

    @PostMapping("/addCarDealer")
    public ResponseEntity<String> addCarDealer(@RequestParam("nameCarDealer") String nameCarDealer) {
        carDealerService.addCarDealer(nameCarDealer);
        return new ResponseEntity<>("Add car dealer: " + nameCarDealer, HttpStatus.CREATED);
    }

    @PostMapping("/addBankToCarDealer")
    public ResponseEntity<String> addBankToCarDealer(@RequestParam("nameBank") String nameBank,
                                               @RequestParam("nameCarDealer") String nameCarDealer) {
        carDealerService.addBankToCarDealer(nameCarDealer,nameBank);
        return new ResponseEntity<>("Add bank: " + nameBank+" to "+nameCarDealer, HttpStatus.CREATED);
    }
}
