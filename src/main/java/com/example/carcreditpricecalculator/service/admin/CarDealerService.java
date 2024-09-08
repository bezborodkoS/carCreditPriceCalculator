package com.example.carcreditpricecalculator.service.admin;

import com.example.carcreditpricecalculator.model.Bank;
import com.example.carcreditpricecalculator.model.CarDealer;
import com.example.carcreditpricecalculator.repository.BankRepository;
import com.example.carcreditpricecalculator.repository.CarDealerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarDealerService {
    private final CarDealerRepository carDealerRepository;
    private final BankRepository bankRepository;

    public CarDealerService(CarDealerRepository carDealerRepository, BankRepository bankRepository) {
        this.carDealerRepository = carDealerRepository;
        this.bankRepository = bankRepository;
    }

    public boolean addCarDealer(String nameCarDealer){
        if (carDealerRepository.findByNameCarDealer(nameCarDealer)==null){
            CarDealer carDealer = new CarDealer();
            carDealer.setNameCarDealer(nameCarDealer);
            carDealerRepository.save(carDealer);
            return true;
        }
        return false;
    }

    public boolean addBankToCarDealer(String nameCarDealer,String nameBank){
        if (carDealerRepository.findByNameCarDealer(nameCarDealer)!=null&&bankRepository.findByNameBank(nameBank)!=null){
//            CarDealer carDealer = carDealerRepository.findByNameCarDealer(nameCarDealer);
//            carDealer.getBanks().add(bankRepository.findByNameBank(nameBank));
//            carDealerRepository.save(carDealer);
            Bank bank = bankRepository.findByNameBank(nameBank);
            bank.setCarDealer(carDealerRepository.findByNameCarDealer(nameCarDealer));
            bankRepository.save(bank);
            return true;
        }

        return false;
    }
}
