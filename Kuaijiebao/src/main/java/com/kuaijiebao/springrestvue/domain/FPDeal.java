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
@Table(name = "financial_product_deal")
public class FPDeal {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)//For Long
    @Column(name="deal_id")
    private Long id;
    @Column(name="product_id")
    private Long productId;
    @Column(name="user_id")
    private Long userId;
    private Long num;
    private Boolean type;

    /*
    public FPDeal(){}

    public void setType(Boolean type) {
        this.type = type;
    }

    public Boolean getType() {
        return type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getUser_id() {
        return user_id;
    }
    */
}
