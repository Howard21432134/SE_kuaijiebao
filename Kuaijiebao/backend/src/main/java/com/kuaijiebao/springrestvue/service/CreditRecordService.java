package com.kuaijiebao.springrestvue.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.kuaijiebao.springrestvue.domain.CreditRecord;
import com.kuaijiebao.springrestvue.repository.CreditRecordRepository;

@Service
public class CreditRecordService {
    @Autowired
    CreditRecordRepository creditRecordRepository;

    public List<CreditRecord> ShowCreditRecordActivity(Long id){ return creditRecordRepository.findAllByUserid(id);}

    public CreditRecord CreditRecordDetailActivity(Long id){ return creditRecordRepository.findOneById(id);}

    public CreditRecord create(CreditRecord creditRecord){ return creditRecordRepository.save(creditRecord);}

    public CreditRecord update(CreditRecord creditRecord){ return creditRecordRepository.save(creditRecord);}

    public void DeleteById(Long id){ creditRecordRepository.deleteOneById(id);}

}
