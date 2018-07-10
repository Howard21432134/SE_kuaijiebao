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


    @GetMapping
    public List<User> getAll() {
        List<User> user = userService.findAll();
        return user;
    }

    @GetMapping(path = "/getUser/{id}")
    public User getUserByUserId(@PathVariable Long id) {
        return userService.findOneByUserId(id);
    }

    @PutMapping(path = "/updateUser/{userId}")
    public User putCartItem(@PathVariable Long userId, @RequestBody User user) {
        user.setId(userId);
        return userService.update(user);
    }


    @GetMapping(path = "/getPassword/{id}")
    public Account getUserPasswordByUserId(@PathVariable Long id) {
        return accountService.findOneAccountById(id);
    }


    //
    //can ONLY change the password field
    @PutMapping(path = "/updatePassword/{userId}")
    public Account putCartItem(@PathVariable Long userId, @RequestBody Account account) {
        Account newAccount=accountService.findOneAccountById(userId);
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
        return bankCardService.addCard(bankCard);
    }

    @DeleteMapping(path ="/removeBankCard/{id}")
    void deleteUserCard(@PathVariable Long id,
                                     @RequestBody BankCard bankCard) {
       bankCardService.deleteByCardNum(bankCard.getCardNum());
    }


    /*
    @GetMapping("/validateUser")
    public User validateUser(@RequestParam String email, @RequestParam String password) {
        User user=userService.findOneByEmail(email);
        System.out.println(user.getPassword());
        if(user.getPassword().equals(password))
            return user;
        return null;
    }
*/

/*
    // curl http://localhost:8080/api/user/validateUser -i -XPOST -H "Content-Type: application/json" -d "{\"email\":\"user@qq.com\",\"password\":\"user\"}"
    @PostMapping("/validateUser")
    @ResponseStatus(HttpStatus.CREATED)
    User validateUser(@RequestBody User RequestUser) {
        User user=userService.findOneByEmail(RequestUser.getEmail());
        System.out.println(user.getPassword());
        if(user.getPassword().equals(RequestUser.getPassword()))
            return user;
        return null;
    }



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