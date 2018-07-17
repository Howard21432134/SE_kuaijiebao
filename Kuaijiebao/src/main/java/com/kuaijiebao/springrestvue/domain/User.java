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
@Table(name = "user")
//component below leads parse error of http
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class User {

    @Id
    //@GeneratedValue(strategy=GenerationType.IDENTITY)//For Long
    @Column(name="user_id")
    private Long id;
    @Column(name="nick_name")
    private String nickname;
    private String name;
    private String identity;
    private String job;
    private Integer income;
    private String address;
    private String introduction;
    private String phone;
    private String email;

}
