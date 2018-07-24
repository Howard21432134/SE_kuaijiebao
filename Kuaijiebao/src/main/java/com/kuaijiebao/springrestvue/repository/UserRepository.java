package com.kuaijiebao.springrestvue.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.kuaijiebao.springrestvue.domain.User;

import javax.transaction.Transactional;
import java.util.Optional;

//@RepositoryRestResource(collectionResourceRel = "books", path = "books")
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUserId(Long id);
    public boolean existsByEmail(String  email);
    public boolean existsByPhone(String email);
    public User save(User user);
    @Modifying
    @Transactional
    public void deleteByUserId(Long id);


}