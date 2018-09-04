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
@Table(name = "bankcard")
public class BankCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="bankcard_id")
    private Long bankcardId;
    @Column(name="card")
    private String cardNum;
    @Column(name="user_id")
    private Long userId;

    public BankCard(String cardNum, Long userId){
        this.cardNum=cardNum;
        this.userId=userId;
    }

}
