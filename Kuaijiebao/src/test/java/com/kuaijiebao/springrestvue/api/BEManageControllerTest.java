package com.kuaijiebao.springrestvue.api;

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
public class BEManageControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FPRepository fpRepository;

    @Autowired
    private DebtRepository debtRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @After
    public void resetDb() {
        userRepository.deleteAll();
        fpRepository.deleteAll();
        debtRepository.deleteAll();
        questionRepository.deleteAll();
    }

    @Test
    public void givenUsers_whenGetAllUsers_thenStatus200() throws Exception {

        User bob=new User("bobby","student");
        User john=new User("johnny","business person");
        userRepository.saveAndFlush(bob);
        userRepository.saveAndFlush(john);
        mvc.perform(get("/api/BEManage/manageUser/getAllUsers").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void givenUserId_whenDeleteUser_thenStatus200() throws Exception {

        User bob=new User("bobby","student");
        userRepository.saveAndFlush(bob);
        mvc.perform(delete("/api/BEManage/manageUser/deleteUser/"+bob.getId()).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void givenQuestions_whenGetAllQuestions_thenStatus200() throws Exception {

        Question qOne=new Question("HowToLend?","Can anybody tell me how to lend money?","ImAnswer.");
        Question qTwo=new Question("HowToSignUp?","Can anybody tell me how to sign up?","ImAnswer.");
        questionRepository.saveAndFlush(qOne);
        questionRepository.saveAndFlush(qTwo);
        mvc.perform(get("/api/BEManage/manageQuestion/getAllQuestions").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void whenValidInput_thenAddQuestion() throws Exception {
        Question qOne=new Question("HowToLend?","Can anybody tell me how to lend money?","ImAnswer.");
        mvc.perform(post("/api/BEManage/manageQuestion/addQuestion").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(qOne)));

        List<Question> found = questionRepository.findAll();
        assertThat(found).extracting(Question::getTitle).containsOnly(qOne.getTitle());
    }

    @Test
    public void givenQuestionId_whenDeleteQuestion_thenStatus200() throws Exception {

        Question qOne=new Question("HowToLend?","Can anybody tell me how to lend money?","ImAnswer.");
        Question qTwo=new Question("HowToSignUp?","Can anybody tell me how to sign up?","ImAnswer.");
        questionRepository.saveAndFlush(qOne);
        questionRepository.saveAndFlush(qTwo);
        mvc.perform(delete("/api/BEManage/manageQuestion/deleteQuestion/"+qOne.getId()).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void givenFPs_whenGetAllFPs_thenStatus200() throws Exception {

        FP fpOne=new FP(100l,"JapaneseSomeCosmetics",300f,"Shiseido");
        FP fpTwo=new FP(20l,"JapaneseSomeCars",30000f,"MAZDA");
        fpRepository.saveAndFlush(fpOne);
        fpRepository.saveAndFlush(fpTwo);
        mvc.perform(get("/api/BEManage/manageFP/getAllFPs").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void whenValidInput_thenAddFP() throws Exception {

        FP fpOne=new FP(100l,"JapaneseSomeCosmetics",300f,"Shiseido");
        mvc.perform(post("/api/BEManage/manageFP/addFP").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(fpOne)));
        List<FP> found = fpRepository.findAll();
        assertThat(found).extracting(FP::getName).containsOnly(fpOne.getName());
    }


    @Test
    public void givenFP_whenDeleteFP_thenStatus200() throws Exception {

        FP fpOne=new FP(100l,"JapaneseSomeCosmetics",300f,"Shiseido");
        fpRepository.saveAndFlush(fpOne);
        mvc.perform(delete("/api/BEManage/manageFP/deleteFP/"+fpOne.getId()).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void givenDebts_whenGetAllDebts_thenStatus200() throws Exception {

        Date dateOne=new Date();
        Date dateTwo=new Date();
        Debt debtOne=new Debt(1l,11l,100l,dateOne,"ImContent",true,dateOne,true,dateOne,true,1f,1);
        Debt debtTwo=new Debt(1l,22l,150l,dateTwo,"ImContent",true,dateTwo,true,dateTwo,true,1f,1);
        debtRepository.saveAndFlush(debtOne);
        debtRepository.saveAndFlush(debtTwo);
        mvc.perform(get("/api/BEManage/manageFP/getAllFPs").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void givenDebt_whenDeleteDebt_thenStatus200() throws Exception {

        Date dateOne=new Date();
        Date dateTwo=new Date();
        Debt debtOne=new Debt(1l,11l,100l,dateOne,"ImContent",true,dateOne,true,dateOne,true,1f,1);
        Debt debtTwo=new Debt(1l,22l,150l,dateTwo,"ImContent",true,dateTwo,true,dateTwo,true,1f,1);
        debtRepository.saveAndFlush(debtOne);
        debtRepository.saveAndFlush(debtTwo);
        mvc.perform(delete("/api/BEManage/manageDebt/deleteDebt/"+debtOne.getId()).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }



}