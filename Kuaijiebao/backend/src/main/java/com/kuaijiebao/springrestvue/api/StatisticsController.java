package com.kuaijiebao.springrestvue.api;


import com.kuaijiebao.springrestvue.repository.FPDRRepository;
import com.kuaijiebao.springrestvue.service.AccountService;
import com.kuaijiebao.springrestvue.service.BankCardService;
import com.kuaijiebao.springrestvue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.UsesSunHttpServer;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;
import java.util.Date;

import com.kuaijiebao.springrestvue.repository.DebtRepository;
import com.kuaijiebao.springrestvue.service.DebtService;
import com.kuaijiebao.springrestvue.service.FPDRService;
import com.kuaijiebao.springrestvue.domain.FPDR;
import com.kuaijiebao.springrestvue.domain.Debt;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class StatisticsController {

    ///
    //shold user other services donent have to stub statistics service

    @Autowired
    DebtService debtService;

    @Autowired
    FPDRService fpdrService;

    @Autowired
    FPDRRepository fpdrRepository;

    @Autowired
    DebtRepository debtRepository;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);

        binder.registerCustomEditor(
                Date.class,
                new CustomDateEditor(dateFormat, false)
        );
    }


    // @DateTimeFormat(pattern="yyyy-MM-dd")

    @GetMapping(path = "/v2/statistics/debt-stat/{userId}")
    public List<Debt> getDebtStatByUserId(@PathVariable Long userId,
                                @RequestParam(required = false)  Date since,
                                @RequestParam(required = false)  Date until) {
        return debtService.showDebtByUserBetween(userId,since,until);

    }

    @GetMapping(path = "/v2/statistics/fpdr-stat/{userId}")
    public List<FPDR> getFPDRStatByUserId(@PathVariable Long userId,
                                @RequestParam(required = false) Date since,
                                @RequestParam(required = false) Date until) {
        return fpdrService.showFPDRByUserBetween(userId, since, until);
    }

}
