package com.kuaijiebao.springrestvue.repository;

import com.kuaijiebao.springrestvue.domain.PersonalCredit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalCreditRepository extends JpaRepository<PersonalCredit,Long> {

    public PersonalCredit findByUserId(Long id);
    public PersonalCredit findByPersonId(Long id);

}
