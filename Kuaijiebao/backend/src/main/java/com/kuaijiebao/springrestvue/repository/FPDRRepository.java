package com.kuaijiebao.springrestvue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Date;

import com.kuaijiebao.springrestvue.domain.FPDR;

@Repository
public interface FPDRRepository extends JpaRepository<FPDR, Long>{

    public FPDR findOneById(Long id);

    public List<FPDR> findAllByUserId(Long id);

    public List<FPDR> findAllByProductId(Long id);

    @Query("select fpdr from FPDR fpdr where fpdr.userId=?1 AND fpdr.time BETWEEN ?2 And ?3 Order By fpdr.time")
    public List<FPDR> findByTimeAtBetweenOrderByTimeAndIdIs(Long id, Date since, Date until);



}