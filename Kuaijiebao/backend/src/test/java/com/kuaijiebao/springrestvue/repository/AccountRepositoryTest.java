package com.kuaijiebao.springrestvue.repository;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import com.kuaijiebao.springrestvue.domain.Account;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AccountRepository repository;

    Account austin=new Account("austion","password",11L);

    @Before
    public void initialize() {
        entityManager.persistAndFlush(austin);
    }


    //
    //findByAccountId
    @Test
    public void GivenValidAccount_whenFindByAccountId_thenReturnAccount() {
        Account found = repository.findByAccountId(austin.getAccountId());
        assertThat(found.getAccountId()).isEqualTo(austin.getAccountId());
    }

    //
    //findByAccountId
    @Test
    public void GivenInvalidAccountId_whenFindByAccountId_thenReturnNull() {
        Account found = repository.findByAccountId(-99L);
        assertThat(found).isNull();
    }

    //
    //findByUserId
    @Test
    public void GivenValidAccount_whenFindByUserId_thenReturnAccount() {
        Account found = repository.findByUserId(austin.getUserId());
        assertThat(found.getUserId()).isEqualTo(austin.getUserId());
    }

    //
    //findByUserId
    @Test
    public void GivenInvalidAccountId_whenFindByUserId_thenReturnNull() {
        Account found = repository.findByUserId(-99L);
        assertThat(found).isNull();
    }

    //
    //findByUsername
    @Test
    public void GivenValidAccount_whenFindByUsername_thenReturnAccount() {
        Account found = repository.findByUsername(austin.getUsername()).orElse(null);
        assertThat(found.getUsername()).isEqualTo(austin.getUsername());
    }

    //
    //findByUsername
    @Test
    public void GivenInvalidAccountId_whenFindByUsername_thenReturnNull() {
        Account found = repository.findByUsername("DoesNotExist").orElse(null);
        assertThat(found).isNull();
    }

    //
    //existsByUsername
    @Test
    public void GivenValidAccount_whenExistsByUsername_thenReturnTrue() {
        boolean found = repository.existsByUsername(austin.getUsername());
        assertThat(found).isTrue();
    }

    //
    //existsByUsername
    @Test
    public void GivenInValidAccount_whenExistsByUsername_thenReturnFalse() {
        boolean found = repository.existsByUsername("DoesNotExist");
        assertThat(found).isFalse();
    }

    //
    //findByUsernameAndPassword
    @Test
    public void  GivenValidAccount_whenFindByUsernameAndPassword_thenReturnAccount() {
        Account found = repository.findByUsernameAndPassword(austin.getUsername(),austin.getPassword());
        assertThat(found.getAccountId()).isEqualTo(austin.getAccountId());
    }

    //
    //findByUsernameAndPassword
    @Test
    public void  GivenInValidAccount_whenFindByUsernameAndPassword_thenReturnNull() {
        Account found = repository.findByUsernameAndPassword("DoesNotExist",austin.getPassword());
        assertThat(found).isNull();
    }

    //
    //save
    @Test
    public void givenValidAccount_whenSave_thenReturnAccount() {
        Account jenny=new Account("jenny","password",12L);
        Account fromDb = repository.save(jenny);
        assertThat(fromDb.getAccountId()).isEqualTo(jenny.getAccountId());
    }

    //
    //deleteByUserId
    @Test
    public void givenValidUser_whenDeleteByAccountId_thenSuccess() {
        repository.deleteByAccountId(austin.getUserId());
    }

}
