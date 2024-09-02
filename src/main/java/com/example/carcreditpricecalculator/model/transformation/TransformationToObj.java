package com.example.carcreditpricecalculator.model.transformation;



import java.util.Map;

public class TransformationToObj {



    private int countMonthInYear;
    private int percentDeposit;
    private  double percent;

    private double monthlyPayment;

    public TransformationToObj(int countMonthInYear, int percentDeposit, double percent, double monthlyPayment) {
        this.countMonthInYear = countMonthInYear;
        this.percentDeposit = percentDeposit;
        this.percent = percent;
        this.monthlyPayment = monthlyPayment;
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
