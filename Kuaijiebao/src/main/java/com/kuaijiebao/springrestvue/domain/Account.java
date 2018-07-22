package com.kuaijiebao.springrestvue.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


import org.hibernate.annotations.NaturalId;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "account", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        })
})
public class Account{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="account_id")
    private Long accountId;

    @NotBlank
    @Size(max = 15)
    private String username;

    @NotBlank
    @Size(max = 100)
    private String password;

    @Column(name="user_id")
    private Long userId;

    private String roles;
    /*
        @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
        @JoinTable(name = "user_roles",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id"))
        private Set<Role> roles = new HashSet<>();
    */

    public Account(String username, String password) {
        this.username = username;
        this.password = password;

    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long id) {
        this.accountId = accountId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
/*
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    */

    public String getRoles(){
        return roles;
    }

    public void setRoles(String roles){
        this.roles=roles;
    }

    public void setUserId(Long userId){
        this.userId=userId;
    }

    public Long getUserId(){
        return this.userId;
    }
}