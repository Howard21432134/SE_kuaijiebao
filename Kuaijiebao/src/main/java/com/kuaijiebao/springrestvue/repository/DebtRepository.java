package com.kuaijiebao.springrestvue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kuaijiebao.springrestvue.domain.Debt;

import java.util.Date;
import java.util.List;

@Repository
public interface DebtRepository extends JpaRepository<Debt, Long>{

    public Debt findOneById(Long id);
    @Query("select debt from Debt debt where debt.user_id=:id")
    public List<Debt> findAllByUser(Long id);
    public void deleteById(Long id);
    @Query("select debt from Debt debt where debt.owner_id=:id")
    public List<Debt> findAllByOwner(Long id);
    @Query("select debt from Debt debt where debt.state=1")
    public List<Debt> findAllUnsucceed();
    @Query("select debt from Debt debt where debt.type=true and debt.state=2")
    public List<Debt> findAllOnsale();

    @Query("select debt from Debt debt where debt.user_id=?1 AND debt.succeed_time BETWEEN ?2 And ?3 Order By debt.succeed_time")
    public List<Debt> findByTimeAtBetweenOrderByTimeAndIdIs(Long id, Date since, Date until);


}