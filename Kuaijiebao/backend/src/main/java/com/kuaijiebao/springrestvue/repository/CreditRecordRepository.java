package com.kuaijiebao.springrestvue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.kuaijiebao.springrestvue.domain.CreditRecord;

@Repository
public interface CreditRecordRepository extends JpaRepository<CreditRecord,Long>{
    public CreditRecord save(CreditRecord creditRecord);
    public CreditRecord findOneById(Long id);
    public List<CreditRecord> findAllByUserid(Long id);
    public void deleteOneById(Long id);
}
