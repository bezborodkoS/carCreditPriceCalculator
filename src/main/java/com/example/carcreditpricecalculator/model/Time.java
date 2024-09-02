package com.example.carcreditpricecalculator.model;

import jakarta.persistence.*;

import java.util.Set;

@Table(name = "time")
@Entity
public class Time {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer month;

    private Integer percentDeposit;

    private Double percent;

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
}
