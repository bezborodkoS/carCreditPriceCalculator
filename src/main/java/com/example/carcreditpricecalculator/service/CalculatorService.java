package com.example.carcreditpricecalculator.service;

//import com.example.carcreditpricecalculator.model.Percent;
//import com.example.carcreditpricecalculator.model.PercentDeposit;
//import com.example.carcreditpricecalculator.model.Time;
//import com.example.carcreditpricecalculator.repository.PercentDepositRepository;
//import com.example.carcreditpricecalculator.repository.PercentRepository;

import com.example.carcreditpricecalculator.model.Time;
import com.example.carcreditpricecalculator.model.transformation.TransformationToObj;
import com.example.carcreditpricecalculator.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CalculatorService {
    private final TimeRepository timeRepository;

    public CalculatorService(TimeRepository timeRepository) {
        this.timeRepository = timeRepository;
    }
//    private final PercentRepository percentRepository;
//    private final PercentDepositRepository percentDepositRepository;

//    public CalculatorService(PercentDepositRepository percentDepositRepository, PercentRepository percentRepository, TimeRepository timeRepository) {
//        this.percentDepositRepository = percentDepositRepository;
//        this.percentRepository = percentRepository;
//        this.timeRepository = timeRepository;
//    }


    public boolean addSettingsPercent(Integer month, Integer percentDeposit, Double percent) {
        Time time;
        if (timeRepository.findTimeByMonthAndPercentDeposit(month, percentDeposit) == null) {
            time = new Time();
            time.setMonth(month);
            time.setPercentDeposit(percentDeposit);
            time.setPercent(percent);
            timeRepository.save(time);
            return true;
        }

//        Time time;
//        PercentDeposit percentDepositNew;
//        Percent percentNew;
//        if (!findSettingInDb(month, percentDeposit, percent)) {
//            time = new Time();
//            time.setMonth(month);
//            timeRepository.save(time);
//            percentDepositNew = new PercentDeposit();
//            percentDepositNew.setPercentDeposit(percentDeposit);
//            percentDepositNew.setTime(time);
//            percentDepositRepository.save(percentDepositNew);
//            percentNew = new Percent();
//            percentNew.setPercent(percent);
//            percentNew.setPercentDeposit(percentDepositNew);
//
//            percentRepository.save(percentNew);
//            return true;
//        } else if (findMonthInDB(month)) {
//            time = timeRepository.findTimeByMonth(month);
////            if (findPercentDepositInDB(percentDeposit)){
////                percentDepositNew= percentDepositRepository.findPercentDepositByPercentDeposit(percentDeposit);
////            }else {
//                percentDepositNew = new PercentDeposit();
//                percentDepositNew.setPercentDeposit(percentDeposit);
////            }
//            percentDepositNew.setTime(time);
//            percentDepositRepository.save(percentDepositNew);
//            percentNew = new Percent();
//            percentNew.setPercent(percent);
//            percentNew.setPercentDeposit(percentDepositNew);
//            percentRepository.save(percentNew);
//            return true;
//        }
//
//        System.out.println(false);
//        return false;
//        }
        canBuyCar(1000000.0, 590000.0);
        return false;
    }

//    public boolean findSettingInDb(Integer month, Integer percentDeposit, Double percent) {
//        if (findMonthInDB(month) && findPercentDepositInDB(percentDeposit) && findPercentInDB(percent)){
//            return true;
//        }
//        return false;
//    }
//
//    private boolean findMonthInDB(Integer month) {
//        if (timeRepository.findTimeByMonth(month)!=null) {
//            return true;
//        }
//        return false;
//    }
//
//    private boolean findPercentDepositInDB(Integer percentDeposit) {
//        if (timeRepository.findTimeByMonth(percentDeposit)!=null) {
//            return true;
//        }
//        return false;
//    }

//    private boolean findPercentInDB(Double percent) {
//        if (percentRepository.findPercentByPercent(percent)!=null) {
//            return true;
//        }
//        return false;
//    }


    public ArrayList<String> canBuyCar(Double costCar, Double deposit) {
        Integer calculateDepositPercent = (int) (deposit / (costCar / 100));
        calculateDepositPercent=foundPercentDepositFromCostCar(calculateDepositPercent);
        System.out.println("car " +calculateDepositPercent);
        ArrayList<String> transformationToObjArrayList = returnAllPurchaseOptionsCar(costCar, deposit, calculateDepositPercent);
        for (String s :transformationToObjArrayList) {
            System.out.println(s);
        }
        return transformationToObjArrayList;
    }

    private ArrayList<String> returnAllPurchaseOptionsCar(Double costCar, Double deposit, Integer calculateDepositPercent) {
        List<Time> foundAllVersionsWhizSpecificsPercentDeposit = timeRepository.findAllByPercentDeposit(calculateDepositPercent);
        ArrayList<String> transformationToObjArrayList = new ArrayList<>();
        for (Time t : foundAllVersionsWhizSpecificsPercentDeposit) {
            double costCarAfterPayDeposit = costCar - deposit;
            double monthlyPayment = (costCarAfterPayDeposit+(costCarAfterPayDeposit/t.getPercent()))/t.getMonth();
            TransformationToObj transformationToObj = new TransformationToObj(t.getMonth(),t.getPercentDeposit(),t.getPercent(),monthlyPayment);
            transformationToObjArrayList.add(transformationToObj.toString());
        }
        return transformationToObjArrayList;
    }

    private Integer foundPercentDepositFromCostCar(Integer calculateDepositPercent) {
        List<Time> timeList = timeRepository.findAllByMonth(12);
        for (Time t : timeList) {
            System.out.println(t.getPercentDeposit());
        }
        for (int i = 0; i < timeList.size(); i++) {
            if (i!= timeList.size()-1) {
                if (calculateDepositPercent >= timeList.get(i).getPercentDeposit() && calculateDepositPercent < timeList.get(i + 1).getPercentDeposit()) {
                    calculateDepositPercent = timeList.get(i).getPercentDeposit();
                    return calculateDepositPercent;
                }
            }else {
                calculateDepositPercent = timeList.get(i).getPercentDeposit();
            }
        }
        return calculateDepositPercent;
    }
}
