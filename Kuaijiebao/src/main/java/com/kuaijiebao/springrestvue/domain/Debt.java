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
    private Long user_id;
    private Long owner_id;
    private Long sum;
    private Date expect_discharge_time;//expect discharge time
    private String content;
    private Boolean whether_succeed;
    private Date succeed_time;
    private Boolean whether_discharge;
    private Date discharge_time;
    private Boolean type;
    private Float rate;
    private int state;

    public Debt(Long user_id,Long owner_id,Long sum,Date expect_discharge_time,String content,
                Boolean whether_succeed,Date succeed_time,Boolean whether_discharge,Date discharge_time,
                Boolean type,Float rate,int state){
        this.user_id = user_id;
        this.owner_id = owner_id;
        this.sum = sum;
        this.expect_discharge_time = expect_discharge_time;
        this.content = content;
        this.whether_succeed = whether_succeed;
        this.succeed_time = succeed_time;
        this.whether_discharge = whether_discharge;
        this.discharge_time = discharge_time;
        this.type = type;
        this.rate = rate;
        this.state = state;
    }

    /*
    public Debt(){}

    public void setId(Long id) {
        this.id = id;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public Float getRate() {
        return rate;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public Boolean getType() {
        return type;
    }

    public void setWhether_discharge(Boolean whether_discharge) {
        this.whether_discharge = whether_discharge;
    }

    public Boolean getWhether_discharge() {
        return whether_discharge;
    }

    public void setSucceed_time(Date succeed_time) {
        this.succeed_time = succeed_time;
    }

    public Date getSucceed_time() {
        return succeed_time;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setExpect_discharge_time(Date expect_discharge_time) {
        this.expect_discharge_time = expect_discharge_time;
    }

    public Date getExpect_discharge_time() {
        return expect_discharge_time;
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }

    public void setOwner_id(Long owner_id) {
        this.owner_id = owner_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Boolean getWhether_succeed() {
        return whether_succeed;
    }

    public Date getDischarge_time() {
        return discharge_time;
    }

    public Long getId() {
        return id;
    }

    public Long getOwner_id() {
        return owner_id;
    }

    public int getState() {
        return state;
    }

    public Long getSum() {
        return sum;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setDischarge_time(Date discharge_time) {
        this.discharge_time = discharge_time;
    }

    public void setWhether_succeed(Boolean whether_succeed) {
        this.whether_succeed = whether_succeed;
    }
*/
}
