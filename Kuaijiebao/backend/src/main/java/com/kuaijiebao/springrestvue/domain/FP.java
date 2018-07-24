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
@Table(name="financial_product")
public class FP {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)//For Long
    @Column(name="product_id")
    private Long id;
    private Long sum;
    @Column(name="product_name")
    private String name;
    private Float price;
    private String productor;

    public FP(Long sum, String name, Float price, String productor){

        this.sum = sum;
        this.name = name;
        this.price = price;
        this.productor = productor;
    }

    /*
    public FP(){}

    public Long getSum() {
        return sum;
    }

    public Long getId() {
        return id;
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getProductor() {
        return productor;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setProductor(String productor) {
        this.productor = productor;
    }
    */
}

