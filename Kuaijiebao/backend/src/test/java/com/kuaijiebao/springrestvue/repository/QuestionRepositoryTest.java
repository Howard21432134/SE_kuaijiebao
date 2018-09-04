package com.kuaijiebao.springrestvue.repository;

import com.kuaijiebao.springrestvue.domain.Question;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class QuestionRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private QuestionRepository repository;

    Question question=new Question("ImTitle","ImContent","ImAnswer");

    @Before
    public void initialize() {
        entityManager.persistAndFlush(question);
    }


    //
    //findOneByQuestionId
    @Test
    public void GivenValidQuestion_whenFindByQuestionId_thenReturnQuestion() {
        Question found = repository.findOneByQuestionId(question.getQuestionId());
        assertThat(found.getTitle()).isEqualTo(question.getTitle());
    }

    //
    //findOneByQuestionId
    @Test
    public void GivenInvalidQuestionId_whenFindByQuestionId_thenReturnNull() {
         Question found = repository.findOneByQuestionId(-99L);
        assertThat(found).isNull();
    }


    //
    //save
    @Test
    public void givenValidQuestion_whenSave_thenReturnQuestion() {

        Question qOne=new Question("ImTitle","ImContent","ImAnswer");
        Question fromDb = repository.save(qOne);
        assertThat(fromDb.getTitle()).isEqualTo(qOne.getTitle());
    }

    //
    //deleteByQuestionId
    @Test
    public void givenValidQuestion_whenDeleteByQuestionId_thenSuccess() {
        repository.deleteByQuestionId(question.getQuestionId());
    }

}