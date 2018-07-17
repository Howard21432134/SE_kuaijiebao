package com.kuaijiebao.springrestvue.service;

import com.kuaijiebao.springrestvue.repository.FPDealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.kuaijiebao.springrestvue.domain.FP;
import com.kuaijiebao.springrestvue.domain.FPDeal;
import com.kuaijiebao.springrestvue.domain.FPDR;
import com.kuaijiebao.springrestvue.repository.FPRepository;
import com.kuaijiebao.springrestvue.repository.FPDRRepository;

@Service
public class FPDealService {

    @Autowired
    FPDealRepository fpDealRepository;

    public List<FPDeal> ShowFPDealActivity(Long id){ return fpDealRepository.findAllByUserId(id);}

    public FPDeal FPDealActivity(Long id){ return fpDealRepository.findOneById(id);}

    public FPDeal AddFPDealActivity(FPDeal fp){ return fpDealRepository.save(fp);}

    public void DeleteFPDealActivity(Long id){ fpDealRepository.deleteById(id);}

}
