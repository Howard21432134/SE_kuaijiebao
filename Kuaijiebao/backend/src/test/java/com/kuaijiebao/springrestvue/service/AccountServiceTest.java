package com.kuaijiebao.springrestvue.service;

import static org.junit.Assert.*;

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

    @Autowired
    private AccountService service;

    @MockBean
    private AccountRepository accountRepository;



    Account john=new Account("john","passwordjohn",11L);
    Account bob=new Account("bobby","passwordbob",12L);
    Account alex=new Account("alex","passwordalex",13L);
    List<Account> allAccounts = Arrays.asList(john, bob, alex);

    @Before
    public void setUp() {

        Mockito.when(accountRepository.findByAccountId(john.getAccountId())).thenReturn(john);
        Mockito.when(accountRepository.findByAccountId(bob.getAccountId())).thenReturn(bob);
        Mockito.when(accountRepository.findByAccountId(alex.getAccountId())).thenReturn(alex);
        Mockito.when(accountRepository.findByAccountId(-99L)).thenReturn(null);

        Mockito.when(accountRepository.findByUserId(john.getUserId())).thenReturn(john);
        Mockito.when(accountRepository.findByUserId(bob.getUserId())).thenReturn(bob);
        Mockito.when(accountRepository.findByUserId(alex.getUserId())).thenReturn(alex);
        Mockito.when(accountRepository.findByUserId(-99L)).thenReturn(null);

        Mockito.when(accountRepository.findByUsernameAndPassword(john.getUsername(),john.getPassword())).thenReturn(john);
        Mockito.when(accountRepository.findByUsernameAndPassword(bob.getUsername(),bob.getPassword())).thenReturn(bob);
        Mockito.when(accountRepository.findByUsernameAndPassword(alex.getUsername(),alex.getPassword())).thenReturn(alex);
        Mockito.when(accountRepository.findByUsernameAndPassword("doesNotExist","doesNotExist")).thenReturn(null);

        Mockito.when(accountRepository.findAll()).thenReturn(allAccounts);

        Mockito.when(accountRepository.save(john)).thenReturn(john);

    }


    //
    //findByAccountId
    @Test
    public void GivenValidAccountId_WhenFindByAccountId_thenAccountShouldBeFound() {
        Account fromDb = service.findByAccountId(john.getAccountId());
        assertThat(fromDb.getAccountId()).isEqualTo(john.getAccountId());
    }
    //
    //findByUserId
    @Test
    public void GivenValidUserId_WhenFindByUserId_thenAccountShouldBeFound() {
        Account fromDb = service.findByUserId(john.getUserId());
        assertThat(fromDb.getUserId()).isEqualTo(john.getUserId());
    }
/*
    @Test
    public void whenInValidId_thenAccountShouldNotBeFound() {
        Account fromDb = service.findByAccountId(-99L);
        //verifyFindByIdIsCalledOnce();
        assertThat(fromDb).isNull();
    }
*/

    //
    //findAll
    @Test
    public void given3ValidAccounts_whenFindAll_thenReturn3Records() {
        List<Account> allAccounts = service.findAll();
        assertThat(allAccounts).hasSize(3)
                .extracting(Account::getAccountId)
                .contains(john.getAccountId(),bob.getAccountId(),alex.getAccountId());
    }

    //
    //save
    @Test
    public void GivenValidAccount_whenSave_thenReturnRecord() {
        Account fromDb=service.save(john);
        assertThat(fromDb.getAccountId()).isEqualTo(john.getAccountId());
    }

    //
    //delete
    @Test
    public void GivenValidAccount_whenDelete_thenSuccess() {
        service.delete(john.getAccountId());
    }

}