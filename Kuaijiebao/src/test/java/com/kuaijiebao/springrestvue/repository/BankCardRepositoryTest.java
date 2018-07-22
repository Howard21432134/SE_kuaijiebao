package com.kuaijiebao.springrestvue.repository;


import org.junit.Test;

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
import com.kuaijiebao.springrestvue.domain.BankCard;


@RunWith(SpringRunner.class)
@DataJpaTest
public class BankCardRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BankCardRepository bankCardRepository;

    @Test
    public void whenFindOnesById_thenReturnBankCards() {
        BankCard bankcardOne = new BankCard("1111222233334444",11L);
        BankCard bankcardTwo = new BankCard("5555666677778888",11L);
        entityManager.persistAndFlush(bankcardOne);
        entityManager.persistAndFlush(bankcardTwo);
        List<BankCard> fromDb = bankCardRepository.findByUserId(11L);
        assertThat(fromDb).hasSize(2).extracting(BankCard::getCardNum).contains("1111222233334444", "5555666677778888");
    }

    @Test
    public void whenInvalidId_thenReturnEmpty() {
        List<BankCard> fromDb = bankCardRepository.findByUserId(-11l);
        assertThat(fromDb).isEmpty();
    }

    @Test
    public void givenBankCard_whenSave_thenReturnBankCard() {
        BankCard myCard=new BankCard("1111222233334444",11L);
        BankCard fromDb = bankCardRepository.save(myCard);
        assertThat(fromDb.getCardNum()).isEqualTo(myCard.getCardNum());
    }

    @Test
    public void givenBankCard_whenDeleteWithId_thenReturnEmpty() {
        BankCard myCard=new BankCard("1111222233334444",11L);
        entityManager.persistAndFlush(myCard);

        bankCardRepository.deleteByCardNum("1111222233334444");
        List<BankCard> fromDb = bankCardRepository.findByUserId(11L);
        assertThat(fromDb).isEmpty();
    }

}