package com.example.carcreditpricecalculator.repository;

import com.example.carcreditpricecalculator.model.CreditSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CreditSettingRepository extends JpaRepository<CreditSetting, Integer> {
    //    boolean findTimeByMonth(Integer month);
    CreditSetting findTimeByMonthAndPercentDepositAndPercent(Integer month, Integer percentDeposit, Double percent);
    CreditSetting findTimeByMonthAndPercentDeposit(Integer month, Integer percentDeposit);
    ArrayList<CreditSetting> findAllByMonth(Integer month);
    ArrayList<CreditSetting> findAllByPercentDeposit(Integer percentDeposit);
    ArrayList<CreditSetting> findAllByPercentDepositAndCarDealer_NameCarDealerAndBank_NameBank(Integer percentDeposit, String nameCarDealer, String nameBank);
}
