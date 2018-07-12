package com.kuaijiebao.springrestvue.service;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.kuaijiebao.springrestvue.domain.FP;
import com.kuaijiebao.springrestvue.repository.FPRepository;
@Service
public class FPService {

    @Autowired
    FPRepository fpRepository;

    public List<FP> ShowFPActivity() { return fpRepository.findAll();}

    public FP FPDetailActivity(Long id){ return fpRepository.findOneById(id);}

    public void AddFPActivity(FP fp){ fpRepository.save(fp);}

}