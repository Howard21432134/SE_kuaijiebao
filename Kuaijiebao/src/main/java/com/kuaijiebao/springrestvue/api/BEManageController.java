package com.kuaijiebao.springrestvue.api;

import com.kuaijiebao.springrestvue.domain.User;
import com.kuaijiebao.springrestvue.domain.FP;
import com.kuaijiebao.springrestvue.domain.Debt;
import com.kuaijiebao.springrestvue.domain.Account;
import com.kuaijiebao.springrestvue.repository.FPRepository;
import com.kuaijiebao.springrestvue.repository.DebtRepository;
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
    QuestionService questionService;

    @Autowired
    FPRepository fpRepository;

    @Autowired
    DebtRepository debtRepository;

    @GetMapping(path = "/manageUser/getAllUsers")
    public List<User> getAllUsers() {
        List<User> user = userService.findAll();
        return user;
    }

    @DeleteMapping(path ="/manageUser/deleteUser/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
    }

    @GetMapping(path = "/manageQuestion/getAllQuestions")
    public List<Question> getAllQuestions() {
        List<Question> question = questionService.findAll();
        return question;
    }

    @PostMapping(path ="/manageQuestion/addQuestion")
    @ResponseStatus(HttpStatus.CREATED)
    public Question postNewQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }


    @DeleteMapping(path ="/manageQuestion/deleteQuestion/{id}")
    public void deleteQuestion(@PathVariable Long id) {
        questionService.delete(id);
    }


    @GetMapping(path = "/manageFP/getAllFPs")
    public List<FP> getAllFPs() {
        return fpRepository.findAll();
    }


    @PostMapping(path ="/manageFP/addFP")
    @ResponseStatus(HttpStatus.CREATED)
    public FP postNewFP(@RequestBody FP fp) {
        return fpRepository.save(fp);
    }


    @DeleteMapping(path="/manageFP/deleteFP/{id}")
    public void deleteFP(@PathVariable Long id) {
        fpRepository.deleteById(id);
    }


    @GetMapping(path = "/manageDebt/getAllDebts")
    public List<Debt> getAllDebts() {
        return debtRepository.findAll();
    }

    @DeleteMapping(path="/manageDebt/deleteDebt/{id}")
    public void deleteDebt(@PathVariable Long id) {
        debtRepository.deleteById(id);
    }


}
