package com.kuaijiebao.springrestvue.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kuaijiebao.springrestvue.domain.Account;
import com.kuaijiebao.springrestvue.repository.AccountRepository;
import com.kuaijiebao.springrestvue.exception.ResourceNotFoundException;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        // Let people login with either username or email
        Account user = accountRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Account not found with username or email : " + username)
        );

        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        Account user = accountRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Account", "id", id)
        );

        return UserPrincipal.create(user);
    }
}