package com.example.carcreditpricecalculator.model;

import jakarta.persistence.*;

import java.util.List;


@Table(name = "autodiler")
@Entity
public class CarDealer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nameCarDealer;

    @OneToMany(mappedBy = "carDealer")
    private List<Bank> banks;

    @OneToMany(mappedBy = "carDealer")
    private List<CreditSetting> creditSettings;

    public Integer getId() {
        return id;
    }

    public String getNameCarDealer() {
        return nameCarDealer;
    }

    public void setNameCarDealer(String nameCarDealer) {
        this.nameCarDealer = nameCarDealer;
    }

    public List<Bank> getBanks() {
        return banks;
    }

    public void setBanks(List<Bank> banks) {
        this.banks = banks;
    }

    public List<CreditSetting> getCreditSettings() {
        return creditSettings;
    }

    public void setCreditSettings(List<CreditSetting> creditSettings) {
        this.creditSettings = creditSettings;
    }
}
