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
import java.util.List;
import java.util.Date;

import com.kuaijiebao.springrestvue.domain.FPDR;
import com.kuaijiebao.springrestvue.domain.BankCard;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.kuaijiebao.springrestvue.domain.Debt;
import com.kuaijiebao.springrestvue.repository.UserRepository;
import com.kuaijiebao.springrestvue.repository.DebtRepository;
import com.kuaijiebao.springrestvue.repository.FPDRRepository;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = SpringRestReactApplication.class)
@AutoConfigureMockMvc
// @TestPropertySource(locations = "classpath:application-integrationtest.properties")
@AutoConfigureTestDatabase
public class StatisticsControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private DebtRepository debtRepository;

    @Autowired
    private FPDRRepository fpdrRepository;


    @After
    public void resetDb() {
        debtRepository.deleteAll();
        fpdrRepository.deleteAll();
    }

    @Test
    public void givenDebts_whenGetUserDebtStat_thenStatus200() throws Exception {

        Date dateOne=new Date();
        Date dateTwo=new Date();

        Debt debtOne=new Debt(1l,11l,100l,dateOne,dateOne,"ImContent",true,dateOne,true,dateOne,true,1f,1);
        Debt debtTwo=new Debt(1l,22l,150l,dateTwo,dateTwo,"ImContent",true,dateTwo,true,dateTwo,true,1f,1);
        debtRepository.saveAndFlush(debtOne);
        debtRepository.saveAndFlush(debtTwo);

        mvc.perform(get("/api/v2/statistics/debt-stat/1?since=2000-01-01&until=2100-01-01").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$[0].userId", is(1)))
                .andExpect(jsonPath("$[1].userId", is(1)));
    }

    @Test
    public void givenFPDRs_whenGetUserFPDRStat_thenStatus200() throws Exception {

        Date dateOne=new Date();
        Date dateTwo=new Date();

        FPDR FPDROne=new FPDR(1l,1l,1l,dateOne,1);
        FPDR FPDRTwo=new FPDR(1l,1l,1l,dateTwo,1);

        fpdrRepository.saveAndFlush(FPDROne);
        fpdrRepository.saveAndFlush(FPDRTwo);

        mvc.perform(get("/api/v2/statistics/fpdr-stat/1?since=2000-01-01&until=2100-01-01").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$[0].userId", is(1)))
                .andExpect(jsonPath("$[1].userId", is(1)));
    }
}