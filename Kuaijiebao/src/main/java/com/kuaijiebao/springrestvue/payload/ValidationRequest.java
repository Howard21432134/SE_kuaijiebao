package com.kuaijiebao.springrestvue.payload;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ValidationRequest {

    @NotBlank
    @Size(min = 4, max = 14)
    private String username;

    @NotBlank
    @Size(min = 4, max = 30)
    private String email;

    @NotBlank
    @Size(min = 2, max = 30)
    private String elem;//BANK_CARD PHONE_NUMBER EMAIL_ADDRESS PASS_WORD

    @NotBlank
    @Size(min = 2, max = 30)
    private String item;

}
