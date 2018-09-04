package com.kuaijiebao.springrestvue.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.lang.UsesSunHttpServer;

import javax.persistence.*;


@JsonIgnoreProperties(ignoreUnknown = true)
@Data
//@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
//component below leads parse error of http
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class User extends ResourceSupport {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)//For Long
    @Column(name="user_id")
    private Long userId;

    private String nickname;
    private String identity;

    private String name;
    private String job;
    private Integer income;
    private String address;
    private String introduction;
    private String phone;
    private String email;

    public User() {
        super();
    }

    //
    //For signUp
    public User(String nickname, String identity){
        super();
        this.nickname = nickname;
        this.identity = identity;
    }

    public User(String nickname, String identity, String name,
                String job, Integer income, String address,
                String introduction, String phone, String email){
        super();
        this.nickname = nickname;
        this.identity = identity;
        this.name = name;
        this.job = job;
        this.income = income;
        this.address = address;
        this.introduction = introduction;
        this.phone=phone;
        this.email = email;
    }

}
