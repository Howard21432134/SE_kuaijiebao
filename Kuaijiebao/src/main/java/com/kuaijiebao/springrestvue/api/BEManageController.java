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

//*********************************************************************************
//dependency for HATEOAS
//

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value="/api")
@EnableHypermediaSupport(type = HypermediaType.HAL)
public class BEManageController {



    @Autowired
    UserService userService;

    @Autowired
    QuestionService questionService;

    @Autowired
    FPRepository fpRepository;

    @Autowired
    DebtRepository debtRepository;

    @Autowired
    AccountService accountService;

    @GetMapping(path = "/user-management/users")
    public List<User> getAllUsers() {
        List<User> user = userService.findAll();
        return user;
    }

    @DeleteMapping(path ="/user-management/users/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteByUserId(userId);
    }

    @GetMapping(path = "/question-management/questions")
    public List<Question> getAllQuestions() {
        List<Question> question = questionService.findAll();
        return question;
    }

    @PostMapping(path ="/question-management/questions")
    @ResponseStatus(HttpStatus.CREATED)
    public Question createQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }


    @DeleteMapping(path ="/question-management/questions/{id}")
    public void deleteQuestion(@PathVariable Long id) {
        questionService.delete(id);
    }


    @GetMapping(path = "/fp-management/fps")
    public List<FP> getAllFPs() {
        return fpRepository.findAll();
    }


    @PostMapping(path ="/fp-management/fps")
    @ResponseStatus(HttpStatus.CREATED)
    public FP createFP(@RequestBody FP fp) {
        return fpRepository.save(fp);
    }


    @DeleteMapping(path="/fp-management/fps/{id}")
    public void deleteFP(@PathVariable Long id) {
        fpRepository.deleteById(id);
    }


    @GetMapping(path = "/debt-management/debts")
    public List<Debt> getAllDebts() {
        return debtRepository.findAll();
    }

    @DeleteMapping(path="/debt-management/debts/{id}")
    public void deleteDebt(@PathVariable Long id) {
        debtRepository.deleteById(id);
    }


    //*********************************************************************************
    //HATEOAS
    //
    @RequestMapping(path = "v1/user-management/users", method = RequestMethod.GET, produces = {"application/hal+json"})
    public Resources<User> getAllUsersHateoas() {
        final List<User> allUsers = userService.findAll();

        for (final User user : allUsers) {
            Long userId = user.getUserId();
            Link selfLink = linkTo(methodOn(UserController.class).getUserByUserId(userId)).withSelfRel();
            user.add(selfLink);
            final Link accountLink = linkTo(methodOn(AccountController.class).getByUserId(userId)).withRel("account");
            user.add(accountLink);
            final Link bankCardLink =linkTo(methodOn(UserController.class).getBankCardsByUserId(userId)).withRel("bankcard");
            user.add(bankCardLink);
        }

        Link link =linkTo(BEManageController.class).slash("v1/user-management/users").withSelfRel();
        Resources<User> result = new Resources<User>(allUsers,link);
        return result;
    }

}
