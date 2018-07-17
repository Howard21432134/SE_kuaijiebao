package com.kuaijiebao.springrestvue.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.kuaijiebao.springrestvue.domain.Account;

public class UserPrincipal implements UserDetails {
    private Long id;

    private String username;

    private Long userId;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(Long id, String username, String password, Long userId, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.userId=userId;
    }

    public static UserPrincipal create(Account user) {
        //List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
        //        new SimpleGrantedAuthority(role.getName().name())
        //).collect(Collectors.toList());

        List<GrantedAuthority> authorities=AuthorityUtils.createAuthorityList(user.getRoles());

        return new UserPrincipal(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getUserId(),
                authorities
        );
    }

    public Long getId() {
        return id;
    }


    @Override
    public String getUsername() {
        return username;
    }

    //doesnt have to @Override because userId not incuded in super class
    //
    public Long getUserId() {
        return userId;
    }

    @Override
    public String getPassword() {
        return password;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
