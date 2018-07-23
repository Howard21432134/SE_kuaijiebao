package com.kuaijiebao.springrestvue.api;

import static org.junit.Assert.*;


import static org.junit.Assert.*;

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
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.Contains;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.kuaijiebao.springrestvue.domain.User;
import com.kuaijiebao.springrestvue.domain.Question;
import com.kuaijiebao.springrestvue.domain.Debt;
import com.kuaijiebao.springrestvue.domain.FP;
import com.kuaijiebao.springrestvue.repository.UserRepository;
import com.kuaijiebao.springrestvue.repository.QuestionRepository;
import com.kuaijiebao.springrestvue.repository.FPRepository;
import com.kuaijiebao.springrestvue.repository.DebtRepository;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = SpringRestReactApplication.class)
@AutoConfigureMockMvc
// @TestPropertySource(locations = "classpath:application-integrationtest.properties")
@AutoConfigureTestDatabase
public class QuestionControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private QuestionRepository questionRepository;

    @After
    public void resetDb() {
        questionRepository.deleteAll();
    }

    @Test
    public void givenQuestions_whenGetAllQuestions_thenStatus200() throws Exception {

        Question qOne=new Question("HowToLend?","Can anybody tell me how to lend money?","ImAnswer.");
        Question qTwo=new Question("HowToSignUp?","Can anybody tell me how to sign up?","ImAnswer.");
        questionRepository.saveAndFlush(qOne);
        questionRepository.saveAndFlush(qTwo);
        mvc.perform(get("/api/v2/counsel/questions").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void givenKeyword_whenGetSearchKeywordQuestions_thenStatus200AndContainsKeyword() throws Exception {

        String keyword="lend";
        Question qOne=new Question("HowToLend?","Can anybody tell me how to lend money?","ImAnswer.");
        Question qTwo=new Question("HowToSignUp?","Can anybody tell me how to sign up?","ImAnswer.");
        questionRepository.saveAndFlush(qOne);
        questionRepository.saveAndFlush(qTwo);
        mvc.perform(get("/api/v2/questions/search?keyword="+keyword).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString(keyword)));
    }

}