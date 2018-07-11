package com.kuaijiebao.springrestvue.repository;

import static org.junit.Assert.*;

import static org.junit.Assert.*;

import com.kuaijiebao.springrestvue.domain.User;
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

import static org.assertj.core.api.Assertions.assertThat;

import com.kuaijiebao.springrestvue.domain.Account;


@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;


    @Test
    public void whenFindById_thenReturnAccount() {

        User satoshi = new User("sato","satoshi","sophomore",
                "student",0,"SJTU", "Hello",
                "11122223333", "example@qq.com");
        entityManager.persistAndFlush(satoshi);
        Account satoAccount=new Account(satoshi.getId(),"sato","password");
        entityManager.persistAndFlush(satoAccount);

        Account fromDb = accountRepository.findOneById(satoAccount.getId());

        assertThat(fromDb.getId()).isEqualTo(satoAccount.getId());
    }

    @Test
    public void whenInvalidId_thenReturnNull() {

        Account fromDb = accountRepository.findById(-11l).orElse(null);
        assertThat(fromDb).isNull();
    }

    @Test
    public void whenFindOneByUsernameAndPassword_thenReturnAccount() {

        //
        //Mock SignUp
        User satoshi = new User("sato","satoshi","sophomore",
                "student",0,"SJTU", "Hello",
                "11122223333", "example@qq.com");
        entityManager.persistAndFlush(satoshi);
        Account satoAccount=new Account(satoshi.getId(),"sato","password");
        entityManager.persistAndFlush(satoAccount);


        Account found = accountRepository.findOneByUsernameAndPassword(satoAccount.getUsername(),satoAccount.getPassword());

        assertThat(found.getId()).isEqualTo(satoshi.getId());
    }

    @Test
    public void whenInvalidUsernameAndValidPassword_thenReturnNull() {
        //
        //Mock SignUp
        User satoshi = new User("sato","satoshi","sophomore",
                "student",0,"SJTU", "Hello",
                "11122223333", "example@qq.com");
        entityManager.persistAndFlush(satoshi);
        Account satoAccount=new Account(satoshi.getId(),"sato","password");
        entityManager.persistAndFlush(satoAccount);

        Account fromDb = accountRepository.findOneByUsernameAndPassword("doesNotExist",satoAccount.getPassword());
        assertThat(fromDb).isNull();
    }


    @Test
    public void whenValidUsernameAndInvalidPassword_thenReturnNull() {
        //
        //Mock SignUp
        User satoshi = new User("sato","satoshi","sophomore",
                "student",0,"SJTU", "Hello",
                "11122223333", "example@qq.com");
        entityManager.persistAndFlush(satoshi);
        Account satoAccount=new Account(satoshi.getId(),"sato","password");
        entityManager.persistAndFlush(satoAccount);

        Account fromDb = accountRepository.findOneByUsernameAndPassword(satoAccount.getUsername(),"doesNotExist");
        assertThat(fromDb).isNull();
    }

    @Test
    public void whenInvalidUsernameAndInvalidPassword_thenReturnNull() {
        //
        //Mock SignUp
        User satoshi = new User("sato","satoshi","sophomore",
                "student",0,"SJTU", "Hello",
                "11122223333", "example@qq.com");
        entityManager.persistAndFlush(satoshi);
        Account satoAccount=new Account(satoshi.getId(),"sato","password");
        entityManager.persistAndFlush(satoAccount);

        Account fromDb = accountRepository.findOneByUsernameAndPassword("doesNotExist","doesNotExist");
        assertThat(fromDb).isNull();
    }

}