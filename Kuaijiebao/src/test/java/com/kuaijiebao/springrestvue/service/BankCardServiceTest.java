package com.kuaijiebao.springrestvue.service;

import static org.junit.Assert.*;

import com.kuaijiebao.springrestvue.domain.Account;
import com.kuaijiebao.springrestvue.domain.User;
import com.kuaijiebao.springrestvue.repository.UserRepository;
import com.kuaijiebao.springrestvue.domain.BankCard;
import com.kuaijiebao.springrestvue.repository.BankCardRepository;


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
public class BankCardServiceTest {

    @TestConfiguration
    static class AccountServiceTestContextConfiguration {
        @Bean
        public BankCardService bankcardService() {
            return new BankCardService();
        }
    }

    @Autowired
    private BankCardService bankCardService;

    @MockBean
    private BankCardRepository bankCardRepository;



    @Before
    public void setUp() {

        BankCard bobsOne=new BankCard("1111222233334444",11L);
        BankCard bobsTwo=new BankCard("22223333444445555",11L);
        BankCard alexs=new BankCard("5555666677778888",12L);
        List<BankCard> bobBankCards=Arrays.asList(bobsOne,bobsTwo);
        List<BankCard> alexBankCards=Arrays.asList(alexs);
        List<BankCard> allBankCards = Arrays.asList(bobsOne,bobsTwo,alexs);


        Mockito.when(bankCardRepository.findOnesById(11L)).thenReturn(bobBankCards);
        Mockito.when(bankCardRepository.findOnesById(-99L)).thenReturn(null);
        Mockito.when(bankCardRepository.findOnesById(12L)).thenReturn(alexBankCards);
        Mockito.when(bankCardRepository.findAll()).thenReturn(allBankCards);
        Mockito.when(bankCardRepository.save(alexs)).thenReturn(alexs);

    }

    @Test
    public void whenValidId_thenBankCardShouldBeFound() {
        List<BankCard> fromDb = bankCardService.findOnesByUserId(11L);
        assertThat(fromDb).hasSize(2).extracting(BankCard::getCardNum).contains("1111222233334444", "22223333444445555");
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        List<BankCard> fromDb = bankCardService.findOnesByUserId(-99L);
        assertThat(fromDb).isNull();
    }

    @Test
    public void given3BankCards_whenGetAll_thenReturn3Records() {

        List<BankCard> allBankCards = bankCardService.findAll();
        assertThat(allBankCards).hasSize(3).extracting(BankCard::getCardNum).contains("1111222233334444", "5555666677778888","22223333444445555");
    }

    @Test
    public void GivenVaildBankCard_whenAddCard_thenReturnRecord() {
        BankCard alexs=new BankCard("5555666677778888",12L);
        BankCard fromDb=bankCardService.addCard(alexs);
        assertThat(fromDb.getCardNum()).isEqualTo("5555666677778888");
    }

    @Test
    public void GivenVaildBankCard_whenDeleteByCardNum_thenSuccess() {
        BankCard alexs=new BankCard("5555666677778888",12L);
        bankCardService.deleteByCardNum("5555666677778888");
    }


}