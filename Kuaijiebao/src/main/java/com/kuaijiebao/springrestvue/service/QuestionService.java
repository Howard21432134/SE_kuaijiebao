package com.kuaijiebao.springrestvue.service;


import com.kuaijiebao.springrestvue.domain.Question;
import com.kuaijiebao.springrestvue.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    QuestionSpecifications questionSpecifications;

    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    public Question findOneById(Long id) { return questionRepository.findOneById(id); }

    public Question addQuestion(Question question){return questionRepository.save(question);}

    public List<Question> findVolumesByKeyword(String keyword) {
        return questionRepository.findAll(Specifications
                .where(questionSpecifications.titleContains(keyword))
                .or(questionSpecifications.contentContains(keyword))
                .or(questionSpecifications.answerContains(keyword))

        );
    }

    public Question update(Question question) {
        return questionRepository.save(question);
    }

}
