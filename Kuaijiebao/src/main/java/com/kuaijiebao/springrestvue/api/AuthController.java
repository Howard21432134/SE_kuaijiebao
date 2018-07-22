package com.kuaijiebao.springrestvue.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;
import java.util.Collections;

import com.kuaijiebao.springrestvue.domain.Account;
import com.kuaijiebao.springrestvue.domain.User;
import com.kuaijiebao.springrestvue.repository.AccountRepository;
import com.kuaijiebao.springrestvue.repository.UserRepository;
import com.kuaijiebao.springrestvue.payload.ApiResponse;
import com.kuaijiebao.springrestvue.payload.LoginRequest;
import com.kuaijiebao.springrestvue.payload.SignUpRequest;
import com.kuaijiebao.springrestvue.exception.AppException;



@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;

    //@Autowired
    //RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    //@Autowired
    //JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        //String jwt = tokenProvider.generateToken(authentication);
        //return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
        return ResponseEntity.ok(authentication);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(accountRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        //if(accountRepository.existsByEmail(signUpRequest.getEmail())) {
        //    return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
        //            HttpStatus.BAD_REQUEST);
        //}

        // Creating user's account
        Account newAccount = new Account(signUpRequest.getUsername(), signUpRequest.getPassword());
        User user=new User(signUpRequest.getNickname(),signUpRequest.getIdentity(),
                signUpRequest.getName(),signUpRequest.getJob(), signUpRequest.getIncome(), signUpRequest.getAddress(),
                signUpRequest.getIntroduction(), signUpRequest.getPhone(), signUpRequest.getEmail());
        User userResult=userRepository.save(user);
        newAccount.setUserId(userResult.getUserId());
        newAccount.setPassword(passwordEncoder.encode(newAccount.getPassword()));
        newAccount.setRoles("ROLE_USER");
        Account result = accountRepository.save(newAccount);
        //System.out.println(RoleName.ROLE_USER);

        //Role userRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
          //      .orElseThrow(() -> new AppException("Account Role not set..."));

        //Role userRole=new Role(RoleName.ROLE_USER);
        //AuthorityUtils.createAuthorityList(manager.getRoles())
        //user.setRoles(Collections.singleton(userRole));

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "Account registered successfully"));
    }
}
