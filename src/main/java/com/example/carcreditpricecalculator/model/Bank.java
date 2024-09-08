package com.example.carcreditpricecalculator.model;

import jakarta.persistence.*;

import java.util.List;

@Table(name = "bank")
@Entity
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nameBank;

    @OneToMany(mappedBy = "bank")
    private List<CreditSetting> creditSettings;

    @ManyToOne
    @JoinTable(name="dealers_banks",
            joinColumns=@JoinColumn(name="bank_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="car_dealer_id", referencedColumnName="id"))
//    @JoinColumn(name = "carDealer_id")
    private CarDealer carDealer;

    public Integer getId() {
        return id;
    }

    public String getNameBank() {
        return nameBank;
    }

    public void setNameBank(String nameBank) {
        this.nameBank = nameBank;
    }

    public List<CreditSetting> getCreditSettings() {
        return creditSettings;
    }

    public void setCreditSettings(List<CreditSetting> creditSettings) {
        this.creditSettings = creditSettings;
    }

    public CarDealer getCarDealer() {
        return carDealer;
    }

    public void setCarDealer(CarDealer carDealer) {
        this.carDealer = carDealer;
    }
}
