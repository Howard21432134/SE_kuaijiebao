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

    public List<FP> findAll() { return fpRepository.findAll();}

    public FP findByFpId(Long id){ return fpRepository.findByProductId(id);}

    public FP save(FP fp){ return fpRepository.save(fp);}

    public FP update(FP fp){return fpRepository.save(fp);}

    public void delete(Long id){ fpRepository.deleteByProductId(id);}

}