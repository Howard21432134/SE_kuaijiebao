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
    public void whenValidInput_thenCreateUser() throws IOException, Exception {
        User bob = new User("bob","bob",
                "kl48394593","pilot",0,
                "hongkong", "Hello","11122223333",
                "bob@qq.com");
        mvc.perform(post("/api/user/createUser").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(bob)));

        List<User> found = repository.findAll();
        assertThat(found).extracting(User::getName).containsOnly("bob");
    }

    @Test
    public void givenUsers_whenGetUsers_thenStatus200() throws Exception {
        createTestUser("john","john",
                "MG234143","student",0,
                "SJTU", "Hello","11122223333",
                "john@qq.com");
        createTestUser("bob","bob",
                "jh23453245","engineer",0,
                "shanghai", "Hello","11122223333",
                "bob@qq.com");

        createTestUser("alex","alex",
                "kl48394593","pilot",0,
                "hongkong", "Hello","11122223333",
                "alex@qq.com");

        // @formatter:off
        mvc.perform(get("/api/user").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$[0].name", is("john")))
                .andExpect(jsonPath("$[1].name", is("bob")));
        // @formatter:on
    }


    @Test
    public void GivenValidUser_whenPutUser_thenUpdateUser() throws IOException, Exception {
        createTestUser("john","john",
                "MG234143","student",0,
                "SJTU", "Hello","11122223333",
                "john@qq.com");
        List<User> found = repository.findAll();
        assertThat(found).extracting(User::getName).containsOnly("john");
    }



    @Test
    public void whenValidInput_thenCreateBankCard() throws IOException, Exception {
        BankCard myCard=new BankCard("1111222233334444",11L);
        mvc.perform(post("/api/user/addBankCard/11").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(myCard)));

        List<BankCard> found = bankCardRepository.findAll();
        assertThat(found).extracting(BankCard::getCardNum).containsOnly("1111222233334444");
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
        mvc.perform(get("/api/user/getBankCard/11").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$[0].cardNum", is("1111222233334444")))
                .andExpect(jsonPath("$[1].cardNum", is("5555666677778888")));
        // @formatter:on
    }

    @Test
    public void givenBankCard_whenRemoveBankCardOfUserId_thenStatus200() throws Exception {

        BankCard myCard=new BankCard("1111222233334444",11L);
        bankCardRepository.saveAndFlush(myCard);
        mvc.perform(delete("/api/user/removeBankCard/11").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(myCard)))
                .andDo(print())
                .andExpect(status().isOk());

        //successfull as well
        //List<BankCard> found = bankCardRepository.findAll();
        //assertThat(found).isEmpty();
    }


    @Test
    public void givenAccount_whenGetPassword_thenStatus200() throws Exception {

        Account johnAccount=new Account(11L,"john","passwordjohn");
        accountRepository.saveAndFlush(johnAccount);
        mvc.perform(get("/api/user/getPassword/11").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    public void GivenValidPassword_whenPutPassword_thenUpdatePassword() throws IOException, Exception {
        Account johnAccount=new Account(11L,"john","passwordjohn");
        accountRepository.saveAndFlush(johnAccount);
        List<Account> found = accountRepository.findAll();
        assertThat(found).extracting(Account::getPassword).containsOnly("passwordjohn");
    }



    private void createTestUser(String nickname, String name, String identity,
                                    String job, Integer income, String address,
                                    String introduction, String phone,
                                    String email) {
        User user = new User(nickname, name, identity,
                job, income, address, introduction,
                phone, email);
        repository.saveAndFlush(user);
    }




}