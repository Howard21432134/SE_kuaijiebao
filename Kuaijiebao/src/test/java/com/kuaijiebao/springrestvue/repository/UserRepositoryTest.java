package com.kuaijiebao.springrestvue.repository;

import static org.junit.Assert.*;

import com.kuaijiebao.springrestvue.domain.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import com.kuaijiebao.springrestvue.domain.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenFindOneById_thenReturnUser() {
        User satoshi = new User("sato","satoshi","sophomore",
                "student",0,"SJTU", "Hello",
                "11122223333", "example@qq.com");
        entityManager.persistAndFlush(satoshi);

        User found = userRepository.findOneByUserId(satoshi.getUserId());

        assertThat(found.getName()).isEqualTo(satoshi.getName());
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        User fromDb = userRepository.findOneByUserId(-99L);
        assertThat(fromDb).isNull();
    }

    @Test
    public void whenFindOneByName_thenReturnUser() {
        User satoshi = new User("sato","satoshi","sophomore",
                                "student",0,"SJTU", "Hello",
                            "11122223333", "example@qq.com");
        entityManager.persistAndFlush(satoshi);

        User found = userRepository.findOneByName(satoshi.getName());

        assertThat(found.getName()).isEqualTo(satoshi.getName());
    }

    @Test
    public void whenInvalidName_thenReturnNull() {
        User fromDb = userRepository.findOneByName("doesNotExist");
        assertThat(fromDb).isNull();
    }

    @Test
    public void givenValidUser_whenSave_thenReturnUser() {

        User satoshi = new User("sato","satoshi","sophomore",
                "student",0,"SJTU", "Hello",
                "11122223333", "example@qq.com");

        User fromDb = userRepository.save(satoshi);
        assertThat(fromDb.getName()).isEqualTo(satoshi.getName());
    }

    @Test
    public void givenValidUser_whenDeleteById_thenReturnNull() {
        User satoshi = new User("sato","satoshi","sophomore",
                "student",0,"SJTU", "Hello",
                "11122223333", "example@qq.com");
        entityManager.persistAndFlush(satoshi);
        userRepository.deleteById(satoshi.getUserId());
        User fromDb = userRepository.findOneByUserId(satoshi.getUserId());
        assertThat(fromDb).isNull();
    }

}