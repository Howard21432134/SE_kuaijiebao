package com.kuaijiebao.springrestvue.api;

import com.kuaijiebao.springrestvue.domain.*;
import com.kuaijiebao.springrestvue.payload.ApiResponse;
import com.kuaijiebao.springrestvue.repository.AccountRepository;
import com.kuaijiebao.springrestvue.repository.UserPendingValidationRepository;
import com.kuaijiebao.springrestvue.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.config.EnableHypermediaSupport;
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
@RequestMapping("api/users")
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
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




    @GetMapping(path = "/{userId}", produces = {"application/hal+json"})
    public User getUserByUserId(@PathVariable Long userId) {
        return userService.findByUserId(userId);
    }

    @PutMapping(path = "/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody User user) {
        user.setUserId(userId);
        return userService.save(user);
    }

    @PutMapping(path ="/{userId}/email")
    public User updateEmail(@PathVariable Long userId,
                                @RequestBody User user) {
        User newUser=userService.findByUserId(userId);
        newUser.setUserId(userId);
        newUser.setEmail(user.getEmail());

        mailAuthService.SendValidationMail("satoaikawa@sjtu.edu.cn","Validation Mail", "Code: 123456789");

        return userService.save(newUser);
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
            User user=userRepository.findByUserId(account.getUserId());

            UserPendingValidation userInfo=userPendingValidationRepository.findByUsername(code.getUsername());
            if("EMAIL_ADDRESS".equals(userInfo.getElem())){
                user.setUserId(user.getUserId());
                user.setEmail(userInfo.getItem());
            }else if("BANK_CARD".equals(userInfo.getElem())){
                //stub bankcard
            }else if("PHONE_NUMBER".equals(userInfo.getElem())){
                user.setUserId(user.getUserId());
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


    @PutMapping(path ="/{userId}/phone")
    public User updatePhone(@PathVariable Long userId,
                                @RequestBody User user) {
        User newUser=userService.findByUserId(userId);
        newUser.setUserId(userId);
        newUser.setPhone(user.getPhone());
        return userService.save(newUser);
    }


    @GetMapping(path = "/{userId}/password")
    public Account getPasswordByUserId(@PathVariable Long userId) {
        return accountService.findByUserId(userId);
    }


    //
    //can ONLY change the password field
    @PutMapping(path = "/{userId}/password")
    public Account updatePassword(@PathVariable Long userId, @RequestBody Account account) {
        Account newAccount=accountService.findByUserId(userId);
        newAccount.setPassword(account.getPassword());
        return accountService.update(newAccount);
    }


    @GetMapping(path = "/{userId}/bankcard", produces = {"application/hal+json"})
    public List<BankCard> getBankCardsByUserId(@PathVariable Long userId) {
        return bankCardService.findByUserId(userId);
    }

    @PostMapping(path ="/{userId}/bankcard")
    @ResponseStatus(HttpStatus.CREATED)
    public BankCard createBankCard(@PathVariable Long userId,
                                @RequestBody BankCard bankCard) {
        BankCard newCard=bankCard;
        newCard.setUserId(userId);
        return bankCardService.addCard(newCard);
    }

    @DeleteMapping(path ="/{userId}/bankcard")
    void deleteBankCard(@PathVariable Long userId,
                                     @RequestBody BankCard bankCard) {
       bankCardService.deleteByCardNum(bankCard.getCardNum());
    }


    @GetMapping(path ="{userId}/email")
    @ResponseStatus(HttpStatus.CREATED)
    public User deleteEmail(@PathVariable Long userId) {
        User newUser=userService.findByUserId(userId);
        newUser.setUserId(userId);
        newUser.setEmail("");
        return userService.save(newUser);
    }

    @GetMapping(path ="/{userId}/phone")
    @ResponseStatus(HttpStatus.CREATED)
    public User deletePhone(@PathVariable Long userId) {
        User newUser=userService.findByUserId(userId);
        newUser.setUserId(userId);
        newUser.setPhone("");
        return userService.save(newUser);
    }


}
