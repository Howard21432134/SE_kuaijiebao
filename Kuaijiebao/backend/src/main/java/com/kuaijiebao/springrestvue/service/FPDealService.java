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

    public List<FPDeal> findAllByUserId(Long id){ return fpDealRepository.findAllByUserId(id);}

    public FPDeal findByDealId(Long id){ return fpDealRepository.findByDealId(id);}

    public void save(FPDeal fpDeal){ fpDealRepository.save(fpDeal);}

    public void update(FPDeal fpDeal){ fpDealRepository.save(fpDeal);}

    public void delete(Long id){ fpDealRepository.deleteByDealId(id);}

}
