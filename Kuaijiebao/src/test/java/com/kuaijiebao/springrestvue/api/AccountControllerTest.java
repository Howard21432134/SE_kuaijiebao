package com.kuaijiebao.springrestvue.api;

import static org.junit.Assert.*;

import com.kuaijiebao.springrestvue.SpringRestReactApplication;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.List;

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

import com.kuaijiebao.springrestvue.domain.Account;
import com.kuaijiebao.springrestvue.repository.AccountRepository;
import com.kuaijiebao.springrestvue.service.AccountService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = SpringRestReactApplication.class)
@AutoConfigureMockMvc
// @TestPropertySource(locations = "classpath:application-integrationtest.properties")
@AutoConfigureTestDatabase
public class AccountControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private AccountRepository repository;

    @After
    public void resetDb() {
        repository.deleteAll();
    }



    @Test
    public void whenValidInput_thenCreateAccount() throws IOException, Exception {
        Account bobAccount = new Account("bob","passwordbob");
        mvc.perform(post("/api/v1/accounts").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(bobAccount)));

        List<Account> found = repository.findAll();
        assertThat(found).extracting(Account::getUsername).containsOnly("bob");
    }


    @Test
    public void givenAccounts_whenGetAccounts_thenStatus200() throws Exception {
        createTestAccount("john","passwordjohn");
        createTestAccount("bob","passwordbob");
        createTestAccount("alex","passwordalex");

        // @formatter:off
        mvc.perform(get("/api/v1/accounts").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$[0].username", is("john")))
                .andExpect(jsonPath("$[1].username", is("bob")));
        // @formatter:on
    }

    private void createTestAccount( String username, String password) {
        Account account = new Account(username, password);
        repository.saveAndFlush(account);
    }

}