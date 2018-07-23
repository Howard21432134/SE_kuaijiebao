package com.kuaijiebao.springrestvue.api;

import com.kuaijiebao.springrestvue.SpringRestReactApplication;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.List;

import com.kuaijiebao.springrestvue.domain.Account;
import com.kuaijiebao.springrestvue.domain.BankCard;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.kuaijiebao.springrestvue.domain.User;
import com.kuaijiebao.springrestvue.repository.UserRepository;
import com.kuaijiebao.springrestvue.repository.BankCardRepository;
import com.kuaijiebao.springrestvue.repository.AccountRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = SpringRestReactApplication.class)
@AutoConfigureMockMvc
// @TestPropertySource(locations = "classpath:application-integrationtest.properties")
@AutoConfigureTestDatabase
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository repository;

    @Autowired
    private BankCardRepository bankCardRepository;

    @Autowired
    private AccountRepository accountRepository;

    @After
    public void resetDb() {
        repository.deleteAll();
        bankCardRepository.deleteAll();
        accountRepository.deleteAll();

    }



    @Test
    public void givenUser_whenGetUsersByUserId_thenStatus200() throws Exception {

        User bob = new User("bob","bob",
                "kl48394593","pilot",0,
                "hongkong", "Hello","11122223333",
                "bob@qq.com");
        repository.saveAndFlush(bob);

        mvc.perform(get("/api/v2/users/"+bob.getUserId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/hal+json"))
                .andExpect(jsonPath("$.nickname", is(bob.getNickname())));
    }


    @Test
    public void GivenValidUser_whenPutUser_thenUpdateUser() throws Exception {
        User bob = new User("bob","bob",
                "kl48394593","pilot",0,
                "hongkong", "Hello","11122223333",
                "bob@qq.com");
        repository.saveAndFlush(bob);
        mvc.perform(put("/api/v2/users/"+bob.getUserId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(bob)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/hal+json"))
                .andExpect(jsonPath("$.name", is(bob.getName())));

    }



    @Test
    public void whenValidInput_thenCreateBankCard() throws Exception {
        BankCard myCard=new BankCard("1111222233334444",11L);
        mvc.perform(post("/api/v2/users/11/bankcard")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(myCard)));

        List<BankCard> found = bankCardRepository.findAll();
        assertThat(found).extracting(BankCard::getCardNum).containsOnly(myCard.getCardNum());
    }

    @Test
    public void givenBankCards_whenGetBankCardOfUserId_thenStatus200() throws Exception {

        BankCard myCardOne=new BankCard("1111222233334444",11L);
        BankCard myCardTwo=new BankCard("5555666677778888",11L);
        BankCard myCardThree=new BankCard("2222333344445555",11L);
        bankCardRepository.saveAndFlush(myCardOne);
        bankCardRepository.saveAndFlush(myCardTwo);
        bankCardRepository.saveAndFlush(myCardThree);

        // @formatter:off
        mvc.perform(get("/api/v2/users/11/bankcard").contentType("application/hal+json"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/hal+json"))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$[0].cardNum", is(myCardOne.getCardNum())))
                .andExpect(jsonPath("$[1].cardNum", is(myCardTwo.getCardNum())));
        // @formatter:on
    }

    @Test
    public void givenBankCard_whenRemoveBankCardOfUserId_thenStatus200() throws Exception {

        BankCard myCard=new BankCard("1111222233334444",11L);
        bankCardRepository.saveAndFlush(myCard);
        mvc.perform(delete("/api/v2/users/11/bankcard")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(myCard)))
                .andDo(print())
                .andExpect(status().isOk());

        //successfull as well
        //List<BankCard> found = bankCardRepository.findAll();
        //assertThat(found).isEmpty();
    }


    @Test
    public void givenAccount_whenGetPassword_thenStatus200() throws Exception {

        Account johnAccount=new Account("john","passwordjohn");
        accountRepository.saveAndFlush(johnAccount);
        mvc.perform(get("/api/v2/users/11/password").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    public void GivenValidPassword_whenPutPassword_thenUpdatePassword() throws Exception {
        Account johnAccount=new Account("john","passwordjohn",11L);
        accountRepository.saveAndFlush(johnAccount);
        mvc.perform(put("/api/v2/users/"+johnAccount.getUserId()+"/password")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(johnAccount)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.password", is(johnAccount.getPassword())));
    }

}