package com.kuaijiebao.springrestvue.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.*;



@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignUpRequest {

    @NotBlank
    @Size(min = 4, max = 14)
    private String username;

    @NotBlank
    @Size(min = 4, max = 14)
    private String password;

    @NotBlank
    @Size(min = 4, max = 14)
    private String nickname;

    @NotBlank
    @Size(min = 4, max = 14)
    private String identity;

    private String name;
    private String job;
    private Integer income;
    private String address;
    private String introduction;
    private String phone;
    private String email;


}
