package com.kuaijiebao.springrestvue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.kuaijiebao.springrestvue.domain.FPDeal;

@Repository
public interface FPDealRepository extends JpaRepository<FPDeal,Long>{

    public List<FPDeal> findAllByUserId(Long id);

    public FPDeal findOneById(Long id);
}
