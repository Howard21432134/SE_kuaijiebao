package com.kuaijiebao.springrestvue.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;


@JsonIgnoreProperties(ignoreUnknown = true)
@Data
//@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "personal_credit")
//component below leads parse error of http
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class PersonalCredit {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)//For Long
    @Column(name="pc_id")
    private Long personId;
    @Column(name="user_id")
    private Long userId;
    @Column(name="credit_limit")
    private Float creditLimit;
}
