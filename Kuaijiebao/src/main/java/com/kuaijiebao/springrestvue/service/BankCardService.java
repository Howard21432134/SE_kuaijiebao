package com.kuaijiebao.springrestvue.service;

import com.kuaijiebao.springrestvue.domain.BankCard;
import com.kuaijiebao.springrestvue.domain.User;
import com.kuaijiebao.springrestvue.repository.BankCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankCardService {

    @Autowired
    BankCardRepository bankCardRepository;

    public List<BankCard> findAll() {
        return bankCardRepository.findAll();
    }

    public List<BankCard> findOnesByUserId(Long userId) { return bankCardRepository.findOnesById(userId); }

    public BankCard addCard(BankCard bankCard) { return bankCardRepository.save(bankCard); }

    public void deleteByCardNum(String cardNum) {
        bankCardRepository.deleteByCardNum(cardNum);
    }


}
