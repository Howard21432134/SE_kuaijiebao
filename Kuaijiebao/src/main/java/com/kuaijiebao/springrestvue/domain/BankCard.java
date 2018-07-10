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
    @Column(name="card")
    private String cardNum;
    @Column(name="user_id")
    private Long id;

}
