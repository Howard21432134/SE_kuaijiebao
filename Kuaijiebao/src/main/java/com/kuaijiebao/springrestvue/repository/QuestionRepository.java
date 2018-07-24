package com.kuaijiebao.springrestvue.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import com.kuaijiebao.springrestvue.domain.Question;

import javax.transaction.Transactional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> , JpaSpecificationExecutor<Question>{
    public Question findOneByQuestionId(Long id);
    public Question save(Question question);
    @Modifying
    @Transactional
    public void deleteByQuestionId(Long id);
}
