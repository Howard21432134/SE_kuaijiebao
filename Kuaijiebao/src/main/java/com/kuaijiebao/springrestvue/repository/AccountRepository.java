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
    //
    //added for LOGIN sub module
    //
    //Optional<Account> findByEmail(String email);
    //Optional<Account> findByUsernameOrEmail(String username, String email);
    List<Account> findByUserIdIn(List<Long> userIds);
    Optional<Account> findByUsername(String username);
    Boolean existsByUsername(String username);
    //Boolean existsByEmail(String email);
    //
    //
    //

    public Account findByAccountId(Long id);
    public Account findByUserId(Long id);
    public Account findOneByUsernameAndPassword(String username, String Password);
    public Account save(Account account);
    @Modifying
    @Transactional
    public void deleteByAccountId(Long id);


}