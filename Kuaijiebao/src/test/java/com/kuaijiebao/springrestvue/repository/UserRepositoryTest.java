package com.kuaijiebao.springrestvue.repository;

import static org.junit.Assert.*;

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
    public void whenFindById_thenReturnUser() {
        User satoshi = new User("sato","satoshi","sophomore",
                "student",0,"SJTU", "Hello",
                "11122223333", "example@qq.com");
        entityManager.persistAndFlush(satoshi);

        User fromDb = userRepository.findOneById(satoshi.getId());

        assertThat(fromDb.getName()).isEqualTo(satoshi.getName());
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        User fromDb = userRepository.findOneById(-11l);
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
/*
    @Test
    public void whenFindById_thenReturnEmployee() {
        Employee emp = new Employee("test");
        entityManager.persistAndFlush(emp);

        Employee fromDb = employeeRepository.findById(emp.getId()).orElse(null);
        assertThat(fromDb.getName()).isEqualTo(emp.getName());
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        Employee fromDb = employeeRepository.findById(-11l).orElse(null);
        assertThat(fromDb).isNull();
    }

    @Test
    public void givenSetOfEmployees_whenFindAll_thenReturnAllEmployees() {
        Employee alex = new Employee("alex");
        Employee ron = new Employee("ron");
        Employee bob = new Employee("bob");

        entityManager.persist(alex);
        entityManager.persist(bob);
        entityManager.persist(ron);
        entityManager.flush();

        List<Employee> allEmployees = employeeRepository.findAll();

        assertThat(allEmployees).hasSize(3).extracting(Employee::getName).containsOnly(alex.getName(), ron.getName(), bob.getName());

    }
    */
}