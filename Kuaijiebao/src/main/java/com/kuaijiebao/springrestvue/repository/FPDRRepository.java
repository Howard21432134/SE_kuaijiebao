package com.kuaijiebao.springrestvue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.kuaijiebao.springrestvue.domain.FPDR;

@Repository
public interface FPDRRepository extends JpaRepository<FPDR, Long>{

    public FPDR findOneById(Long id);

    public List<FPDR> findAllByUserId(Long id);

    public List<FPDR> findAllByProductId(Long id);

}