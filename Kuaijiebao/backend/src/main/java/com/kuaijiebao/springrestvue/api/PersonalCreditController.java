package com.kuaijiebao.springrestvue.api;

import com.kuaijiebao.springrestvue.domain.PersonalCredit;
import com.kuaijiebao.springrestvue.service.PersonalCreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/debt")
public class PersonalCreditController {
    @Autowired
    PersonalCreditService personalCreditService;

    @GetMapping("/api/user/GetPersonalCreditActivity/{id}")
    public PersonalCredit getByUserId(@PathVariable Long id){ return personalCreditService.findByUserId(id);}
}
