package com.kuaijiebao.springrestvue.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)//For Long
    @Column(name="question_id")
    private Long questionId;
    private String title;
    private String content;
    private String answer;

    public Question(String title, String content, String answer){
        this.title = title;
        this.content = content;
        this.answer = answer;
    }

}
