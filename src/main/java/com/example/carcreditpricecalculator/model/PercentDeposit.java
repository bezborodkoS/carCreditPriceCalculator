package com.example.carcreditpricecalculator.model;

import jakarta.persistence.*;

import java.util.Set;

//@Table(name = "percentDeposit")
//@Entity
//public class PercentDeposit {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Integer id;
//
//
//    private Integer percentDeposit;
//
//    @ManyToOne
//    @JoinColumn(name = "time_id")
//    private Time time;
//
//    @OneToMany(mappedBy = "percentDeposit")
//    private Set<Percent> percent;
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public Integer getPercentDeposit() {
//        return percentDeposit;
//    }
//
//    public void setPercentDeposit(Integer percentDeposit) {
//        this.percentDeposit = percentDeposit;
//    }
//
//    public Time getTime() {
//        return time;
//    }
//
//    public void setTime(Time time) {
//        this.time = time;
//    }
//
//    public Set<Percent> getPercent() {
//        return percent;
//    }
//
//    public void setPercent(Set<Percent> percent) {
//        this.percent = percent;
//    }
//}
