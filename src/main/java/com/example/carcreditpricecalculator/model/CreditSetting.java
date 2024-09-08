package com.example.carcreditpricecalculator.model;

import jakarta.persistence.*;

@Table(name = "creditSetting")
@Entity
public class CreditSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer month;

    private Integer percentDeposit;

    private Double percent;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @ManyToOne
    @JoinColumn(name = "carDealer_id")
    private CarDealer carDealer;

//    @OneToMany(mappedBy = "time")
//    private Set<PercentDeposit> percentDeposit;


    public Integer getId() {
        return id;
    }


    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getPercentDeposit() {
        return percentDeposit;
    }

    public void setPercentDeposit(Integer percentDeposit) {
        this.percentDeposit = percentDeposit;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }


    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public CarDealer getCarDealer() {
        return carDealer;
    }

    public void setCarDealer(CarDealer carDealer) {
        this.carDealer = carDealer;
    }

    @Override
    public String toString() {
        return "CreditSetting{" +
                "id=" + id +
                ", month=" + month +
                ", percentDeposit=" + percentDeposit +
                ", percent=" + percent +
                '}';
    }
}
