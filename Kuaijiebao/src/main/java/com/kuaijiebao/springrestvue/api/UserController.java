package com.kuaijiebao.springrestvue.api;

import com.kuaijiebao.springrestvue.domain.*;
import com.kuaijiebao.springrestvue.payload.ApiResponse;
import com.kuaijiebao.springrestvue.repository.AccountRepository;
import com.kuaijiebao.springrestvue.repository.UserPendingValidationRepository;
import com.kuaijiebao.springrestvue.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.net.URI;
import java.util.List;

import com.kuaijiebao.springrestvue.domain.BankCard;
import com.kuaijiebao.springrestvue.service.UserService;
import com.kuaijiebao.springrestvue.service.AccountService;
import com.kuaijiebao.springrestvue.service.BankCardService;
import com.kuaijiebao.springrestvue.service.MailAuthService;
import com.kuaijiebao.springrestvue.payload.ValidationRequest;
import com.kuaijiebao.springrestvue.payload.ValidationCode;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

    @Autowired
    MailAuthService mailAuthService;

    @Autowired
    UserPendingValidationRepository userPendingValidationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;




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

        mailAuthService.SendValidationMail("satoaikawa@sjtu.edu.cn","Validation Mail", "Code: 123456789");

        return userService.update(newUser);
    }

    @PostMapping(path ="/registerInfo")
    public ResponseEntity<?> registerUserInfoModification(@RequestBody ValidationRequest request) {

        boolean accountExists=accountRepository.existsByUsername(request.getUsername());
        if(accountExists==false){
            URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/user/registerInfo")
                    .buildAndExpand().toUri();
            return ResponseEntity.created(location).body(new ApiResponse(false, "\"User with username "+request.getUsername()+"does not exist."));
        }
        UserPendingValidation user=userPendingValidationRepository.findByUsername(request.getUsername());
        if(user==null){
            UserPendingValidation newUser=new UserPendingValidation(request.getUsername(),
                    "ImCode", request.getElem(),request.getItem());
            userPendingValidationRepository.save(newUser);//POST
        }else{
            UserPendingValidation newUser=new UserPendingValidation(request.getUsername(),
                    "ImCode", request.getElem(),request.getItem());
            userPendingValidationRepository.save(newUser);//PUT
        }
        //
        //IMPORTANT
        //
        //mailAuthService.SendValidationMail("satoaikawa@sjtu.edu.cn","Validation Mail", "Code: 123456789");
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/user/registerInfo")
                .buildAndExpand().toUri();

        return ResponseEntity.created(location).body(new ApiResponse(false, "\"User Info registered successfully."));
    }
    @PostMapping(path ="/validateInfo")
    public ResponseEntity<?> validateUserInfoModification(@RequestBody ValidationCode code) {
        boolean existUser=userPendingValidationRepository.existsByUsernameAndCode(code.getUsername(),code.getCode());

        if(existUser){

            Account account=accountRepository.findByUsername(code.getUsername()).orElse(null);
            User user=userRepository.findOneById(account.getUserId());

            UserPendingValidation userInfo=userPendingValidationRepository.findByUsername(code.getUsername());
            if("EMAIL_ADDRESS".equals(userInfo.getElem())){
                user.setId(user.getId());
                user.setEmail(userInfo.getItem());
            }else if("BANK_CARD".equals(userInfo.getElem())){
                //stub bankcard
            }else if("PHONE_NUMBER".equals(userInfo.getElem())){
                user.setId(user.getId());
                user.setPhone(userInfo.getItem());
            }else{
                URI location = ServletUriComponentsBuilder
                        .fromCurrentContextPath().path("/user/validateInfo/{username}")
                        .buildAndExpand(userInfo.getUsername()).toUri();

                return ResponseEntity.created(location).body(new ApiResponse(false, "\"User Info modification failed."));
            }
            userRepository.save(user);
            userPendingValidationRepository.deleteByUsername(code.getUsername());

            URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/user/validateInfo/{username}")
                    .buildAndExpand(userInfo.getUsername()).toUri();

            return ResponseEntity.created(location).body(new ApiResponse(true, "User Info modified successfully."));
        }else{
            URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/user/validateInfo")
                    .buildAndExpand().toUri();

            return ResponseEntity.created(location).body(new ApiResponse(false, "\"User Info modification failed."));
        }

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