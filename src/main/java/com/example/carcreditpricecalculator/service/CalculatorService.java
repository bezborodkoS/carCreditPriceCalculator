package com.example.carcreditpricecalculator.service;


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


    //    start:  /buyCar
    //  покупка автомобиля с месячным платежом пользователя который он может себе позволить
    public ArrayList<CreditSettingDTO> canBuyCar(Double costCar, Double deposit, Double wantPayInMonth, String nameAutoDealer, String nameBank) {
        Integer calculateDepositPercent = (int) (deposit / (costCar / 100));
        calculateDepositPercent = foundPercentDepositFromCostCar(calculateDepositPercent);
        ArrayList<CreditSettingDTO> transformationToObjArrayList = returnAllPurchaseOptionsCarWithWantPayInMonth(costCar, deposit, calculateDepositPercent, wantPayInMonth,nameAutoDealer,nameBank);
        return transformationToObjArrayList;
    }

    //    вернуть список всех возможных вариантов кредита с месячным платежом пользователя который он может себе позволить
    private ArrayList<CreditSettingDTO> returnAllPurchaseOptionsCarWithWantPayInMonth(Double costCar, Double deposit, Integer calculateDepositPercent, Double wantPayInMonth, String nameAutoDealer, String nameBank) {
        List<CreditSetting> foundAllVersionsWhizSpecificsPercentDeposit = creditSettingRepository.findAllByPercentDepositAndCarDealer_NameCarDealerAndBank_NameBank(calculateDepositPercent,nameAutoDealer,nameBank);
        ArrayList<CreditSettingDTO> transformationToObjArrayList = new ArrayList<>();
        for (CreditSetting t : foundAllVersionsWhizSpecificsPercentDeposit) {
            double costCarAfterPayDeposit = calculateCostCarAfterPayDeposit(costCar, deposit);
            double monthlyPayment = calculateMonthlyPayment(t, costCarAfterPayDeposit);
            if (wantPayInMonth >= monthlyPayment) {
                CreditSettingDTO creditSettingDTO = new CreditSettingDTO().convertCreditSettingToDTO(t);
                creditSettingDTO.setMonthlyPayment(monthlyPayment);
                transformationToObjArrayList.add(creditSettingDTO);
            }
        }

        return transformationToObjArrayList;
    }
//    end:   /buyCar


    //    start:    /morePayInMonth
//    все варианты машины с возможными кредитами
    public ArrayList<CreditSettingDTO> allOptionsWithThisCar(Double costCar, Double deposit,String nameAutoDealer) {
        Integer calculateDepositPercent = (int) (deposit / (costCar / 100));
        calculateDepositPercent = foundPercentDepositFromCostCar(calculateDepositPercent);
        ArrayList<CreditSettingDTO> transformationToObjArrayList = returnAllPurchaseOptionsCar(costCar, deposit, calculateDepositPercent,nameAutoDealer);
        return transformationToObjArrayList;
    }

    //    все варианты покупки машины в кредит
    private ArrayList<CreditSettingDTO> returnAllPurchaseOptionsCar(Double costCar, Double deposit, Integer calculateDepositPercent,String nameAutoDealer) {
        List<CreditSetting> foundAllVersionsWhizSpecificsPercentDeposit = creditSettingRepository.findAllByPercentDepositAndCarDealer_NameCarDealer(calculateDepositPercent,nameAutoDealer);
        ArrayList<CreditSettingDTO> transformationToObjArrayList = new ArrayList<>();
        for (CreditSetting t : foundAllVersionsWhizSpecificsPercentDeposit) {
            double costCarAfterPayDeposit = calculateCostCarAfterPayDeposit(costCar, deposit);
            double monthlyPayment = calculateMonthlyPayment(t, costCarAfterPayDeposit);
            CreditSettingDTO creditSettingDTO = new CreditSettingDTO().convertCreditSettingToDTO(t);
            creditSettingDTO.setMonthlyPayment(monthlyPayment);
            transformationToObjArrayList.add(creditSettingDTO);
        }
        return transformationToObjArrayList;
    }
//    end:    /morePayInMonth


    //    найти процент первого взноса от общей стоимости всей машины
    private Integer foundPercentDepositFromCostCar(Integer calculateDepositPercent) {
        List<CreditSetting> creditSettingList = creditSettingRepository.findAllByMonth(12);
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
