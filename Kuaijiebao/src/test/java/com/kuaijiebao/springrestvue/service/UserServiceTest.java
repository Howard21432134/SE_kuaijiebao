package com.kuaijiebao.springrestvue.service;

import static org.junit.Assert.*;

import com.kuaijiebao.springrestvue.domain.User;
import com.kuaijiebao.springrestvue.repository.UserRepository;


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
public class UserServiceTest {


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



    @Before
    public void setUp() {

        User john = new User("john","john",
                "MG234143","student",0,
                "SJTU", "Hello","11122223333",
                "john@qq.com");
        john.setId(11L);


        User bob = new User("bob","bob",
                "jh23453245","engineer",0,
                "shanghai", "Hello","11122223333",
                "bob@qq.com");
        bob.setId(12L);

        User alex = new User("alex","alex",
                "kl48394593","pilot",0,
                "hongkong", "Hello","11122223333",
                "bob@qq.com");
        alex.setId(13L);


        List<User> allUsers = Arrays.asList(john, bob, alex);

        Mockito.when(userRepository.findOneByName(john.getName())).thenReturn(john);
        Mockito.when(userRepository.findOneByName(alex.getName())).thenReturn(alex);
        Mockito.when(userRepository.findOneByName("wrong_name")).thenReturn(null);
        Mockito.when(userRepository.findOneById(john.getId())).thenReturn(john);
        Mockito.when(userRepository.findAll()).thenReturn(allUsers);
        Mockito.when(userRepository.findOneById(-99L)).thenReturn(null);
    }

    @Test
    public void whenValidId_thenUserShouldBeFound() {
        User fromDb = userService.findOneByUserId(11L);
        assertThat(fromDb.getName()).isEqualTo("john");

        //verifyFindByIdIsCalledOnce();
    }


    @Test
    public void whenInValidId_thenUserShouldNotBeFound() {
        User fromDb = userService.findOneByUserId(-99L);
        //verifyFindByIdIsCalledOnce();
        assertThat(fromDb).isNull();
    }

    @Test
    public void given3Employees_whenGetAll_thenReturn3Records() {

        List<User> allEmployees = userService.findAll();
        //verifyFindAllEmployeesIsCalledOnce();
        assertThat(allEmployees).hasSize(3).extracting(User::getId).contains(11L, 12L, 13L);
    }



}