package com.kuaijiebao.springrestvue.api;


import com.kuaijiebao.springrestvue.domain.BankCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import com.kuaijiebao.springrestvue.domain.User;
import com.kuaijiebao.springrestvue.domain.Account;
import com.kuaijiebao.springrestvue.service.UserService;
import com.kuaijiebao.springrestvue.service.AccountService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    UserService userService;

    @Autowired
    AccountService accountService;

    @GetMapping
    public List<Account> getAll() {
        List<Account> accounts = accountService.findAll();
        return accounts;
    }

/*
    @GetMapping(path = "/validateUser")
    public Account validateUser(@RequestBody Account account) {

        return accountService.findOneByUsernameAndPassword(account.getUsername(),
                account.getPassword());
    }
*/
    //
    //can ONLY change the password field
    @PutMapping(path = "/updatePassword/{userId}")
    public Account putUserPassword(@PathVariable Long userId, @RequestBody Account account) {
        Account newAccount=accountService.findOneById(userId);
        newAccount.setPassword(account.getPassword());
        return accountService.update(newAccount);
    }


    @PostMapping(path ="/createAccount")
    @ResponseStatus(HttpStatus.CREATED)
    public Account postUserNewCard(@RequestBody Account account) {
        return accountService.create(account);
    }



}
