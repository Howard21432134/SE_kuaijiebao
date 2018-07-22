package com.kuaijiebao.springrestvue.repository;

import com.kuaijiebao.springrestvue.domain.UserPendingValidation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

//@RepositoryRestResource(collectionResourceRel = "books", path = "books")
@Repository
public interface UserPendingValidationRepository extends JpaRepository<UserPendingValidation, Long> {
    public UserPendingValidation findByUsername(String username);
    public boolean existsByUsernameAndCode(String username,String code);
    @Modifying
    @Transactional
    public void deleteByUsername(String username);
}