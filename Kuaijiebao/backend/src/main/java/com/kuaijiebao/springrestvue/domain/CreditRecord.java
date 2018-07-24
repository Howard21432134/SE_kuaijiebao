package com.kuaijiebao.springrestvue.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "credit_record")
public class CreditRecord {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)//For Long
    @Column(name="credit_record_id")
    private Long id;
    @Column(name="user_id")
    private Long userid;
    private Date time;
    @Column(name="old_sum")
    private Float oldsum;
    @Column(name="new_sum")
    private Float newsum;
    private Boolean type;

    public CreditRecord(Long userid,Date time,Float oldsum,Float newsum,Boolean type){
        this.userid=userid;
        this.time=time;
        this.oldsum=oldsum;
        this.newsum=newsum;
        this.type=type;
    }
}
