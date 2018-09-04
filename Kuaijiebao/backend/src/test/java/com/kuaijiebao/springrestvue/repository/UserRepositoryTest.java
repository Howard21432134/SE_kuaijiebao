package com.kuaijiebao.springrestvue.repository;

import static org.junit.Assert.*;

import com.kuaijiebao.springrestvue.domain.Account;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import com.kuaijiebao.springrestvue.domain.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    User austin=new User("austin","ImIdentity","austin",
            "student",0,"ImAddress", "ImIntroduction",
            "ImPhone", "example@qq.com");

    @Before
    public void initialize() {
        entityManager.persistAndFlush(austin);
    }


    //
    //findById
    @Test
    public void GivenValidUser_whenFindById_thenReturnUser() {
        User found = userRepository.findByUserId(austin.getUserId());
        assertThat(found.getName()).isEqualTo(austin.getName());
    }

    //
    //findById
    @Test
    public void GivenInvalidUserId_whenFindById_thenReturnNull() {
        User fromDb = userRepository.findByUserId(-99L);
        assertThat(fromDb).isNull();
    }

    //
    //existsByEmail
    @Test
    public void GivenValidUser_whenExistsByEmail_thenReturnTrue() {
        boolean found = userRepository.existsByEmail(austin.getEmail());
        assertThat(found).isTrue();
    }

    //
    //existsByEmail
    @Test
    public void GivenInValidUser_whenExistsByEmail_thenReturnFalse() {
        boolean found = userRepository.existsByEmail("DoesNotExist@email.com");
        assertThat(found).isFalse();
    }

    //
    //existsByPhone
    @Test
    public void GivenValidUser_whenExistsByPhone_thenReturnTrue() {
        boolean found = userRepository.existsByPhone(austin.getPhone());
        assertThat(found).isTrue();
    }

    //
    //existsByPhone
    @Test
    public void GivenInValidUser_whenExistsByPhone_thenReturnFalse() {
        boolean found = userRepository.existsByPhone("DoesNotExistNumber");
        assertThat(found).isFalse();
    }


    //
    //save
    @Test
    public void givenValidUser_whenSave_thenReturnUser() {

        User jenny=new User("jenny","ImIdentity","jenny",
                "student",0,"ImAddress", "ImIntroduction",
                "ImPhone", "jenny@qq.com");
        User fromDb = userRepository.save(jenny);
        assertThat(fromDb.getName()).isEqualTo(jenny.getName());
    }

    //
    //deleteByUserId
    @Test
    public void givenValidUser_whenDeleteById_thenSuccess() {
        userRepository.deleteByUserId(austin.getUserId());
    }

}