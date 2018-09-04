package com.kuaijiebao.springrestvue.payload;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class addDebtRequest {

    private Long userId;
    private Long sum;
    private Float rate;
    private Date ValidTime ;
    private Date ExpectDischargeTime;
    private String Content;

}