package com.kuaijiebao.springrestvue.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;


@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "debt")
public class Debt {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)//For Long
    @Column(name="debt_id")
    private Long id;
    @Column(name="user_id")
    private Long userId;
    @Column(name="owner_id")
    private Long ownerId;
    private Long sum;
    @Column(name="valid_time")
    private Date validTime;
    @Column(name="expect_discharge_time")
    private Date expectDischargeTime;//expect discharge time
    private String content;
    @Column(name="whether_succeed")
    private Boolean whetherSucceed;
    @Column(name="succeed_time")
    private Date succeedTime;
    @Column(name="whether_discharge")
    private Boolean whetherDischarge;
    @Column(name="discharge_time")
    private Date dischargeTime;
    private Boolean type;
    private Float rate;
    private Integer state;

    public Debt(Long userId, Long ownerId, Long sum, Date validTime, Date expectDischargeTime, String content,
               Boolean whetherSucceed, Date succeedTime, Boolean whetherDischarge,
               Date dischargeTime, Boolean type, Float rate, Integer state){

        this.userId = userId;
        this.ownerId = ownerId;
        this.sum = sum;
        this.validTime = validTime;
        this.expectDischargeTime = expectDischargeTime;
        this.content = content;
        this.whetherSucceed = whetherSucceed;
        this.succeedTime = succeedTime;
        this.whetherDischarge = whetherDischarge;
        this.dischargeTime = dischargeTime;
        this.type = type;
        this.rate = rate;
        this.state = state;
    }

}
