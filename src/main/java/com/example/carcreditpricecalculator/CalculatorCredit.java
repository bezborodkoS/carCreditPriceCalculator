package com.example.carcreditpricecalculator;

public class CalculatorCredit {


    private Double foundPercentDeposit(int deposit, double priceCar){
        return (priceCar/100)*deposit;
    }


    public String returnAnswer(int deposit, int wantContributions, double priceCar,int countMonth) {
        double foundPercentDeposit = foundPercentDeposit(deposit, priceCar);

        return null;
    }

}
