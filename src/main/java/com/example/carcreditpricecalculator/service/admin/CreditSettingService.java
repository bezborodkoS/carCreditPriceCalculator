package com.example.carcreditpricecalculator.service.admin;

import com.example.carcreditpricecalculator.model.CreditSetting;
import com.example.carcreditpricecalculator.repository.BankRepository;
import com.example.carcreditpricecalculator.repository.CarDealerRepository;
import com.example.carcreditpricecalculator.repository.CreditSettingRepository;
import org.springframework.stereotype.Service;

@Service
public class CreditSettingService {

    private final CarDealerRepository carDealerRepository;
    private final BankRepository bankRepository;
    private final CreditSettingRepository creditSettingRepository;

    public CreditSettingService(CreditSettingRepository creditSettingRepository, CarDealerRepository carDealerRepository, BankRepository bankRepository) {
        this.creditSettingRepository = creditSettingRepository;
        this.carDealerRepository = carDealerRepository;
        this.bankRepository = bankRepository;
    }

    public boolean addSettingsPercent(Integer month, Integer percentDeposit, Double percent, String nameBank, String nameCarDealer) {
        CreditSetting creditSetting;
        System.out.println("come");
        if (creditSettingRepository.findTimeByMonthAndPercentDeposit(month, percentDeposit) == null &&
                carDealerRepository.findByNameCarDealer(nameCarDealer) != null &&
                bankRepository.findByNameBank(nameBank) != null) {
            System.out.println("come to addSetting");
            creditSetting = new CreditSetting();
            creditSetting.setMonth(month);
            creditSetting.setPercentDeposit(percentDeposit);
            creditSetting.setPercent(percent);
            creditSetting.setCarDealer(carDealerRepository.findByNameCarDealer(nameCarDealer));
            creditSetting.setBank(bankRepository.findByNameBank(nameBank));
            creditSettingRepository.save(creditSetting);
            return true;
        }

        return false;
    }
}
