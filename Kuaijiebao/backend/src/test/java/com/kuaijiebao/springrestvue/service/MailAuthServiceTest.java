package com.kuaijiebao.springrestvue.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MailAuthServiceTest {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String username;

    @Test
    public void WhenValidInput_WhenSendValidationMail_ThenSuccess() {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(username);
        simpleMailMessage.setTo("example@sjtu.edu.cn");//接收邮件的邮箱
        simpleMailMessage.setSubject("Kuaijiebao Validation Email");
        simpleMailMessage.setText("ImValidationCode");

        mailSender.send(simpleMailMessage);

    }
}