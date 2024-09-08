package com.example.carcreditpricecalculator.model;

import jakarta.persistence.*;

@Table(name = "time")
@Entity
public class CreditSetting {
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
