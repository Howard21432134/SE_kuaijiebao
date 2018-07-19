package com.kuaijiebao.springrestvue.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Date;

import com.kuaijiebao.springrestvue.repository.DebtRepository;
import com.kuaijiebao.springrestvue.domain.Debt;

@Service
public class DebtService {
    @Autowired
    DebtRepository debtRepository;

    public void AddDebtActivity(Debt debt){ debtRepository.save(debt);}

    public List<Debt> ShowDebtbyUserActivity(Long id){ return debtRepository.findAllByUserId(id);}

    public Debt DebtDetailActivity(Long id){ return debtRepository.findOneById(id);}

    public Boolean DeleteDebtActivity(Long id){
        Debt debt=debtRepository.findOneById(id);
        if(debt.getState()!=1){ return false;}
        debtRepository.deleteById(id);
        return true;
    }

    public Boolean DischargeDebtActivity(Long id,Date time){
        Debt debt=debtRepository.findOneById(id);
        if(debt.getState()!=2){ return false;}
        debt.setState(3);
        debt.setType(false);
        debt.setDischargeTime(time);
        debt.setWhetherDischarge(true);
        debtRepository.save(debt);
        return true;
    }

    public List<Debt> ShowDebtbyOwnerActivity(Long id){ return debtRepository.findAllByOwnerId(id);}

    public List<Debt> ShowDebtUnsucceedActivity(){ return debtRepository.findAllUnsucceed();}

    public List<Debt> ShoDebtOnsaleActivity(){ return debtRepository.findAllOnsale();}

    public List<Debt> showDebtByUserBetween(Long id, Date since, Date until){
        if(since==null)since=new Date(1000000);
        if(until==null)until=new Date();
        return debtRepository.findByTimeAtBetweenOrderByTimeAndIdIs(id, since, until);
    }

}