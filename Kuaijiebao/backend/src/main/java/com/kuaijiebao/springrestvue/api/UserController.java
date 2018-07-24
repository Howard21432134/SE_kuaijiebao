package com.kuaijiebao.springrestvue.api;

import com.kuaijiebao.springrestvue.domain.*;
import com.kuaijiebao.springrestvue.payload.ApiResponse;
import com.kuaijiebao.springrestvue.repository.AccountRepository;
import com.kuaijiebao.springrestvue.repository.BankCardRepository;
import com.kuaijiebao.springrestvue.repository.UserPendingValidationRepository;
import com.kuaijiebao.springrestvue.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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
@RequestMapping("/api")
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

    @Autowired
    BankCardRepository bankCardRepository;


    @Autowired
    PasswordEncoder passwordEncoder;




    @GetMapping(path = "/v2/users/{userId}", produces = {"application/hal+json"})
    public User getUserByUserId(@PathVariable Long userId) {
        return userService.findByUserId(userId);
    }

    @PutMapping(path = "/v2/users/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody User user) {
        user.setUserId(userId);
        return userService.save(user);
    }

    @PutMapping(path ="/v2/users/{userId}/email")
    public User updateEmail(@PathVariable Long userId,
                                @RequestBody User user) {
        User newUser=userService.findByUserId(userId);
        newUser.setUserId(userId);
        newUser.setEmail(user.getEmail());

        //mailAuthService.SendValidationMail("satoaikawa@sjtu.edu.cn","Validation Mail", "Code: 123456789");

        return userService.save(newUser);
    }

    //
    //VPN should be set to global mode
    //
    @PostMapping(path ="/v2/users/registerInfo")
    public ResponseEntity<?> registerUserInfoModification(@RequestBody ValidationRequest request) {

        String generatedCode=getRandomCode();

        boolean accountExists=accountRepository.existsByUsername(request.getUsername());
        if(accountExists==false){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(false, "User with username "+request.getUsername()+" does not exist."));
        }

        //
        //password can be duplicated
        if("EMAIL_ADDRESS".equals(request.getElem())){
            if(userRepository.existsByEmail(request.getItem()))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, "The email address already used."));
        }else if("BANK_CARD".equals(request.getElem())){
            if(bankCardRepository.existsByCardNum(request.getItem()))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, "The bankcard already used."));
        }else if("PHONE_NUMBER".equals(request.getElem())){
            if(userRepository.existsByPhone(request.getItem()))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, "The phone number already used."));
        }else if("PASS_WORD".equals(request.getElem())) {
            //
            //stub error
            //
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, "ERROR: User Info registration FAILED."));
        }

        UserPendingValidation user=userPendingValidationRepository.findByUsername(request.getUsername());
        if(user==null){//POST
            UserPendingValidation newUser=new UserPendingValidation(request.getUsername(),
                    generatedCode, request.getElem(),request.getItem());
            userPendingValidationRepository.save(newUser);
        }else{//PUT if user with username already register validation info for some elem.
            UserPendingValidation newUser=new UserPendingValidation(request.getUsername(),
                    generatedCode, request.getElem(),request.getItem());
            newUser.setId(user.getId());
            userPendingValidationRepository.save(newUser);
        }
        //
        //IMPORTANT

        //if user change email user should validate with that updating email address
        //else user change phone or bankcard user can choose validating email
        if("EMAIL_ADDRESS".equals(request.getElem()))
            mailAuthService.SendValidationMail(request.getItem(),"Kuaijiebao Validation Mail", "Validation Code: "+generatedCode);
        else
            mailAuthService.SendValidationMail(request.getEmail(),"Kuaijiebao Validation Mail", "Validation Code: "+generatedCode);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "User Info registered successfully."));
    }
    @PostMapping(path ="/v2/users/validateInfo")
    public ResponseEntity<?> validateUserInfoModification(@RequestBody ValidationCode code) {
        boolean existUser=userPendingValidationRepository.existsByUsernameAndCode(code.getUsername(),code.getCode());

        if(existUser){

            Account account=accountRepository.findByUsername(code.getUsername()).orElse(null);
            User user=userRepository.findByUserId(account.getUserId());

            UserPendingValidation userInfo=userPendingValidationRepository.findByUsername(code.getUsername());
            if("EMAIL_ADDRESS".equals(userInfo.getElem())){//update email
                user.setUserId(user.getUserId());
                user.setEmail(userInfo.getItem());
                userRepository.save(user);
            }else if("BANK_CARD".equals(userInfo.getElem())){//add bankcard
                BankCard card=new BankCard(userInfo.getItem(),user.getUserId());
                bankCardRepository.save(card);
            }else if("PHONE_NUMBER".equals(userInfo.getElem())){//update phone
                user.setUserId(user.getUserId());
                user.setPhone(userInfo.getItem());
                userRepository.save(user);
            }else if("PASS_WORD".equals(userInfo.getElem())){//update password
                account.setAccountId(account.getAccountId());
                account.setPassword(passwordEncoder.encode(userInfo.getItem()));
                accountRepository.save(account);
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, "User Info modification FAILED."));
            }
            userPendingValidationRepository.deleteByUsername(code.getUsername());
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "User Info modification SUCCESS."));

        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, "User Info modification FAILED."));
        }

    }


    @PutMapping(path ="/v2/users/{userId}/phone")
    public User updatePhone(@PathVariable Long userId,
                                @RequestBody User user) {
        User newUser=userService.findByUserId(userId);
        newUser.setUserId(userId);
        newUser.setPhone(user.getPhone());
        return userService.save(newUser);
    }


    @GetMapping(path = "/v2/users/{userId}/password")
    public Account getPasswordByUserId(@PathVariable Long userId) {
        return accountService.findByUserId(userId);
    }


    //
    //can ONLY change the password field
    @PutMapping(path = "/v2/users/{userId}/password")
    public Account updatePassword(@PathVariable Long userId, @RequestBody Account account) {
        Account newAccount=accountService.findByUserId(userId);
        newAccount.setPassword(account.getPassword());
        return accountService.update(newAccount);
    }


    @GetMapping(path = "/v2/users/{userId}/bankcard", produces = {"application/hal+json"})
    public List<BankCard> getBankCardsByUserId(@PathVariable Long userId) {
        return bankCardService.findByUserId(userId);
    }

    @PostMapping(path ="/v2/users/{userId}/bankcard")
    @ResponseStatus(HttpStatus.CREATED)
    public BankCard createBankCard(@PathVariable Long userId,
                                @RequestBody BankCard bankCard) {
        BankCard newCard=bankCard;
        newCard.setUserId(userId);
        return bankCardService.addCard(newCard);
    }

    @DeleteMapping(path ="/v2/users/{userId}/bankcard")
    void deleteBankCard(@PathVariable Long userId,
                                     @RequestBody BankCard bankCard) {
       bankCardService.deleteByCardNum(bankCard.getCardNum());
    }


    @GetMapping(path ="/v2/users{userId}/email")
    @ResponseStatus(HttpStatus.CREATED)
    public User deleteEmail(@PathVariable Long userId) {
        User newUser=userService.findByUserId(userId);
        newUser.setUserId(userId);
        newUser.setEmail("");
        return userService.save(newUser);
    }

    @GetMapping(path ="/v2/users/{userId}/phone")
    @ResponseStatus(HttpStatus.CREATED)
    public User deletePhone(@PathVariable Long userId) {
        User newUser=userService.findByUserId(userId);
        newUser.setUserId(userId);
        newUser.setPhone("");
        return userService.save(newUser);
    }


    //
    //SIMPLE random code generator
    public String getRandomCode(){
        Random random = new Random();

        //between 100000 and 999999
        int x = random.nextInt(900000) + 100000;
        return Integer.toString(x);
    }


}
