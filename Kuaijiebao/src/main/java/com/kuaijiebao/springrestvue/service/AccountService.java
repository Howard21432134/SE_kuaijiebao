package com.kuaijiebao.springrestvue.service;

import com.kuaijiebao.springrestvue.domain.Account;
import com.kuaijiebao.springrestvue.domain.User;
import com.kuaijiebao.springrestvue.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public List<Account> findAll() {
        return accountRepository.findAll();
    }
    public Account findOneById(Long id) { return accountRepository.findOneById(id); }
    public Account findOneByUsernameAndPassword(String username, String password) {
        return accountRepository.findOneByUsernameAndPassword(username,password);
    }
    public Account create(Account account) {
        return accountRepository.save(account);
    }
    public Account update(Account account) {
        return accountRepository.save(account);
    }
}
