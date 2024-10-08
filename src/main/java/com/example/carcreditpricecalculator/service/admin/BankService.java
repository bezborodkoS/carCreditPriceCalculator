package com.example.carcreditpricecalculator.service.admin;

import com.example.carcreditpricecalculator.model.Bank;
import com.example.carcreditpricecalculator.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BankService {
    private final BankRepository bankRepository;

    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public boolean addBank(String nameBank){
        if (bankRepository.findByNameBank(nameBank)==null) {
            System.out.println("come to save");
            Bank bank = new Bank();
            bank.setNameBank(nameBank);
            bankRepository.save(bank);
            return true;
        }
        return false;
    }

    public ArrayList<String> allBank(){
        System.out.println("come to allBank");
        ArrayList<String> bank = new ArrayList<>();
        for (Bank s : bankRepository.findAll()) {
            bank.add(s.getNameBank());
            System.out.println(s.getNameBank());
        }
        return bank;
    }
}
