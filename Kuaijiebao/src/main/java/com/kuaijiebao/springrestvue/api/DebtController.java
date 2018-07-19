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


    @PostMapping(path="/AddDebtActivity")
    public void AddDebtActivity(@RequestBody Debt debt){
        debtService.AddDebtActivity(debt);
    }

    @GetMapping(path="/ShowDebtByUserActivity")
    public List<Debt> ShowDebtByUserActivity(@RequestBody User user){
        return debtService.ShowDebtbyUserActivity(user.getId());
    }

    @GetMapping(path="/DebtDetailActivity/{id}")
    public Debt DebtDetailActivity(@PathVariable Long id){
        return debtService.DebtDetailActivity(id);
    }

    @PutMapping(path="/EditDebtActivity")
    public void EditDebtActivity(@RequestBody Debt debt){
        debtService.AddDebtActivity(debt);
    }

    @DeleteMapping(path="/DeleteDebtActivity/{id}")
    public Boolean DeleteDebtActivity(@PathVariable Long id){ return debtService.DeleteDebtActivity(id);}

    @PutMapping(path="/DischargeDebtActivity/Debt={id}&Time={time}")
    public Boolean DischargeDebtActivity(@PathVariable Long id,@PathVariable Date time){
        return debtService.DischargeDebtActivity(id,time);
    }

    @GetMapping(path="/ShowDebtByOwnerActivity/{id}")
    public List<Debt> ShowDebtByOwnerActivity(@PathVariable  Long id){ return debtService.ShowDebtbyOwnerActivity(id);}

    @GetMapping(path="/ShowDebtUnsucceedActivity")
    public List<Debt> ShowDebtUnsucceedActivity(){ return debtService.ShowDebtUnsucceedActivity();}

    @PutMapping(path="/EditDebtActivity/debt={DebtId}&Owner={id}&RTime={time}&Rate={rate}")
    public Boolean ReceiveDebtActivity(@PathVariable Long DebtId,@PathVariable Long id,@PathVariable Date time,@PathVariable Float rate){
        Debt debt = debtService.DebtDetailActivity(DebtId);
        if(debt.getType()==false){ return false;}
        debt.setWhetherSucceed(true);
        debt.setSucceedTime(time);
        debt.setOwnerId(id);
        debt.setType(false);
        debt.setState(2);
        debt.setRate(rate);
        debtService.AddDebtActivity(debt);
        return true;
    }

    @GetMapping(path="/ShowDebtOnsaleActivity")
    public List<Debt>ShowDebtOnsaleActivity(){ return debtService.ShoDebtOnsaleActivity();}

    @PutMapping(path="/EditDebtActivity/Debt={DebtId}&Owner={id}")
    public Boolean BuyDebtActivity(@PathVariable Long DebtId,@PathVariable Long id){
        Debt debt=debtService.DebtDetailActivity(DebtId);
        if(debt.getType()==false){ return false;}
        debt.setOwnerId(id);
        debtService.AddDebtActivity(debt);
        return true;
    }

    @PutMapping(path="/EditDebtActivity/Debt={DebtId}&Type=true")
    public Boolean SaleDebtActivity(@PathVariable Long DebtId){
        Debt debt=debtService.DebtDetailActivity(DebtId);
        if(debt.getType()!=false||debt.getState()!=2){ return false;}
        debt.setType(true);
        debtService.AddDebtActivity(debt);
        return true;
    }


}