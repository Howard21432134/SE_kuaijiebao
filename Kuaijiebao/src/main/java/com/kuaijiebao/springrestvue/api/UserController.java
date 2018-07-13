package com.kuaijiebao.springrestvue.api;

import com.kuaijiebao.springrestvue.domain.BankCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

import com.kuaijiebao.springrestvue.domain.User;
import com.kuaijiebao.springrestvue.domain.Account;
import com.kuaijiebao.springrestvue.domain.BankCard;
import com.kuaijiebao.springrestvue.service.UserService;
import com.kuaijiebao.springrestvue.service.AccountService;
import com.kuaijiebao.springrestvue.service.BankCardService;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AccountService accountService;

    @Autowired
    BankCardService bankCardService;


    @GetMapping(path = "/getUser/{userId}")
    public User getUserByUserId(@PathVariable Long userId) {
        return userService.findOneByUserId(userId);
    }

    @PutMapping(path = "/updateUser/{userId}")
    public User putUser(@PathVariable Long userId, @RequestBody User user) {
        user.setId(userId);
        return userService.update(user);
    }

    @PutMapping(path ="/addEmail/{userId}")
    public User putUserNewEmail(@PathVariable Long userId,
                                @RequestBody User user) {
        User newUser=userService.findOneByUserId(userId);
        newUser.setId(userId);
        newUser.setEmail(user.getEmail());
        return userService.update(newUser);
    }

    @PutMapping(path ="/addPhone/{userId}")
    public User putUserNewPhone(@PathVariable Long userId,
                                @RequestBody User user) {
        User newUser=userService.findOneByUserId(userId);
        newUser.setId(userId);
        newUser.setPhone(user.getPhone());
        return userService.update(newUser);
    }


    @GetMapping(path = "/getPassword/{userId}")
    public Account getUserPasswordByUserId(@PathVariable Long userId) {
        return accountService.findOneById(userId);
    }


    //
    //can ONLY change the password field
    @PutMapping(path = "/updatePassword/{userId}")
    public Account putPassword(@PathVariable Long userId, @RequestBody Account account) {
        Account newAccount=accountService.findOneById(userId);
        newAccount.setPassword(account.getPassword());
        return accountService.update(newAccount);
    }


    @GetMapping(path = "/getBankCard/{id}")
    public List<BankCard> getUserBankCardsByUserId(@PathVariable Long id) {
        return bankCardService.findOnesByUserId(id);
    }

    @PostMapping(path ="/addBankCard/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public BankCard postUserNewCard(@PathVariable Long id,
                                @RequestBody BankCard bankCard) {
        BankCard newCard=bankCard;
        newCard.setId(id);
        return bankCardService.addCard(newCard);
    }

    @DeleteMapping(path ="/removeBankCard/{id}")
    void deleteUserCard(@PathVariable Long id,
                                     @RequestBody BankCard bankCard) {
       bankCardService.deleteByCardNum(bankCard.getCardNum());
    }


    @GetMapping(path ="/removeEmail/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public User deleteUserEmail(@PathVariable Long id) {
        User newUser=userService.findOneByUserId(id);
        newUser.setId(id);
        newUser.setEmail("");
        return userService.update(newUser);
    }

    @GetMapping(path ="/removePhone/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public User deleteUserPhone(@PathVariable Long id) {
        User newUser=userService.findOneByUserId(id);
        newUser.setId(id);
        newUser.setPhone("");
        return userService.update(newUser);
    }




/*


    @PostMapping("/addUser")
    @ResponseStatus(HttpStatus.CREATED)
    public User postNewUser(@RequestBody User user) {
        return userService.create(user);
    }

    @DeleteMapping("/deleteUser")
    void deleteUser(@RequestParam("id") Long id) {
        userService.delete(id);
    }


*/
}

//curl http://localhost:8080/api/user/addUser -i -XPOST -H "Content-Type: application/json" -d "{\"username\":\"snow boll boy\",\"firstname\":\"tores\",\"lastname\":\"louis\",\"username\":\"snow boll boy\",\"email\":\"vvsx@qq.com\",\"imgLink\":\"snow.jpg\"}"

//http://localhost:8080/api/user/validateUser?email=ja@qq.com&password=fafd
//curl http://localhost:8080/api/cart/addCartOfUserId -i -XPOST -H "Content-Type: application/json" -d "[{\"userId\":\"222\",\"bookId\":\"1\",\"count\":\"2\"}]"
//