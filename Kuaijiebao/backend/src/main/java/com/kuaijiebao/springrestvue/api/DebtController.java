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
    public Debt AddDebtActivity(@RequestBody Debt debt){
        debt.setType(true);
        debt.setWhetherDischarge(false);
        debt.setWhetherSucceed(false);
        debt.setState(1);
        return debtService.AddDebtActivity(debt);
    }

    @GetMapping(path="/ShowDebtByUserActivity/{id}")
    public List<Debt> ShowDebtByUserActivity(@PathVariable Long id){
        return debtService.ShowDebtbyUserActivity(id);
    }

    @GetMapping(path="/DebtDetailActivity/{id}")
    public Debt DebtDetailActivity(@PathVariable Long id){
        return debtService.DebtDetailActivity(id);
    }

    @PutMapping(path="/EditDebtActivity")
    public Debt EditDebtActivity(@RequestBody Debt debt){
        return debtService.AddDebtActivity(debt);
    }

    @DeleteMapping(path="/DeleteDebtActivity/{id}")
    public Boolean DeleteDebtActivity(@PathVariable Long id){ return debtService.DeleteDebtActivity(id);}

    @PutMapping(path="/DischargeDebtActivity")
    public Debt DischargeDebtActivity(@RequestBody Debt debt){
        return debtService.AddDebtActivity(debt);
    }

    @GetMapping(path="/ShowDebtByOwnerActivity/{id}")
    public List<Debt> ShowDebtByOwnerActivity(@PathVariable  Long id){ return debtService.ShowDebtbyOwnerActivity(id);}

    @GetMapping(path="/ShowDebtUnsucceedActivity")
    public List<Debt> ShowDebtUnsucceedActivity(){ return debtService.ShowDebtUnsucceedActivity();}

    @PutMapping(path="/EditDebtActivity/Receive")
    public Debt ReceiveDebtActivity(@RequestBody Debt debt){
        return debtService.AddDebtActivity(debt);
    }

    @GetMapping(path="/ShowDebtOnsaleActivity")
    public List<Debt>ShowDebtOnsaleActivity(){ return debtService.ShoDebtOnsaleActivity();}

    @PutMapping(path="/EditDebtActivity/Debt={DebtId}&Owner={id}")
    public Boolean BuyDebtActivity(@PathVariable Long DebtId,@PathVariable Long id){
        Debt debt=debtService.DebtDetailActivity(DebtId);
        if(debt.getType()==false){ return false;}
        debt.setOwnerId(id);
        debt.setState(2);
        debt.setType(false);
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