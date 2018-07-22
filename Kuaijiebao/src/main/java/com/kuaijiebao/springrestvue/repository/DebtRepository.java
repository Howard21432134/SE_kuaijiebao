package com.kuaijiebao.springrestvue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kuaijiebao.springrestvue.domain.Debt;

import java.util.List;
import java.util.Date;

@Repository
public interface DebtRepository extends JpaRepository<Debt, Long>{

    public Debt findOneById(Long id);
    public List<Debt> findAllByUserId(Long id);
    public void deleteById(Long id);
    public List<Debt> findAllByOwnerId(Long id);
    @Query("select debt from Debt debt where debt.state=1")
    public List<Debt> findAllUnsucceed();
    @Query("select debt from Debt debt where debt.type=true and debt.state=2")
    public List<Debt> findAllOnsale();
    @Query("select debt from Debt debt where debt.userId=?1 AND debt.succeedTime BETWEEN ?2 And ?3 Order By debt.succeedTime")
    public List<Debt> findByTimeAtBetweenOrderByTimeAndIdIs(Long id, Date since, Date until);
}