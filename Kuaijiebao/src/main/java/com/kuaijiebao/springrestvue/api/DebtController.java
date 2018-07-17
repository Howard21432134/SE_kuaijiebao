package com.kuaijiebao.springrestvue.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Date;

import com.kuaijiebao.springrestvue.domain.Debt;
import com.kuaijiebao.springrestvue.domain.User;
import com.kuaijiebao.springrestvue.service.DebtService;
import com.kuaijiebao.springrestvue.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/api/debt")
public class DebtController {
    @Autowired
    UserService userService;

    @Autowired
    DebtService debtService;


    @PostMapping(path="/AddDebtActivity/User={id}&Sum={sum}&EDTime={EDTime}&Content={Content}")
    public void AddDebtActivity(@PathVariable Long id,@PathVariable Long sum,@PathVariable Date EDTime,@PathVariable String Content){
        Debt debt = new Debt();
        debt.setUser_id(id);
        debt.setSum(sum);
        debt.setExpect_discharge_time(EDTime);
        debt.setContent(Content);
        debt.setType(true);
        debt.setState(1);
        debt.setWhether_discharge(false);
        debt.setWhether_succeed(false);
        debtService.AddDebtActivity(debt);
    }

    @GetMapping(path="/ShowDebtActivity/User={id}")
    public List<Debt> ShowDebtbyUserActivity(@PathVariable Long id){
        return debtService.ShowDebtbyUserActivity(id);
    }

    @GetMapping(path="DebtDetailActivity/Debt={id}")
    public Debt DebtDetailActivity(@PathVariable Long id){
        return debtService.DebtDetailActivity(id);
    }

    @PutMapping(path="/EditDebtActivity/Debt={id}&Sum={sum}&EDTime={EDTime}&Content={Content}")
    public Boolean EditDebtActivity(@PathVariable Long id,@PathVariable Long sum,@PathVariable Date EDTime,@PathVariable String Content){
        Debt debt = debtService.DebtDetailActivity(id);
        if(debt.getState()>1){ return false;}
        debt.setDischarge_time(EDTime);
        debt.setSum(sum);
        debt.setContent(Content);
        debtService.AddDebtActivity(debt);
        return true;
    }

    @DeleteMapping(path="/DeleteDebtActivity/Debt={id}")
    public Boolean DeleteDebtActivity(@PathVariable Long id){ return debtService.DeleteDebtActivity(id);}

    @PutMapping(path="/DischargeDebtActivity/Debt={id}&Time={time}")
    public Boolean DischargeDebtActivity(@PathVariable Long id,@PathVariable Date time){
        return debtService.DischargeDebtActivity(id,time);
    }

    @GetMapping(path="/ShowDebtActivity/Owner={id}")
    public List<Debt> ShowDebtybyOwnerActivity(Long id){ return debtService.ShowDebtbyOwnerActivity(id);}

    @GetMapping(path="/ShowDebtUnsucceedActivity")
    public List<Debt> ShowDebtUnsucceedActivity(){ return debtService.ShowDebtUnsucceedActivity();}

    @PutMapping(path="/EditDebtActivity/debt={Debt_id}&Owner={id}&RTime={time}&Rate={rate}")
    public Boolean ReceiveDebtActivity(@PathVariable Long Debt_id,@PathVariable Long id,@PathVariable Date time,@PathVariable Float rate){
        Debt debt = debtService.DebtDetailActivity(Debt_id);
        if(debt.getType()==false){ return false;}
        debt.setWhether_succeed(true);
        debt.setSucceed_time(time);
        debt.setOwner_id(id);
        debt.setType(false);
        debt.setState(2);
        debt.setRate(rate);
        debtService.AddDebtActivity(debt);
        return true;
    }

    @GetMapping(path="/ShowDebtOnsaleActivity")
    public List<Debt>ShowDebtOnsaleActivity(){ return debtService.ShoDebtOnsaleActivity();}

    @PutMapping(path="/EditDebtActivity/Debt={Debt_id}&Owner={id}")
    public Boolean BuyDebtActivity(@PathVariable Long Debt_id,@PathVariable Long id){
        Debt debt=debtService.DebtDetailActivity(Debt_id);
        if(debt.getType()==false){ return false;}
        debt.setOwner_id(id);
        debtService.AddDebtActivity(debt);
        return true;
    }

    @PutMapping(path="/EditDebtActivity/Debt={Debt_id}&Type=true")
    public Boolean SaleDebtActivity(@PathVariable Long Debt_id){
        Debt debt=debtService.DebtDetailActivity(Debt_id);
        if(debt.getType()!=false||debt.getState()!=2){ return false;}
        debt.setType(true);
        debtService.AddDebtActivity(debt);
        return true;
    }


}