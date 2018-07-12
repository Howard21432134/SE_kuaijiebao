package com.kuaijiebao.springrestvue.repository;

import com.kuaijiebao.springrestvue.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.kuaijiebao.springrestvue.domain.Account;

import javax.transaction.Transactional;

//@RepositoryRestResource(collectionResourceRel = "books", path = "books")
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    public Account findOneById(Long id);
    public Account findOneByUsernameAndPassword(String username, String Password);
    public Account save(Account account);
    @Modifying
    @Transactional
    public void deleteById(Long id);
}