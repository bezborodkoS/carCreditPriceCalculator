package com.example.carcreditpricecalculator.service;

//import com.example.carcreditpricecalculator.model.Percent;
//import com.example.carcreditpricecalculator.model.PercentDeposit;
//import com.example.carcreditpricecalculator.model.Time;
//import com.example.carcreditpricecalculator.repository.PercentDepositRepository;
//import com.example.carcreditpricecalculator.repository.PercentRepository;

import com.example.carcreditpricecalculator.model.CreditSetting;
import com.example.carcreditpricecalculator.model.dto.CreditSettingDTO;
import com.example.carcreditpricecalculator.repository.CreditSettingRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CalculatorService {
    private final CreditSettingRepository creditSettingRepository;

    public CalculatorService(CreditSettingRepository creditSettingRepository) {
        this.creditSettingRepository = creditSettingRepository;
    }
//    private final PercentRepository percentRepository;
//    private final PercentDepositRepository percentDepositRepository;

//    public CalculatorService(PercentDepositRepository percentDepositRepository, PercentRepository percentRepository, TimeRepository timeRepository) {
//        this.percentDepositRepository = percentDepositRepository;
//        this.percentRepository = percentRepository;
//        this.timeRepository = timeRepository;
//    }


    public boolean addSettingsPercent(Integer month, Integer percentDeposit, Double percent) {
        CreditSetting creditSetting;
        if (creditSettingRepository.findTimeByMonthAndPercentDeposit(month, percentDeposit) == null) {
            creditSetting = new CreditSetting();
            creditSetting.setMonth(month);
            creditSetting.setPercentDeposit(percentDeposit);
            creditSetting.setPercent(percent);
            creditSettingRepository.save(creditSetting);
            return true;
        }

        return false;
    }


//    start:  /buyCar
    //  покупка автомобиля с месячным платежом пользователя который он может себе позволить
    public ArrayList<String> canBuyCar(Double costCar, Double deposit, Double wantPayInMonth) {
        Integer calculateDepositPercent = (int) (deposit / (costCar / 100));
        calculateDepositPercent = foundPercentDepositFromCostCar(calculateDepositPercent);
        System.out.println("car " + calculateDepositPercent);
        ArrayList<String> transformationToObjArrayList = returnAllPurchaseOptionsCarWithWantPayInMonth(costCar, deposit, calculateDepositPercent, wantPayInMonth);
        for (String s : transformationToObjArrayList) {
            System.out.println(s);
        }
        return transformationToObjArrayList;
    }

    //    вернуть список всех возможных вариантов кредита с месячным платежом пользователя который он может себе позволить
    private ArrayList<String> returnAllPurchaseOptionsCarWithWantPayInMonth(Double costCar, Double deposit, Integer calculateDepositPercent, Double wantPayInMonth) {
        List<CreditSetting> foundAllVersionsWhizSpecificsPercentDeposit = creditSettingRepository.findAllByPercentDeposit(calculateDepositPercent);
        ArrayList<String> transformationToObjArrayList = new ArrayList<>();
        for (CreditSetting t : foundAllVersionsWhizSpecificsPercentDeposit) {
            double costCarAfterPayDeposit = calculateCostCarAfterPayDeposit(costCar, deposit);
            double monthlyPayment = calculateMonthlyPayment(t, costCarAfterPayDeposit);
            if (wantPayInMonth >= monthlyPayment) {
                System.out.println(t.toString());
                CreditSettingDTO creditSettingDTO = new CreditSettingDTO().convertCreditSettingToDTO(t);
                creditSettingDTO.setMonthlyPayment(monthlyPayment);
                System.out.println(creditSettingDTO.toString() + "//////////");
                transformationToObjArrayList.add(creditSettingDTO.toString());
            }
        }
        return transformationToObjArrayList;
    }
//    end:   /buyCar



//    start:    /morePayInMonth
//    все варианты машины с возможными кредитами
    public ArrayList<String> allOptionsWithThisCar(Double costCar, Double deposit) {
        Integer calculateDepositPercent = (int) (deposit / (costCar / 100));
        calculateDepositPercent = foundPercentDepositFromCostCar(calculateDepositPercent);
        System.out.println("car " + calculateDepositPercent);
        ArrayList<String> transformationToObjArrayList = returnAllPurchaseOptionsCar(costCar, deposit, calculateDepositPercent);
        for (String s : transformationToObjArrayList) {
            System.out.println(s);
        }
        return transformationToObjArrayList;
    }

    //    все варианты покупки машины в кредит
    private ArrayList<String> returnAllPurchaseOptionsCar(Double costCar, Double deposit, Integer calculateDepositPercent) {
        List<CreditSetting> foundAllVersionsWhizSpecificsPercentDeposit = creditSettingRepository.findAllByPercentDeposit(calculateDepositPercent);
        ArrayList<String> transformationToObjArrayList = new ArrayList<>();
        for (CreditSetting t : foundAllVersionsWhizSpecificsPercentDeposit) {
            double costCarAfterPayDeposit = calculateCostCarAfterPayDeposit(costCar, deposit);
            double monthlyPayment = calculateMonthlyPayment(t, costCarAfterPayDeposit);
            System.out.println(t.toString());
            CreditSettingDTO creditSettingDTO = new CreditSettingDTO().convertCreditSettingToDTO(t);
            creditSettingDTO.setMonthlyPayment(monthlyPayment);
            transformationToObjArrayList.add(creditSettingDTO.toString());
        }
        return transformationToObjArrayList;
    }
//    end:    /morePayInMonth


    //    найти процент первого взноса от общей стоимости всей машины
    private Integer foundPercentDepositFromCostCar(Integer calculateDepositPercent) {
        List<CreditSetting> creditSettingList = creditSettingRepository.findAllByMonth(12);
        for (CreditSetting t : creditSettingList) {
            System.out.println(t.getPercentDeposit());
        }
        for (int i = 0; i < creditSettingList.size(); i++) {
            if (i != creditSettingList.size() - 1) {
                if (calculateDepositPercent >= creditSettingList.get(i).getPercentDeposit() && calculateDepositPercent < creditSettingList.get(i + 1).getPercentDeposit()) {
                    calculateDepositPercent = creditSettingList.get(i).getPercentDeposit();
                    return calculateDepositPercent;
                }
            } else {
                calculateDepositPercent = creditSettingList.get(i).getPercentDeposit();
            }
        }
        return calculateDepositPercent;
    }

//    Расчитать месячный платеж
    private static double calculateMonthlyPayment(CreditSetting t, double costCarAfterPayDeposit) {
        return (costCarAfterPayDeposit + (costCarAfterPayDeposit / t.getPercent())) / t.getMonth();
    }

//    расчитать оставшуюся стоимость машины после вычета депозита
    private static double calculateCostCarAfterPayDeposit(Double costCar, Double deposit) {
        return costCar - deposit;
    }
}
