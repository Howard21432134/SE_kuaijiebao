package com.kuaijiebao.springrestvue.repository;

import com.kuaijiebao.springrestvue.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


import com.kuaijiebao.springrestvue.domain.Account;

import javax.transaction.Transactional;

//@RepositoryRestResource(collectionResourceRel = "books", path = "books")
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    public Account findByAccountId(Long id);
    public Account findByUserId(Long id);
    Optional<Account> findByUsername(String username);
    Boolean existsByUsername(String username);
    public Account findByUsernameAndPassword(String username, String Password);
    public Account save(Account account);
    @Modifying
    @Transactional
    public void deleteByAccountId(Long id);


}