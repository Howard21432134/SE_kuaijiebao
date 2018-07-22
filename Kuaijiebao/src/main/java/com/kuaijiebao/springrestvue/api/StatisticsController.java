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


@CrossOrigin
@RestController
@RequestMapping("/api/statistics")
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


    @GetMapping(path = "/DebtStat/{userId}")
    public List<Debt> getDebtStatByUserId(@PathVariable Long userId,
                                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date since,
                                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date until) {
        return debtService.showDebtByUserBetween(userId,since,until);

    }

    @GetMapping(path = "/FPDRStat/{userId}")
    public List<FPDR> getFPDRStatByUserId(@PathVariable Long userId,
                                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date since,
                                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date until) {
        return fpdrService.showFPDRByUserBetween(userId, since, until);
    }

}
