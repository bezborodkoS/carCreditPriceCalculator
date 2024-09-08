package com.example.carcreditpricecalculator.model.dto;


import com.example.carcreditpricecalculator.model.CreditSetting;

public class CreditSettingDTO {



    private int countMonthInYear;
    private int percentDeposit;
    private  double percent;

    private double monthlyPayment;

    public CreditSettingDTO() {
    }

    public CreditSettingDTO convertCreditSettingToDTO(CreditSetting creditSetting){
        System.out.println("come to convert");
        CreditSettingDTO creditSettingDTO = new CreditSettingDTO();
        creditSettingDTO.setCountMonthInYear(creditSetting.getMonth());
        creditSettingDTO.setPercentDeposit(creditSetting.getPercentDeposit());
        creditSettingDTO.setPercent(creditSetting.getPercent());
        return creditSettingDTO;
    }

    public CreditSetting convertDTOToCreditSetting(CreditSettingDTO creditSettingDTO){
        CreditSetting creditSetting = new CreditSetting();
        creditSetting.setMonth(creditSettingDTO.getCountMonthInYear());
        creditSetting.setPercentDeposit(creditSettingDTO.getPercentDeposit());
        creditSetting.setPercent(creditSettingDTO.getPercent());
        return creditSetting;
    }


    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public int getCountMonthInYear() {
        return countMonthInYear;
    }

    public int getPercentDeposit() {
        return percentDeposit;
    }

    public double getPercent() {
        return percent;
    }

    public void setCountMonthInYear(int countMonthInYear) {
        this.countMonthInYear = countMonthInYear;
    }

    public void setPercentDeposit(int percentDeposit) {
        this.percentDeposit = percentDeposit;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    @Override
    public String toString() {
        return "TransformationToObj{" +
                "Количество месяцев=" + countMonthInYear +
                ", Разовая оплата от общей стоимости автомобиля=" + percentDeposit +
                ", Процентная ставка в месяц=" + percent +
                ", ежемесячный платеж=" + monthlyPayment +
                '}';
    }

    //    public TransformationToObj returnTransformation(Year year, double foundPercentDeposit){
//        TransformationToObj transformationToObj = new TransformationToObj();
//        for (Map.Entry<Integer, PercentDepositAllPercent> entry: year.getYear().entrySet()){
//            transformationToObj.setCountMonthInYear(entry.getKey());
//            for (Map.Entry<Integer, Double> percentDepositAllPercent: entry.getValue().getPercentDepositAllPercent().entrySet()) {
//                transformationToObj.setPercentDeposit(percentDepositAllPercent.getKey());
//                transformationToObj.setPercent(percentDepositAllPercent.getValue());
//            }
//        }
//        return transformationToObj;
//    }
}
