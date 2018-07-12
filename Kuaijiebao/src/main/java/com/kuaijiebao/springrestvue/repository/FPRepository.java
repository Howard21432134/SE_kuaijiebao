package com.kuaijiebao.springrestvue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.kuaijiebao.springrestvue.domain.FP;
@Repository
public interface FPRepository extends JpaRepository<FP, Long>{

    public List<FP> findAll();

    public FP findOneById(Long id);

}
