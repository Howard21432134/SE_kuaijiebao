package com.kuaijiebao.springrestvue.api;

import com.kuaijiebao.springrestvue.domain.Account;
import com.kuaijiebao.springrestvue.domain.BankCard;
import com.kuaijiebao.springrestvue.domain.User;
import com.kuaijiebao.springrestvue.service.AccountService;
import com.kuaijiebao.springrestvue.service.BankCardService;
import com.kuaijiebao.springrestvue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {




}

//curl http://localhost:8080/api/user/addUser -i -XPOST -H "Content-Type: application/json" -d "{\"username\":\"snow boll boy\",\"firstname\":\"tores\",\"lastname\":\"louis\",\"username\":\"snow boll boy\",\"email\":\"vvsx@qq.com\",\"imgLink\":\"snow.jpg\"}"

//http://localhost:8080/api/user/validateUser?email=ja@qq.com&password=fafd
//curl http://localhost:8080/api/cart/addCartOfUserId -i -XPOST -H "Content-Type: application/json" -d "[{\"userId\":\"222\",\"bookId\":\"1\",\"count\":\"2\"}]"
//