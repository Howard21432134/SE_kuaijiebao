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
@Table(name="financial_product_deal_record")
public class FPDR {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)//For Long
    @Column(name="dealrecord_id")
    private Long id;
    @Column(name="product_id")
    private Long productId;
    @Column(name="user_id")
    private Long userId;
    @Column(name="deal_id")
    private Long dealId;
    private Long num;
    private Date time;
    private Float price;
    private int type;

    /*
    public FPDR(){}

    public Long getDeal_id() {
        return deal_id;
    }

    public void setDeal_id(Long deal_id) {
        this.deal_id = deal_id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getPrice() {
        return price;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public Date getTime() {
        return time;
    }

    public int getType() {
        return type;
    }

    public Long getNum() {
        return num;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public void setTime(Date time) {
        this.time = time;
    }
    */
}