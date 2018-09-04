package com.kuaijiebao.springrestvue.service;

import com.kuaijiebao.springrestvue.repository.FPDealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import com.kuaijiebao.springrestvue.domain.FP;
import com.kuaijiebao.springrestvue.domain.FPDeal;
import com.kuaijiebao.springrestvue.domain.FPDR;
import com.kuaijiebao.springrestvue.repository.FPRepository;
import com.kuaijiebao.springrestvue.repository.FPDRRepository;

@Service
public class FPDRService {
    @Autowired
    FPDRRepository fpdrRepository;

    public FPDR save(FPDR fpdr){ return fpdrRepository.save(fpdr);}

    public FPDR update(FPDR fpdr){ return fpdrRepository.save(fpdr);}

    public List<FPDR> findAllByUserId(Long id){ return fpdrRepository.findAllByUserId(id);}

    public List<FPDR> showFPDRByUserBetween(Long id, Date since, Date until){
        if(since==null)since=new Date(1000000);
        if(until==null)until=new Date();
        return fpdrRepository.findByTimeAtBetweenOrderByTimeAndIdIs(id, since, until);
    }

    public FPDR findByRecordId(Long id){ return fpdrRepository.findByProductId(id);}
}
