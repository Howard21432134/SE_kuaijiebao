package com.kuaijiebao.springrestvue.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Date;

import com.kuaijiebao.springrestvue.domain.CreditRecord;
import com.kuaijiebao.springrestvue.service.CreditRecordService;

@CrossOrigin
@RestController
@RequestMapping("/api/CreditRecord")
public class CreditRecordController {

    @Autowired
    CreditRecordService creditRecordService;

    @GetMapping(path="/ShowCreditRecordActivity/User={id}")
    public List<CreditRecord> ShowCreditRecordActivity(@PathVariable Long id){ return creditRecordService.ShowCreditRecordActivity(id);}

    @GetMapping(path="/CreditRecordDetailActivity/CreditRecord={id}")
    public CreditRecord CreditRecordDetailActivity(@PathVariable Long id){ return creditRecordService.CreditRecordDetailActivity(id);}

    @PostMapping(path="/EditCreditActivity/User={id}")
    public CreditRecord CreateCreditRecordActivity(@PathVariable Long id,@RequestBody CreditRecord creditRecord){ return creditRecordService.create(creditRecord);}

    @PostMapping(path="/EditCreditActivity/CreditRecord={id}")
    public CreditRecord UpdateCreditRecordActivity(@PathVariable Long id,@RequestBody CreditRecord creditRecord){ return creditRecordService.update(creditRecord);}
}
