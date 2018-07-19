package com.kuaijiebao.springrestvue.api;


import com.kuaijiebao.springrestvue.domain.BankCard;
import com.kuaijiebao.springrestvue.domain.User;
import com.kuaijiebao.springrestvue.domain.Account;
import com.kuaijiebao.springrestvue.domain.Question;
import com.kuaijiebao.springrestvue.service.AccountService;
import com.kuaijiebao.springrestvue.service.BankCardService;
import com.kuaijiebao.springrestvue.service.UserService;
import com.kuaijiebao.springrestvue.service.QuestionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/BEManage")
public class BEManageController {

    @Autowired
    UserService userService;

    @Autowired
    AccountService accountService;

    @Autowired
    BankCardService bankCardService;

    @Autowired
    QuestionService questionService;

    @GetMapping(path = "/users/getAllUsers")
    public List<User> getAllUser() {
        List<User> user = userService.findAll();
        return user;
    }

    @DeleteMapping(path ="/deleteUser/{id}")
    public void deleteUser(@PathVariable Long id) {
        accountService.delete(id);
        userService.deleteById(id);
    }

    @GetMapping(path = "/counsel/getAllQuestions")
    public List<Question> getAllQuestion() {
        List<Question> question = questionService.findAll();
        return question;
    }

    @PostMapping(path ="/counsel/addQuestion")
    @ResponseStatus(HttpStatus.CREATED)
    public Question postNewQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }


    @DeleteMapping(path ="/counsel/deleteQuestion/{id}")
    public void deleteQuestion(@PathVariable Long id) {
        questionService.delete(id);
    }






}
