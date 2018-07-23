package com.kuaijiebao.springrestvue.api;


import com.kuaijiebao.springrestvue.domain.Question;
import com.kuaijiebao.springrestvue.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping(path = "/v2/counsel/questions")
    public List<Question> getAllQuestions() {
        List<Question> question = questionService.findAll();
        return question;
    }

    @GetMapping(path = "/v2/questions/search")
    public List<Question> getQuestionsByKeyword(@RequestParam(name="keyword", required = false) String keyword) {
        List<Question> questions = questionService.findVolumesByKeyword(keyword);
        return questions;
    }




}
