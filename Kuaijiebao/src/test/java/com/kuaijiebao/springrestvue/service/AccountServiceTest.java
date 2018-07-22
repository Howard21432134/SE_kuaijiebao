package com.kuaijiebao.springrestvue.service;

import static org.junit.Assert.*;

import com.kuaijiebao.springrestvue.domain.BankCard;
import com.kuaijiebao.springrestvue.domain.User;
import com.kuaijiebao.springrestvue.repository.UserRepository;
import com.kuaijiebao.springrestvue.domain.Account;
import com.kuaijiebao.springrestvue.repository.AccountRepository;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class AccountServiceTest {

    @TestConfiguration
    static class AccountServiceTestContextConfiguration {
        @Bean
        public AccountService accountService() {
            return new AccountService();
        }
    }

    @TestConfiguration
    static class UserServiceTestContextConfiguration {
        @Bean
        public UserService userService() {
            return new UserService();
        }
    }

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private AccountService accountService;

    @MockBean
    private AccountRepository accountRepository;



    @Before
    public void setUp() {

        //
        //Mock user signUp
        User john = new User("john","MG234143",
                "john","student",0,
                "SJTU", "Hello","11122223333",
                "john@qq.com");
        john.setUserId(11L);
        Account johnAccount=new Account("john","passwordjohn");


        User bob = new User("bob","jh23453245",
                "bob","engineer",0,
                "shanghai", "Hello","11122223333",
                "bob@qq.com");
        bob.setUserId(12L);
        Account bobAccount=new Account("bob","passwordbob");

        User alex = new User("alex","kl48394593",
                "alex","pilot",0,
                "hongkong", "Hello","11122223333",
                "bob@qq.com");
        alex.setUserId(13L);
        Account alexAccount=new Account("alex","passwordalex");


        List<Account> allAccounts = Arrays.asList(johnAccount, bobAccount, alexAccount);

        Mockito.when(accountRepository.findByAccountId(johnAccount.getAccountId())).thenReturn(johnAccount);
        Mockito.when(accountRepository.findByAccountId(alexAccount.getAccountId())).thenReturn(alexAccount);
        Mockito.when(accountRepository.findByAccountId(-99L)).thenReturn(null);
        Mockito.when(accountRepository.findByAccountId(bobAccount.getAccountId())).thenReturn(bobAccount);
        Mockito.when(accountRepository.findById(-99L)).thenReturn(null);

        Mockito.when(accountRepository.findOneByUsernameAndPassword(johnAccount.getUsername(),johnAccount.getPassword())).thenReturn(johnAccount);
        Mockito.when(accountRepository.findOneByUsernameAndPassword(bobAccount.getUsername(),bobAccount.getPassword())).thenReturn(bobAccount);
        Mockito.when(accountRepository.findOneByUsernameAndPassword("doesNotExist",bobAccount.getPassword())).thenReturn(null);
        Mockito.when(accountRepository.findOneByUsernameAndPassword(alexAccount.getUsername(),alexAccount.getPassword())).thenReturn(alexAccount);
        Mockito.when(accountRepository.findOneByUsernameAndPassword(alexAccount.getPassword(),"doesNotExist")).thenReturn(null);

        Mockito.when(accountRepository.findAll()).thenReturn(allAccounts);

        Mockito.when(accountRepository.save(johnAccount)).thenReturn(johnAccount);

    }

    /*
    @Test
    public void whenValidId_thenAccountShouldBeFound() {
        Account fromDb = accountService.findOneById(11L);
        assertThat(fromDb.getId()).isEqualTo(11L);

        //verifyFindByIdIsCalledOnce();
    }

    @Test
    public void whenValidUsernameAndPassword_thenAccountShouldBeFound() {
        Account fromDb = accountService.findOneByUsernameAndPassword("john","passwordjohn");
        assertThat(fromDb.getId()).isEqualTo(11L);

        //verifyFindByIdIsCalledOnce();
    }
*/
    @Test
    public void whenInValidId_thenAccountShouldNotBeFound() {
        Account fromDb = accountService.findByAccountId(-99L);
        //verifyFindByIdIsCalledOnce();
        assertThat(fromDb).isNull();
    }
/*
    @Test
    public void given3Accounts_whenGetAll_thenReturn3Records() {

        List<Account> allAccounts = accountService.findAll();
        //verifyFindAllEmployeesIsCalledOnce();
        assertThat(allAccounts).hasSize(3).extracting(Account::getId).contains(11L, 12L, 13L);
    }

    @Test
    public void GivenVaildAccount_whenCreate_thenReturnRecord() {
        Account johnAccount=new Account("john","passwordjohn");
        Account fromDb=accountService.create(johnAccount);
        assertThat(fromDb.getUsername()).isEqualTo(johnAccount.getUsername());
    }
*/
    @Test
    public void GivenVaildAccount_whenDelete_thenSuccess() {
        Account johnAccount=new Account("john","passwordjohn");
        accountService.delete(johnAccount.getAccountId());
    }

}