package com.kuaijiebao.springrestvue.api;


import com.kuaijiebao.springrestvue.domain.BankCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import com.kuaijiebao.springrestvue.domain.User;
import com.kuaijiebao.springrestvue.domain.Account;
import com.kuaijiebao.springrestvue.service.UserService;
import com.kuaijiebao.springrestvue.service.AccountService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
public class AccountController {

    @Autowired
    UserService userService;

    @Autowired
    AccountService accountService;

    @GetMapping(path = "/v1/accounts")
    public List<Account> getAllAccounts() {
        List<Account> accounts = accountService.findAll();
        return accounts;
    }

    @GetMapping(path = "/v1/accounts/{userId}", produces = {"application/hal+json"})
    public Account getByUserId(@PathVariable Long userId) {
        return accountService.findByUserId(userId);
    }


    //
    //can ONLY change the password field
    @PutMapping(path = "/v1/accounts/{userId}/password")
    public Account updatePassword(@PathVariable Long userId, @RequestBody Account account) {
        Account newAccount=accountService.findByAccountId(userId);
        newAccount.setPassword(account.getPassword());
        return accountService.update(newAccount);
    }


    @PostMapping(path ="/v1/accounts")
    @ResponseStatus(HttpStatus.CREATED)
    public Account createUserNewCard(@RequestBody Account account) {
        return accountService.save(account);
    }



}
