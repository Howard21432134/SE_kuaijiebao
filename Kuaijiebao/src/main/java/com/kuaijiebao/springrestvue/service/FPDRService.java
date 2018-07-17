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
public class FPDRService {
    @Autowired
    FPDRRepository fpdrRepository;

    public FPDR AddFPDRActivity(FPDR fpdr){ return fpdrRepository.save(fpdr);}

    public List<FPDR> ShowFPDRByUserActivity(Long id){ return fpdrRepository.findAllByUserId(id);}

    public List<FPDR> ShowFPDRByProductActivity(Long id){ return fpdrRepository.findAllByProductId(id);}

    public FPDR FPDRDetailActivity(Long id){ return fpdrRepository.findOneById(id);}
}
