package com.kuaijiebao.springrestvue.repository;

import com.kuaijiebao.springrestvue.domain.UserPendingValidation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserPendingValidationRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserPendingValidationRepository repository;

    UserPendingValidation pendingUser=new UserPendingValidation(
            "austin",
            "123456",
            "BANK_CARD",
            "1111-2222-3333-4444-5555"
    );

    @Before
    public void initialize() {
        entityManager.persistAndFlush(pendingUser);
    }


    //
    //findByUsername
    @Test
    public void GivenValidUser_whenFindByUsername_thenReturnUser() {
        UserPendingValidation found = repository.findByUsername(pendingUser.getUsername());
        assertThat(found.getUsername()).isEqualTo(pendingUser.getUsername());
    }

    //
    //findByUsername
    @Test
    public void GivenInvalidUserId_whenFindByUsername_thenReturnNull() {
        UserPendingValidation found = repository.findByUsername("DoesNotExist");
        assertThat(found).isNull();
    }

    //
    //existsByUsernameAndCode
    @Test
    public void GivenValidUser_whenExistsByUsernameAndCode_thenReturnTrue() {
        boolean found = repository.existsByUsernameAndCode(pendingUser.getUsername(),pendingUser.getCode());
        assertThat(found).isTrue();
    }

    //
    //existsByUsernameAndCode
    @Test
    public void GivenInValidUser_whenExistsByUsernameAndCode_thenReturnFalse() {
        boolean found = repository.existsByUsernameAndCode("DoesNotExist","DoesNotExist");
        assertThat(found).isFalse();
    }


    //
    //save
    @Test
    public void givenValidUser_whenSave_thenReturnUser() {

        UserPendingValidation jenny=new UserPendingValidation(
                "jenny",
                "123456",
                "BANK_CARD",
                "1111-2222-3333-4444-1111");
        UserPendingValidation fromDb = repository.save(jenny);
        assertThat(fromDb.getUsername()).isEqualTo(jenny.getUsername());
    }

    //
    //deleteByUsername
    @Test
    public void givenValidUser_whenDeleteByUsername_thenSuccess() {
        repository.deleteByUsername(pendingUser.getUsername());
    }


}