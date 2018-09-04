package com.kuaijiebao.springrestvue.service;

import com.kuaijiebao.springrestvue.domain.PersonalCredit;
import com.kuaijiebao.springrestvue.repository.PersonalCreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonalCreditService {

    @Autowired
    PersonalCreditRepository personalCreditRepository;

    public PersonalCredit findByUserId(Long id){return personalCreditRepository.findByUserId(id);}

    public PersonalCredit save(PersonalCredit personalCredit){ return personalCreditRepository.save(personalCredit);}

    public PersonalCredit update(PersonalCredit personalCredit){return personalCreditRepository.save(personalCredit);}
}
